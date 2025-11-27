package net.velaliilunalii.cozycafe.item.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ComplexeDrinkItem extends DrinkItem{

    public ComplexeDrinkItem(Item.Properties properties, Item replacementItem) {
        super(properties.stacksTo(1).durability(3), replacementItem);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, Level level, @NotNull LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            FoodProperties food = stack.getFoodProperties(entity);
            if (food != null) {
                player.getFoodData().eat(stack.getItem(), stack, player);
                for (Pair<MobEffectInstance, Float> effect : food.getEffects()) {
                    if (effect != null && level.random.nextFloat() < effect.getSecond()) {
                        player.addEffect(new MobEffectInstance(effect.getFirst()));
                    }
                }
                ItemStack newStack = stack.copy();
                newStack.setDamageValue(stack.getDamageValue() + 1);
                if (newStack.getDamageValue() >= newStack.getMaxDamage()) {
                    return new ItemStack(replacementItem);
                } else {
                    return newStack;
                }
            }
        }
        return stack;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.cozycafe.complexe_drink"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pTooltipFlag);
    }
}
