package net.velaliilunalii.cozycafe.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.velaliilunalii.cozycafe.block.ModBlocks;
import net.velaliilunalii.cozycafe.block.custom.PalmTreeFibersBlock;
import net.velaliilunalii.cozycafe.worldgen.tree.ModTrunkPlacerTypes;

import java.util.List;
import java.util.function.BiConsumer;


public class PalmTrunkPlacer extends TrunkPlacer {

    public static final Codec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            trunkPlacerParts(instance).apply(instance, PalmTrunkPlacer::new)
    );

    public PalmTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.PALM_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setBlock,
                                                            RandomSource random, int height, BlockPos startPos, TreeConfiguration config) {

        BlockPos.MutableBlockPos pos = startPos.mutable();
        Direction bendDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        boolean rotateClockWise = random.nextBoolean();
        int lines = 3 + random.nextInt(2); // 3 or 4
        int bendAt = height / lines;

        for (int i = 0; i < height; i++) {
            if (i != 0 && i % bendAt == 0) {
                if (random.nextBoolean()) {
                    bendDirection = rotateClockWise ? bendDirection.getClockWise() : bendDirection.getCounterClockWise();
                }
                pos.move(bendDirection); // Apply single sideways offset
            }

            BlockState trunkState = config.trunkProvider.getState(random, pos).setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
            BlockPos currentPos = pos.immutable();
            setBlock.accept(currentPos, trunkState);

            placeFiber(level, setBlock, currentPos, random, i, bendAt);

            pos.move(Direction.UP);
        }

        return List.of(new FoliagePlacer.FoliageAttachment(pos, 0, false));
    }

    public void placeFiber(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setBlock,
                           BlockPos currentPos, RandomSource random, int i, int bendAt){
        if (random.nextFloat() < 0.3F){
            for (Direction dir : Direction.Plane.HORIZONTAL){
                BlockPos fiberPos = currentPos.relative(dir);
                if (level.isStateAtPosition(fiberPos, BlockBehaviour.BlockStateBase::isAir) && random.nextFloat() < 0.8F){
                    BlockState palmFiberState = ModBlocks.PALM_TREE_FIBERS.get()
                            .defaultBlockState()
                            .setValue(PalmTreeFibersBlock.getFacingProperty(dir.getOpposite()), true);
                    setBlock.accept(fiberPos, palmFiberState);
                    if (i-1 % bendAt !=0 && random.nextFloat() < 0.3F){
                        placeFiber(level, setBlock, currentPos, random, i, bendAt);
                    }
                }
            }
        }
    }

}
