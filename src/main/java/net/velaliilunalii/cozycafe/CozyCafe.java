package net.velaliilunalii.cozycafe;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.velaliilunalii.cozycafe.block.ModBlocks;
import net.velaliilunalii.cozycafe.entity.ModEntities;
import net.velaliilunalii.cozycafe.item.ModCreativeModeTabs;
import net.velaliilunalii.cozycafe.item.ModItemProperties;
import net.velaliilunalii.cozycafe.item.ModItems;
import net.velaliilunalii.cozycafe.loot.ModLootModifiers;
import net.velaliilunalii.cozycafe.sound.ModSounds;
import net.velaliilunalii.cozycafe.worldgen.tree.ModFoliagePlacers;
import net.velaliilunalii.cozycafe.worldgen.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;

// Very important Comment
// The value here should match an entry in the META-INF/mods.toml file
@Mod(CozyCafe.MOD_ID)
public class CozyCafe {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "cozycafe";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public CozyCafe() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEntities.ENTITIES.register(modEventBus);
        ModSounds.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)  {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.TEA_LEAVES.get(), 0.3f);
            ComposterBlock.COMPOSTABLES.put(ModItems.MATCHA_POWDER.get(), 0.6f);
            ComposterBlock.COMPOSTABLES.put(ModItems.GUAVA_FRUIT.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModItems.COCONUT.get(), 0.4f);
            ComposterBlock.COMPOSTABLES.put(ModItems.OPEN_COCONUT.get(), 0.2f);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.EMBER_BLOOM.get(), 1f);
            ComposterBlock.COMPOSTABLES.put(ModItems.CRUSHED_EMBER_BLOOM.get(), 1f);
        });
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ModItemProperties.addCustomItemProperties();
            event.enqueueWork(() -> {
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.PALM_LEAVES.get(), RenderType.cutoutMipped());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.PALM_SAPLING.get(), RenderType.cutoutMipped());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.PALM_TREE_FIBERS.get(), RenderType.cutoutMipped());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.LOST_FEATHERS.get(), RenderType.cutoutMipped());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.IMPALED_BONE.get(), RenderType.cutoutMipped());
                ItemBlockRenderTypes.setRenderLayer(ModBlocks.EMBER_BLOOM.get(), RenderType.cutoutMipped());
            });
        }
        @SubscribeEvent
        public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.COCONUT.get(), ThrownItemRenderer::new);
        }
    }
}
