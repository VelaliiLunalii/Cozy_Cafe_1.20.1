package net.velaliilunalii.cozycafe.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.velaliilunalii.cozycafe.block.ModBlocks;
import net.velaliilunalii.cozycafe.item.ModItems;

import javax.annotation.Nullable;

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        Level level = context.getLevel();
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(ModBlocks.PALM_LOG.get())) {
                return ModBlocks.STRIPPED_PALM_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if(state.is(ModBlocks.PALM_WOOD.get())) {
                return ModBlocks.STRIPPED_PALM_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        super.playerWillDestroy(level, pos, state, player);

        // Radius to check for leaves
        int radius = 7;
        BlockPos.betweenClosed(pos.offset(-radius, -radius, -radius), pos.offset(radius, radius, radius))
                .forEach(targetPos -> {
                    BlockState targetState = level.getBlockState(targetPos);
                    if (targetState.getBlock() instanceof PalmLeavesBlock &&
                            targetState.hasProperty(LeavesBlock.PERSISTENT) &&
                            targetState.getValue(LeavesBlock.PERSISTENT)) {

                        // Unset persistent so natural decay begins
                        level.setBlock(targetPos,
                                targetState.setValue(LeavesBlock.PERSISTENT, false), 2);
                    }
                });
    }

}
