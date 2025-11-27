package net.velaliilunalii.cozycafe.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.velaliilunalii.cozycafe.item.ModItems;

public class CoconutEntity extends ThrowableItemProjectile {

    public CoconutEntity(EntityType<? extends CoconutEntity> type, Level level) {
        super(type, level);
    }

    public CoconutEntity(EntityType<? extends CoconutEntity> type, Level level, LivingEntity thrower) {
        super(type, thrower, level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.COCONUT.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            ItemEntity drop1 = new ItemEntity(level(), this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.OPEN_COCONUT.get()));
            ItemEntity drop2 = new ItemEntity(level(), this.getX(), this.getY(), this.getZ(), new ItemStack(ModItems.OPEN_COCONUT.get()));
            level().addFreshEntity(drop1);
            level().addFreshEntity(drop2);
            this.discard();
        }
    }
}
