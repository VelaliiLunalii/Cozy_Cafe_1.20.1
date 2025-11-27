package net.velaliilunalii.cozycafe.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.velaliilunalii.cozycafe.CozyCafe;
import net.velaliilunalii.cozycafe.entity.custom.CoconutEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(Registries.ENTITY_TYPE, CozyCafe.MOD_ID);

    public static final RegistryObject<EntityType<CoconutEntity>> COCONUT =
            ENTITIES.register("coconut", () -> EntityType.Builder.<CoconutEntity>of(
                            CoconutEntity::new,
                            MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build("coconut"));
}
