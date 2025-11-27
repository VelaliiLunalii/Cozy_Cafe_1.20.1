package net.velaliilunalii.cozycafe.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.velaliilunalii.cozycafe.CozyCafe;
import net.velaliilunalii.cozycafe.block.ModBlocks;
import net.velaliilunalii.cozycafe.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CozyCafe.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.COCKTAIL_GLASS.get());
        basicItem(ModItems.GLASS_CUP.get());
        basicItem(ModItems.EMPTY_GLASS.get());
        basicItem(ModItems.TEA_LEAVES.get());
        basicItem(ModItems.MATCHA_POWDER.get());
        basicItem(ModItems.GUAVA_FRUIT.get());
        basicItem(ModItems.COCONUT.get());
        basicItem(ModItems.OPEN_COCONUT.get());
        basicItem(ModItems.PALM_FIBER.get());
        basicItem(ModItems.CRUSHED_EMBER_BLOOM.get());
        basicItem(ModItems.EMBER_BLOOM_SEED.get());
        basicItem(ModItems.EMBER_BLOOM_PETALS.get());
        basicItem(ModItems.JUICE_PRESS.get());
        basicItem(ModItems.WOODEN_SHAKER.get());
        basicItem(ModItems.IRON_SHAKER.get());
        basicItem(ModItems.GOLDEN_SHAKER.get());
        basicItem(ModItems.DIAMOND_SHAKER.get());
        basicItem(ModItems.NETHERITE_SHAKER.get());

        for (RegistryObject<Item> item : ModItems.COCKTAIL_UMBRELLAS){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.COCKTAIL_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.COCONUT_COCKTAILS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.TEA_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.TEA_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.PUMPKIN_SPICE_LATTE_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.PUMPKIN_SPICE_LATTE_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.CHOCOLATE_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.CHOCOLATE_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.MATCHA_TEA_LATTE_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.MATCHA_TEA_LATTE_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.WATERMELON_ROSE_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.WATERMELON_ROSE_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.IL_SIRACUSANO_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.IL_SIRACUSANO_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.GUAYABA_COLADA_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.GUAYABA_COLADA_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.GENDER_FLUID_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.GENDER_FLUID_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.BERRYLICIOUS_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.BERRYLICIOUS_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.AUREATE_SPRING_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.AUREATE_SPRING_SHAKERS){
            basicItem(item.get());
        }

        for (RegistryObject<Item> item : ModItems.LA_SIGNORA_GLASSES){
            basicItem(item.get());
        }
        for (RegistryObject<Item> item : ModItems.LA_SIGNORA_SHAKERS){
            basicItem(item.get());
        }

        buttonItem(ModBlocks.PALM_BUTTON, ModBlocks.PALM_PLANKS);
        fenceItem(ModBlocks.PALM_FENCE, ModBlocks.PALM_PLANKS);
        simpleBlockItem(ModBlocks.PALM_DOOR);
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CozyCafe.MOD_ID,"item/" + item.getId().getPath()));
    }

    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(CozyCafe.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
}