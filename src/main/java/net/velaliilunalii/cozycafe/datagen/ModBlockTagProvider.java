package net.velaliilunalii.cozycafe.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.velaliilunalii.cozycafe.CozyCafe;
import net.velaliilunalii.cozycafe.block.ModBlocks;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CozyCafe.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_LOG.get())
                .add(ModBlocks.PALM_WOOD.get())
                .add(ModBlocks.STRIPPED_PALM_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.PALM_PLANKS.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PALM_LOG.get())
                .add(ModBlocks.PALM_WOOD.get())
                .add(ModBlocks.STRIPPED_PALM_LOG.get())
                .add(ModBlocks.STRIPPED_PALM_WOOD.get())
                .add(ModBlocks.PALM_PLANKS.get());

        this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.PALM_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.PALM_FENCE_GATE.get());
    }
}
