package net.velaliilunalii.cozycafe.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.velaliilunalii.cozycafe.CozyCafe;

public class ModItemProperties {
    public static void addCustomItemProperties() {
        ItemProperties.register(ModItems.GUAVA_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.APPLE_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.GLOW_BERRY_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.SWEET_BERRY_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.CARROT_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.CHORUS_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.MELON_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.COCONUT_MILK.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.CARBONATED_WATER.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.PUMPKIN_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
        ItemProperties.register(ModItems.CACTUS_JUICE.get(), ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID, "in_hand"),
                (itemStack, clientLevel, livingEntity, i) -> (livingEntity != null && livingEntity.getItemInHand(InteractionHand.MAIN_HAND) == itemStack) ? 1f : 0f);
    }
}
