package net.velaliilunalii.cozycafe.item.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.velaliilunalii.cozycafe.sound.ModSounds;
import org.jetbrains.annotations.NotNull;

public class DrinkItem extends Item {
    Item replacementItem;

    public DrinkItem(Properties properties, Item replacementItem) {
        super(properties);
        this.replacementItem = replacementItem;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        return new ItemStack(replacementItem);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack itemStack) {
        return replacementItem != null;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, Level level, @NotNull LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            FoodProperties food = stack.getFoodProperties(entity);
            if (food != null) {
                player.eat(level, stack);
                for (Pair<MobEffectInstance, Float> effect : food.getEffects()) {
                    if (effect != null && level.random.nextFloat() < effect.getSecond()) {
                        player.addEffect(new MobEffectInstance(effect.getFirst()));
                    }
                }
                if (replacementItem != null && !player.getInventory().add(new ItemStack(replacementItem))) {
                    player.drop(new ItemStack(replacementItem), false);
                }
                stack.shrink(1);
            }
        }

        return stack;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int count) {
        if (entity instanceof Player player && count == 1) {
            level.playLocalSound(
                    player.getX(), player.getY(), player.getZ(),
                    ModSounds.DRINKING_SOUND.get(),
                    SoundSource.PLAYERS,
                    1F, 1.0F, false
            );
        }
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        return 16;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }
}
