package net.velaliilunalii.cozycafe.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.velaliilunalii.cozycafe.item.ModItems;

import java.util.List;

public class EmptyGlassItem extends Item {
    public EmptyGlassItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Player player = context.getPlayer();
        Direction face = context.getClickedFace();

        BlockState state = level.getBlockState(clickedPos);
        boolean isBubbleColumn = state.is(Blocks.BUBBLE_COLUMN);

        BlockPos relativePos = clickedPos.relative(face);
        BlockState relativeState = level.getBlockState(relativePos);
        boolean isAdjacentBubbleColumn = relativeState.is(Blocks.BUBBLE_COLUMN);

        if ((isBubbleColumn || isAdjacentBubbleColumn) && !level.isClientSide && player != null) {
            if (!player.getInventory().add(new ItemStack(ModItems.CARBONATED_WATER.get()))) {
                player.drop(new ItemStack(ModItems.CARBONATED_WATER.get()), false);
            }
            player.getItemInHand(context.getHand()).shrink(1);
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.cozycafe.empty_glass"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pTooltipFlag);
    }
}
