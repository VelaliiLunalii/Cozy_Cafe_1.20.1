package net.velaliilunalii.cozycafe.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.velaliilunalii.cozycafe.block.ModBlocks;

import static net.velaliilunalii.cozycafe.block.custom.EmberBloomBlock.FACING;

public class EmberBloomSeedItem extends ItemNameBlockItem {
    public EmberBloomSeedItem(Block p_41579_, Properties p_41580_) {
        super(p_41579_, p_41580_);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace().getOpposite());
        BlockState clickedBlockState = level.getBlockState(pos);

        if (clickedBlockState.is(ModBlocks.IMPALED_BONE.get())) {
            BlockState newState = this.getBlock().defaultBlockState().setValue(FACING, clickedBlockState.getValue(FACING));
            level.setBlock(pos, newState, 3);

            if (!context.getPlayer().getAbilities().instabuild) {
                context.getItemInHand().shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    public boolean canPlace(BlockPlaceContext context) {
        BlockState clickedBlockState = context.getLevel().getBlockState(context.getClickedPos());
        return clickedBlockState.is(ModBlocks.IMPALED_BONE.get());
    }
}
