package net.velaliilunalii.cozycafe.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.velaliilunalii.cozycafe.CozyCafe;
import net.velaliilunalii.cozycafe.block.ModBlocks;
import net.velaliilunalii.cozycafe.item.custom.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CozyCafe.MOD_ID);

    private enum UmbrellaColors{
        BROWN_UMBRELLA("brown_umbrella_", BROWN_UMBRELLA_COCKTAIL_GLASS),
        BLACK_UMBRELLA("black_umbrella_", BLACK_UMBRELLA_COCKTAIL_GLASS),
        GRAY_UMBRELLA("gray_umbrella_", GRAY_UMBRELLA_COCKTAIL_GLASS),
        LIGHT_GRAY_UMBRELLA("light_gray_umbrella_", LIGHT_GRAY_UMBRELLA_COCKTAIL_GLASS),
        WHITE_UMBRELLA("white_umbrella_", WHITE_UMBRELLA_COCKTAIL_GLASS),
        LIME_UMBRELLA("lime_umbrella_", LIME_UMBRELLA_COCKTAIL_GLASS),
        GREEN_UMBRELLA("green_umbrella_", GREEN_UMBRELLA_COCKTAIL_GLASS),
        CYAN_UMBRELLA("cyan_umbrella_", CYAN_UMBRELLA_COCKTAIL_GLASS),
        LIGHT_BLUE_UMBRELLA("light_blue_umbrella_", LIGHT_BLUE_UMBRELLA_COCKTAIL_GLASS),
        BLUE_UMBRELLA("blue_umbrella_", BLUE_UMBRELLA_COCKTAIL_GLASS),
        PURPLE_UMBRELLA("purple_umbrella_", PURPLE_UMBRELLA_COCKTAIL_GLASS),
        MAGENTA_UMBRELLA("magenta_umbrella_", MAGENTA_UMBRELLA_COCKTAIL_GLASS),
        PINK_UMBRELLA("pink_umbrella_", PINK_UMBRELLA_COCKTAIL_GLASS),
        RED_UMBRELLA("red_umbrella_", RED_UMBRELLA_COCKTAIL_GLASS),
        ORANGE_UMBRELLA("orange_umbrella_", ORANGE_UMBRELLA_COCKTAIL_GLASS),
        YELLOW_UMBRELLA("yellow_umbrella_", YELLOW_UMBRELLA_COCKTAIL_GLASS);

        private final String name;
        private final Supplier<Item> itemSupplier;

        UmbrellaColors(String name, RegistryObject<Item> item) {
            this.name = name;
            this.itemSupplier = item;
        }

        public String getName() {
            return name;
        }

        public Item getItem() {
            return itemSupplier.get();
        }
    }

    private static ArrayList<RegistryObject<Item>> cocktailGlassesMaker(String name, FoodProperties foodProperties){
        ArrayList<RegistryObject<Item>> list = new ArrayList<>();
        list.add(ITEMS.register(name,
                () -> new ComplexeDrinkItem(new Item.Properties().food(foodProperties), COCKTAIL_GLASS.get())));
        for (UmbrellaColors umbrella : UmbrellaColors.values()){
            list.add(ITEMS.register(umbrella.getName().concat(name),
                    () -> new ComplexeDrinkItem(new Item.Properties().food(foodProperties), umbrella.getItem())));
        }
        return list;
    }

    private static ArrayList<RegistryObject<Item>> cupAndCocktailGlassesMaker(String name, FoodProperties foodProperties){
        String iced = "iced_";
        ArrayList<RegistryObject<Item>> list = cocktailGlassesMaker(iced.concat(name), foodProperties);
        list.add(ITEMS.register(name,
                () -> new ComplexeDrinkItem(new Item.Properties().food(foodProperties), GLASS_CUP.get())));
        return list;
    }

    private static ArrayList<RegistryObject<Item>> shakersMaker(String name, Supplier<Item[]> supplier){
        ArrayList<RegistryObject<Item>> list = new ArrayList<>();
        list.add(ITEMS.register(name.concat("_filled_wooden_shaker"),
                () -> new ShakerItem(new Item.Properties(), 0, supplier)));
        list.add(ITEMS.register(name.concat("_filled_iron_shaker"),
                () -> new ShakerItem(new Item.Properties(), 1, supplier)));
        list.add(ITEMS.register(name.concat("_filled_golden_shaker"),
                () -> new ShakerItem(new Item.Properties(), 2, supplier)));
        list.add(ITEMS.register(name.concat("_filled_diamond_shaker"),
                () -> new ShakerItem(new Item.Properties(), 3, supplier)));
        list.add(ITEMS.register(name.concat("_filled_netherite_shaker"),
                () -> new ShakerItem(new Item.Properties(), 4, supplier)));
        return list;
    }

    public static final RegistryObject<Item> COCKTAIL_GLASS = ITEMS.register("cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GLASS_CUP = ITEMS.register("glass_cup",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_GLASS = ITEMS.register("empty_glass",
            () -> new EmptyGlassItem(new Item.Properties()));
    public static final RegistryObject<Item> TEA_LEAVES = ITEMS.register("tea_leaves",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MATCHA_POWDER = ITEMS.register("matcha_powder",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GUAVA_FRUIT = ITEMS.register("guava_fruit",
            () -> new Item(new Item.Properties().food(Foods.CARROT)));
    public static final RegistryObject<Item> COCONUT = ITEMS.register("coconut",
            () -> new CoconutItem(new Item.Properties()));
    public static final RegistryObject<Item> OPEN_COCONUT = ITEMS.register("open_coconut",
            () -> new Item(new Item.Properties().food(Foods.APPLE)));
    public static final RegistryObject<Item> PALM_FIBER = ITEMS.register("palm_fiber",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SHARP_BONE = ITEMS.register("sharp_bone",
            SharpBoneItem::new);
    public static final RegistryObject<Item> EMBER_BLOOM_SEED = ITEMS.register("ember_bloom_seed",
            () -> new EmberBloomSeedItem(ModBlocks.EMBER_BLOOM.get(), new Item.Properties()));
    public static final RegistryObject<Item> EMBER_BLOOM_PETALS = ITEMS.register("ember_bloom_petals",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRUSHED_EMBER_BLOOM = ITEMS.register("crushed_ember_bloom",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JUICE_PRESS = ITEMS.register("juice_press",
            () -> new JuicePressItem(new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_SHAKER = ITEMS.register("wooden_shaker",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_SHAKER = ITEMS.register("iron_shaker",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_SHAKER = ITEMS.register("golden_shaker",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SHAKER = ITEMS.register("diamond_shaker",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SHAKER = ITEMS.register("netherite_shaker",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GUAVA_JUICE = ITEMS.register("guava_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), null));
    public static final RegistryObject<Item> APPLE_JUICE = ITEMS.register("apple_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), null));
    public static final RegistryObject<Item> GLOW_BERRY_JUICE = ITEMS.register("glow_berry_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.POOR_DRINK), null));
    public static final RegistryObject<Item> SWEET_BERRY_JUICE = ITEMS.register("sweet_berry_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.POOR_DRINK), null));
    public static final RegistryObject<Item> CARROT_JUICE = ITEMS.register("carrot_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), null));
    public static final RegistryObject<Item> CHORUS_JUICE = ITEMS.register("chorus_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), null));
    public static final RegistryObject<Item> MELON_JUICE = ITEMS.register("melon_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.LOW_DRINK), null));
    public static final RegistryObject<Item> COCONUT_MILK = ITEMS.register("coconut_milk",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), null));
    public static final RegistryObject<Item> CARBONATED_WATER = ITEMS.register("carbonated_water",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.LOW_DRINK), null));
    public static final RegistryObject<Item> PUMPKIN_JUICE = ITEMS.register("pumpkin_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), null));
    public static final RegistryObject<Item> CACTUS_JUICE = ITEMS.register("cactus_juice",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.POOR_DRINK), null));

    public static final RegistryObject<Item> BROWN_COCKTAIL_UMBRELLA = ITEMS.register("brown_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLACK_COCKTAIL_UMBRELLA = ITEMS.register("black_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GRAY_COCKTAIL_UMBRELLA = ITEMS.register("gray_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GRAY_COCKTAIL_UMBRELLA = ITEMS.register("light_gray_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_COCKTAIL_UMBRELLA = ITEMS.register("white_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIME_COCKTAIL_UMBRELLA = ITEMS.register("lime_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_COCKTAIL_UMBRELLA = ITEMS.register("green_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_COCKTAIL_UMBRELLA = ITEMS.register("cyan_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_COCKTAIL_UMBRELLA = ITEMS.register("light_blue_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_COCKTAIL_UMBRELLA = ITEMS.register("blue_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_COCKTAIL_UMBRELLA = ITEMS.register("purple_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_COCKTAIL_UMBRELLA = ITEMS.register("magenta_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PINK_COCKTAIL_UMBRELLA = ITEMS.register("pink_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_COCKTAIL_UMBRELLA = ITEMS.register("red_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_COCKTAIL_UMBRELLA = ITEMS.register("orange_cocktail_umbrella",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_COCKTAIL_UMBRELLA = ITEMS.register("yellow_cocktail_umbrella",
            () -> new Item(new Item.Properties()));

    public static final ArrayList<RegistryObject<Item>> COCKTAIL_UMBRELLAS = new ArrayList<>(List.of(
            BROWN_COCKTAIL_UMBRELLA, BLACK_COCKTAIL_UMBRELLA, GRAY_COCKTAIL_UMBRELLA, LIGHT_GRAY_COCKTAIL_UMBRELLA,
            WHITE_COCKTAIL_UMBRELLA, LIME_COCKTAIL_UMBRELLA, GREEN_COCKTAIL_UMBRELLA, CYAN_COCKTAIL_UMBRELLA,
            LIGHT_BLUE_COCKTAIL_UMBRELLA, BLUE_COCKTAIL_UMBRELLA, PURPLE_COCKTAIL_UMBRELLA, MAGENTA_COCKTAIL_UMBRELLA,
            PINK_COCKTAIL_UMBRELLA, RED_COCKTAIL_UMBRELLA, ORANGE_COCKTAIL_UMBRELLA, YELLOW_COCKTAIL_UMBRELLA
    ));

    public static final RegistryObject<Item> BROWN_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("brown_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLACK_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("black_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GRAY_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("gray_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_GRAY_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("light_gray_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("white_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIME_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("lime_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GREEN_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("green_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CYAN_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("cyan_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHT_BLUE_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("light_blue_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BLUE_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("blue_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("purple_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGENTA_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("magenta_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PINK_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("pink_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("red_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORANGE_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("orange_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_UMBRELLA_COCKTAIL_GLASS = ITEMS.register("yellow_umbrella_cocktail_glass",
            () -> new Item(new Item.Properties()));

    public static final ArrayList<RegistryObject<Item>> COCKTAIL_GLASSES = new ArrayList<>(List.of(
            BROWN_UMBRELLA_COCKTAIL_GLASS, BLACK_UMBRELLA_COCKTAIL_GLASS, GRAY_UMBRELLA_COCKTAIL_GLASS,
            LIGHT_GRAY_UMBRELLA_COCKTAIL_GLASS, WHITE_UMBRELLA_COCKTAIL_GLASS, LIME_UMBRELLA_COCKTAIL_GLASS,
            GREEN_UMBRELLA_COCKTAIL_GLASS, CYAN_UMBRELLA_COCKTAIL_GLASS, LIGHT_BLUE_UMBRELLA_COCKTAIL_GLASS,
            BLUE_UMBRELLA_COCKTAIL_GLASS, PURPLE_UMBRELLA_COCKTAIL_GLASS, MAGENTA_UMBRELLA_COCKTAIL_GLASS,
            PINK_UMBRELLA_COCKTAIL_GLASS, RED_UMBRELLA_COCKTAIL_GLASS, ORANGE_UMBRELLA_COCKTAIL_GLASS,
            YELLOW_UMBRELLA_COCKTAIL_GLASS
    ));

    public static final RegistryObject<Item> BROWN_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("brown_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), BROWN_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> BLACK_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("black_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), BLACK_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> GRAY_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("gray_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), GRAY_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> LIGHT_GRAY_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("light_gray_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), LIGHT_GRAY_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> WHITE_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("white_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), WHITE_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> LIME_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("lime_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), LIME_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> GREEN_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("green_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), GREEN_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> CYAN_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("cyan_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), CYAN_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> LIGHT_BLUE_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("light_blue_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), LIGHT_BLUE_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> BLUE_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("blue_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), BLUE_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> PURPLE_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("purple_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), PURPLE_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> MAGENTA_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("magenta_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), MAGENTA_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> PINK_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("pink_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), PINK_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> RED_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("red_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), RED_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> ORANGE_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("orange_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), ORANGE_COCKTAIL_UMBRELLA.get()));
    public static final RegistryObject<Item> YELLOW_UMBRELLA_COCONUT_COCKTAIL = ITEMS.register("yellow_umbrella_coconut_cocktail",
            () -> new DrinkItem(new Item.Properties().food(ModFoodProperties.NORMAL_DRINK), YELLOW_COCKTAIL_UMBRELLA.get()));

    public static final ArrayList<RegistryObject<Item>> COCONUT_COCKTAILS = new ArrayList<>(List.of(
            BROWN_UMBRELLA_COCONUT_COCKTAIL, BLACK_UMBRELLA_COCONUT_COCKTAIL, GRAY_UMBRELLA_COCONUT_COCKTAIL,
            LIGHT_GRAY_UMBRELLA_COCONUT_COCKTAIL, WHITE_UMBRELLA_COCONUT_COCKTAIL, LIME_UMBRELLA_COCONUT_COCKTAIL,
            GREEN_UMBRELLA_COCONUT_COCKTAIL, CYAN_UMBRELLA_COCONUT_COCKTAIL, LIGHT_BLUE_UMBRELLA_COCONUT_COCKTAIL,
            BLUE_UMBRELLA_COCONUT_COCKTAIL, PURPLE_UMBRELLA_COCONUT_COCKTAIL, MAGENTA_UMBRELLA_COCONUT_COCKTAIL,
            PINK_UMBRELLA_COCONUT_COCKTAIL, RED_UMBRELLA_COCONUT_COCKTAIL, ORANGE_UMBRELLA_COCONUT_COCKTAIL,
            YELLOW_UMBRELLA_COCONUT_COCKTAIL
    ));

    public static final ArrayList<RegistryObject<Item>> TEA_GLASSES =
            cupAndCocktailGlassesMaker("tea", ModFoodProperties.NORMAL_COMPLEX_DRINK);
    public static final Supplier<Item[]> tea_shaker_list =
            () -> TEA_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> TEA_SHAKERS =
            shakersMaker("tea", tea_shaker_list);

    public static final ArrayList<RegistryObject<Item>> PUMPKIN_SPICE_LATTE_GLASSES =
            cupAndCocktailGlassesMaker("pumpkin_spice_latte", ModFoodProperties.HIGH_COMPLEX_DRINK);
    public static final Supplier<Item[]> pumpkin_spice_latte_shaker_list =
            () -> PUMPKIN_SPICE_LATTE_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> PUMPKIN_SPICE_LATTE_SHAKERS =
            shakersMaker("pumpkin_spice_latte", pumpkin_spice_latte_shaker_list);

    public static final ArrayList<RegistryObject<Item>> CHOCOLATE_GLASSES =
            cupAndCocktailGlassesMaker("chocolate", ModFoodProperties.NORMAL_COMPLEX_DRINK);
    public static final Supplier<Item[]> chocolate_shaker_list =
            () -> CHOCOLATE_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> CHOCOLATE_SHAKERS =
            shakersMaker("chocolate", chocolate_shaker_list);

    public static final ArrayList<RegistryObject<Item>> MATCHA_TEA_LATTE_GLASSES =
            cupAndCocktailGlassesMaker("matcha_tea_latte", ModFoodProperties.HIGH_COMPLEX_DRINK);
    public static final Supplier<Item[]> matcha_tea_latte_shaker_list =
            () -> MATCHA_TEA_LATTE_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> MATCHA_TEA_LATTE_SHAKERS =
            shakersMaker("matcha_tea_latte", matcha_tea_latte_shaker_list);

    public static final ArrayList<RegistryObject<Item>> WATERMELON_ROSE_GLASSES =
            cocktailGlassesMaker("watermelon_rose", ModFoodProperties.NORMAL_DRINK);
    public static final Supplier<Item[]> watermelon_rose_shaker_list =
            () -> WATERMELON_ROSE_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> WATERMELON_ROSE_SHAKERS =
            shakersMaker("watermelon_rose", watermelon_rose_shaker_list);

    public static final ArrayList<RegistryObject<Item>> IL_SIRACUSANO_GLASSES =
            cocktailGlassesMaker("il_siracusano", ModFoodProperties.HIGH_COMPLEX_DRINK);
    public static final Supplier<Item[]> il_siracusano_shaker_list =
            () -> IL_SIRACUSANO_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> IL_SIRACUSANO_SHAKERS =
            shakersMaker("il_siracusano", il_siracusano_shaker_list);

    public static final ArrayList<RegistryObject<Item>> GUAYABA_COLADA_GLASSES =
            cocktailGlassesMaker("guayaba_colada", ModFoodProperties.NORMAL_COMPLEX_DRINK);
    public static final Supplier<Item[]> guayaba_colada_shaker_list =
            () -> GUAYABA_COLADA_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> GUAYABA_COLADA_SHAKERS =
            shakersMaker("guayaba_colada", guayaba_colada_shaker_list);

    public static final ArrayList<RegistryObject<Item>> GENDER_FLUID_GLASSES =
            cocktailGlassesMaker("gender_fluid", ModFoodProperties.GREAT_COMPLEX_DRINK);
    public static final Supplier<Item[]> gender_fluid_shaker_list =
            () -> GENDER_FLUID_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> GENDER_FLUID_SHAKERS =
            shakersMaker("gender_fluid", gender_fluid_shaker_list);

    public static final ArrayList<RegistryObject<Item>> BERRYLICIOUS_GLASSES =
            cocktailGlassesMaker("berrylicious", ModFoodProperties.NORMAL_COMPLEX_DRINK);
    public static final Supplier<Item[]> berrylicious_shaker_list =
            () -> BERRYLICIOUS_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> BERRYLICIOUS_SHAKERS =
            shakersMaker("berrylicious", berrylicious_shaker_list);

    public static final ArrayList<RegistryObject<Item>> AUREATE_SPRING_GLASSES =
            cocktailGlassesMaker("aureate_spring", ModFoodProperties.NORMAL_COMPLEX_DRINK);
    public static final Supplier<Item[]> aureate_spring_shaker_list =
            () -> AUREATE_SPRING_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> AUREATE_SPRING_SHAKERS =
            shakersMaker("aureate_spring", aureate_spring_shaker_list);

    public static final ArrayList<RegistryObject<Item>> LA_SIGNORA_GLASSES =
            cocktailGlassesMaker("la_signora", ModFoodProperties.HIGH_COMPLEX_DRINK);
    public static final Supplier<Item[]> la_signora_shaker_list =
            () -> LA_SIGNORA_GLASSES.stream().map(RegistryObject::get).toArray(Item[]::new);
    public static final ArrayList<RegistryObject<Item>> LA_SIGNORA_SHAKERS =
            shakersMaker("la_signora", la_signora_shaker_list);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
