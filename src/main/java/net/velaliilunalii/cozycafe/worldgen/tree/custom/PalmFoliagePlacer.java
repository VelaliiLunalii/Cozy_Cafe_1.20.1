package net.velaliilunalii.cozycafe.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.core.Direction;
import net.velaliilunalii.cozycafe.worldgen.tree.ModFoliagePlacers;

import java.util.ArrayList;
import java.util.List;


public class PalmFoliagePlacer extends FoliagePlacer {

    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> foliagePlacerParts(instance)
            .apply(instance, PalmFoliagePlacer::new));

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.PALM_FOLIAGE_PLACER.get(); // Register this!
    }

    @Override
    protected void createFoliage(LevelSimulatedReader reader, FoliageSetter setter, RandomSource rand, TreeConfiguration config,
                                 int height, FoliageAttachment attachment, int radius, int offset, int layer) {
        //Definitions
        BlockPos center = attachment.pos();
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        ArrayList<BlockPos> cardinal = new ArrayList<>(List.of(
                new BlockPos(1, 0, 0),   // East
                new BlockPos(-1, 0, 0),  // West
                new BlockPos(0, 0, -1),  // North
                new BlockPos(0, 0, 1)    // South
        ));

        ArrayList<BlockPos> diagonals = new ArrayList<>(List.of(
                new BlockPos(1, 0, 1),   // South-East
                new BlockPos(-1, 0, 1),  // South-West
                new BlockPos(1, 0, -1),  // North-East
                new BlockPos(-1, 0, -1)  // North-West
        ));
        if (rand.nextBoolean()){diagonals.remove(rand.nextInt(diagonals.size()));}
        if (rand.nextBoolean()){diagonals.remove(rand.nextInt(diagonals.size()));}

        ArrayList<BlockPos> diagonal_leans = new ArrayList<>(diagonals);
        for (int i = 0; i < diagonal_leans.size(); i++) {
            BlockPos oldPos = diagonal_leans.get(i);
            int random = rand.nextInt(2);
            diagonal_leans.set(i, new BlockPos(random * oldPos.getX(), oldPos.getY(), (1 - random) * oldPos.getZ()));
        }

        ArrayList<BlockPos> cardinal_leans = new ArrayList<>(cardinal);
        for (int i = 0; i < cardinal_leans.size(); i++) {
            ArrayList<BlockPos> adjacent_diagonals = new ArrayList<>();
            for (BlockPos diag : diagonals) {   //get adjacents
                if (cardinal_leans.get(i).distSqr(new Vec3i(diag.getX(), diag.getZ(), diag.getZ())) <= 2){
                    adjacent_diagonals.add(diag);
                }
            }
            if (adjacent_diagonals.size() == 1){
                if (cardinal_leans.get(i).getX() != 0){
                    cardinal_leans.set(i, new BlockPos(0, cardinal_leans.get(i).getY(), - adjacent_diagonals.get(0).getZ()));
                }else {
                    cardinal_leans.set(i, new BlockPos(- adjacent_diagonals.get(0).getX(), cardinal_leans.get(i).getY(), 0));
                }
            } else if (adjacent_diagonals.isEmpty()) {
                int random = rand.nextInt(3) - 1;
                if (cardinal_leans.get(i).getX() != 0){
                    cardinal_leans.set(i, new BlockPos(0, cardinal_leans.get(i).getY(), random));
                }else {
                    cardinal_leans.set(i, new BlockPos(random, cardinal_leans.get(i).getY(), 0));
                }
            } else {
                cardinal_leans.set(i, BlockPos.ZERO);
            }
        }

        //Static placements
        setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, center);
        if (rand.nextBoolean()) setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, center.relative(Direction.UP, 1));
        for (BlockPos dir : cardinal) {
            if (rand.nextFloat() < 0.66) setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, center.offset(dir.getX(), -1, dir.getZ()));
        }
        for (BlockPos dir : diagonals) {
            setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, center.offset(dir.getX(), 0, dir.getZ()));
            if (rand.nextFloat() < 0.66) setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, center.offset(dir.getX(), 1, dir.getZ()));
        }

        //Cardinal placements
        for (int i = 0; i < cardinal.size(); i++) {
            BlockPos dir = cardinal.get(i);
            BlockPos currentPos = center;
            for (int j = 0; j<6; ++j){
                if(j>2 && rand.nextFloat() < 0.05*(j-2)) break;
                if (j==3 || j== 5){
                    currentPos = currentPos.offset(0, -1, 0);
                    if (cardinal_leans.get(i) != BlockPos.ZERO){
                        currentPos = currentPos.offset(cardinal_leans.get(i));
                        setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, currentPos);
                        currentPos = currentPos.offset(0, 1, 0);
                        setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, currentPos);
                        currentPos = currentPos.offset(0, -1, 0);
                    }
                }
                currentPos = currentPos.offset(dir.getX(), 0, dir.getZ());
                setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, currentPos);
                currentPos = currentPos.offset(0, 1, 0);
                setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, currentPos);
                currentPos = currentPos.offset(0, -1, 0);
            }
        }

        //Diagonal placements
        for (int i=0; i<diagonals.size(); ++i){
            BlockPos dir = diagonals.get(i);
            BlockPos dirLean = diagonal_leans.get(i);
            BlockPos currentPos = center;
            currentPos = currentPos.offset(dir.getX() - dirLean.getX(), -1, dir.getZ() - dirLean.getZ());
            for(int j = 0; j<8; ++j) {
                if(j>3 && rand.nextFloat() < 0.05*(j-3)) break;
                if (j==2) currentPos = currentPos.offset(dir.getX() - 2* dirLean.getX(), 1, dir.getZ() - 2* dirLean.getZ());
                else if (j!=0 && j % 2 == 0) currentPos = currentPos.offset(dir.getX() - 2* dirLean.getX(), -1, dir.getZ() - 2* dirLean.getZ());
                currentPos = currentPos.offset(dirLean.getX(), 0, dirLean.getZ());
                setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, currentPos);
                currentPos = currentPos.offset(0, 1, 0);
                setTryAndPlaceLeaf(reader, setter, rand, config, mutablePos, currentPos);
                currentPos = currentPos.offset(0, -1, 0);
            }
        }
    }

    private static void setTryAndPlaceLeaf(LevelSimulatedReader reader, FoliageSetter setter, RandomSource rand, TreeConfiguration config, BlockPos.MutableBlockPos mutablePos, BlockPos center) {
        if (rand.nextFloat() < 0.95) {
            mutablePos.set(center);
            placeLeaf(reader, setter, mutablePos, config, rand);
        }
    }

    private static void placeLeaf(LevelSimulatedReader level, FoliageSetter placer,
                                  BlockPos pos, TreeConfiguration config, RandomSource random) {
        if (level.isStateAtPosition(pos, state -> state.canBeReplaced())) {
            BlockState leafState = config.foliageProvider.getState(random, pos);
            if (leafState.hasProperty(net.minecraft.world.level.block.LeavesBlock.PERSISTENT)) {
                leafState = leafState.setValue(net.minecraft.world.level.block.LeavesBlock.PERSISTENT, true);
            }
            placer.set(pos, leafState);
        }
    }

    @Override
    public int foliageHeight(RandomSource p_225601_, int p_225602_, TreeConfiguration p_225603_) {
        return 1;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
