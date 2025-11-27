package net.velaliilunalii.cozycafe.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.velaliilunalii.cozycafe.CozyCafe;
import net.velaliilunalii.cozycafe.block.ModBlocks;
import net.velaliilunalii.cozycafe.util.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, CozyCafe.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.PALM_LOGS)
                .add(ModBlocks.PALM_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_PALM_LOG.get().asItem())
                .add(ModBlocks.PALM_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_PALM_WOOD.get().asItem());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_LOG.get().asItem())
                .add(ModBlocks.PALM_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_PALM_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_PALM_WOOD.get().asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.PALM_PLANKS.get().asItem());
    }
}
