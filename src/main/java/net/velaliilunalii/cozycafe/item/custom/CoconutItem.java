package net.velaliilunalii.cozycafe.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.velaliilunalii.cozycafe.block.ModBlocks;
import net.velaliilunalii.cozycafe.entity.ModEntities;
import net.velaliilunalii.cozycafe.entity.custom.CoconutEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CoconutItem extends Item {
    public CoconutItem(Properties properties) {
        super(properties);
    }

    private boolean mayPlaceOn (Level level, BlockPos pos, UseOnContext context){
        BlockState clicked = level.getBlockState(context.getClickedPos());
        BlockState bellowClicked = level.getBlockState(pos.below());
        return (((clicked.is(Blocks.GRASS_BLOCK) || clicked.is(Blocks.DIRT) || clicked.is(Blocks.SAND)) &&
                context.getClickedFace() == Direction.UP) ||
                bellowClicked.is(Blocks.GRASS_BLOCK) || bellowClicked.is(Blocks.DIRT) || bellowClicked.is(Blocks.SAND))
                && level.getBlockState(pos).canBeReplaced();
    }

    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos().relative(context.getClickedFace());
        Player player = context.getPlayer();
        ItemStack stack = context.getItemInHand();

        if (!level.isClientSide && player != null && player.isShiftKeyDown()) {
            if (mayPlaceOn(level, pos, context)) {
                level.setBlock(pos, ModBlocks.PALM_SAPLING.get().defaultBlockState(), 3);
                level.playSound(null, pos, SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                return InteractionResult.SUCCESS;
            }
        }
        return super.useOn(context);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!player.isShiftKeyDown()) {
            if (!level.isClientSide) {
                CoconutEntity coconut = new CoconutEntity(ModEntities.COCONUT.get(), level, player);
                coconut.setItem(itemstack);
                coconut.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(coconut);
            }

            player.getCooldowns().addCooldown(this, 20); // Optional cooldown
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @NotNull Level pLevel, List<Component> pTooltipComponents, @NotNull TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.cozycafe.coconut_1"));
        pTooltipComponents.add(Component.translatable("tooltip.cozycafe.coconut_2"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pTooltipFlag);
    }
}