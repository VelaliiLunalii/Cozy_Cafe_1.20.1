package net.velaliilunalii.cozycafe.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.velaliilunalii.cozycafe.CozyCafe;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_TREE_PALM_BEACH = registerKey("add_tree_palm_beach");
    public static final ResourceKey<BiomeModifier> ADD_TREE_PALM_DESERT = registerKey("add_tree_palm_desert");
    public static final ResourceKey<BiomeModifier> ADD_TREE_PALM_JUNGLE = registerKey("add_tree_palm_jungle");
    public static final ResourceKey<BiomeModifier> ADD_EMBER_BLOOM = registerKey("add_ember_bloom");
    public static final ResourceKey<BiomeModifier> ADD_LOST_FEATHERS_COMMON = registerKey("add_lost_feathers_common");
    public static final ResourceKey<BiomeModifier> ADD_LOST_FEATHERS_RARE = registerKey("add_lost_feathers_rare");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_TREE_PALM_BEACH, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.BEACH)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PALM_PLACED_BEACH_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_PALM_DESERT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DESERT)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PALM_PLACED_DESERT_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_TREE_PALM_JUNGLE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SPARSE_JUNGLE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PALM_PLACED_JUNGLE_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_EMBER_BLOOM, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.NETHER_WASTES)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EMBER_BLOOM_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
        context.register(ADD_LOST_FEATHERS_COMMON, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.SPARSE_JUNGLE),
                        biomes.getOrThrow(Biomes.BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.DARK_FOREST),
                        biomes.getOrThrow(Biomes.FLOWER_FOREST),
                        biomes.getOrThrow(Biomes.FOREST),
                        biomes.getOrThrow(Biomes.GROVE),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_BIRCH_FOREST),
                        biomes.getOrThrow(Biomes.STONY_SHORE),
                        biomes.getOrThrow(Biomes.WOODED_BADLANDS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_FOREST),
                        biomes.getOrThrow(Biomes.WINDSWEPT_GRAVELLY_HILLS)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LOST_FEATHERS_COMMON_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_LOST_FEATHERS_RARE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(
                        biomes.getOrThrow(Biomes.BAMBOO_JUNGLE),
                        biomes.getOrThrow(Biomes.BADLANDS),
                        biomes.getOrThrow(Biomes.CHERRY_GROVE),
                        biomes.getOrThrow(Biomes.ERODED_BADLANDS),
                        biomes.getOrThrow(Biomes.MEADOW),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                        biomes.getOrThrow(Biomes.OLD_GROWTH_PINE_TAIGA),
                        biomes.getOrThrow(Biomes.PLAINS),
                        biomes.getOrThrow(Biomes.SAVANNA),
                        biomes.getOrThrow(Biomes.SAVANNA_PLATEAU),
                        biomes.getOrThrow(Biomes.SNOWY_TAIGA),
                        biomes.getOrThrow(Biomes.SUNFLOWER_PLAINS),
                        biomes.getOrThrow(Biomes.SWAMP),
                        biomes.getOrThrow(Biomes.TAIGA),
                        biomes.getOrThrow(Biomes.WINDSWEPT_HILLS),
                        biomes.getOrThrow(Biomes.WINDSWEPT_SAVANNA)
                ),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LOST_FEATHERS_RARE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(CozyCafe.MOD_ID, name));
    }
}
