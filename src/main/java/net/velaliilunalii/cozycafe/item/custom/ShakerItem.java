package net.velaliilunalii.cozycafe.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.velaliilunalii.cozycafe.item.ModItems;
import net.velaliilunalii.cozycafe.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class ShakerItem extends Item {
    private final Supplier<Item[]> l_drinks_supplier;
    private final int tier;

    public ShakerItem(Properties properties, int tier, Supplier<Item[]> l_drinks_supplier) {
        super(properties.stacksTo(1).durability(9 + tier*3));
        this.l_drinks_supplier = l_drinks_supplier;
        this.tier = tier;
    }


    public Item tierToShaker(int tier){
        Item itemToReturn = ModItems.WOODEN_SHAKER.get();
        itemToReturn = switch (tier) {
            case 1 -> ModItems.IRON_SHAKER.get();
            case 2 -> ModItems.GOLDEN_SHAKER.get();
            case 3 -> ModItems.DIAMOND_SHAKER.get();
            case 4 -> ModItems.NETHERITE_SHAKER.get();
            default -> itemToReturn;
        };
        return itemToReturn;
    }

    private int aux (Level level, Player player,ItemStack currentStack, ItemStack newStack, int needToRepair){
        int deltaCurrentHP = currentStack.getMaxDamage() - currentStack.getDamageValue();  //[1,9 + 3*tier]
        int repairValue = needToRepair;
        if (deltaCurrentHP < needToRepair) {
            repairValue = deltaCurrentHP;
        }
        newStack.setDamageValue(currentStack.getDamageValue() + repairValue);
        level.playSound(null, player.getOnPos(), ModSounds.POURING_SOUND.get(), SoundSource.PLAYERS);
        return needToRepair - repairValue;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            InteractionHand otherHand = InteractionHand.MAIN_HAND;
            if (hand == otherHand) {
                otherHand = InteractionHand.OFF_HAND;
            }
            ItemStack currentStack = player.getItemInHand(hand);
            ItemStack newStack = currentStack.copy();
            ItemStack otherStack = player.getItemInHand(otherHand);

            Set<Item> drinkItems = Set.of(l_drinks_supplier.get());
            boolean isOfL_drinks = drinkItems.contains(otherStack.getItem());
            Item[] l_drinks = l_drinks_supplier.get();

            if (isOfL_drinks && otherStack.getDamageValue() > 0){
                otherStack.setDamageValue(aux(level, player, currentStack, newStack, otherStack.getDamageValue()));
            } else {
                ItemStack newDrink = getNewDrink(otherStack, l_drinks);
                if (newDrink != null) {
                    otherStack.shrink(1);
                    newDrink.setDamageValue(aux(level, player, currentStack, newStack, 3));
                    player.addItem(newDrink);
                }
            }
            // If durability is now 0, replace with another item
            if (newStack.getDamageValue() >= newStack.getMaxDamage()) {
                player.setItemInHand(hand, new ItemStack(tierToShaker(tier)));
            }else{
                player.setItemInHand(hand, newStack);
            }
        }

        return super.use(level, player, hand);
    }


    private static @Nullable ItemStack getNewDrink(ItemStack otherStack, Item[] l_drinks) {
        ItemStack newDrink = null;
        if(otherStack.is(ModItems.COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[0]);
        }
        if(otherStack.is(ModItems.BROWN_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[1]);
        }
        if(otherStack.is(ModItems.BLACK_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[2]);
        }
        if(otherStack.is(ModItems.GRAY_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[3]);
        }
        if(otherStack.is(ModItems.LIGHT_GRAY_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[4]);
        }
        if(otherStack.is(ModItems.WHITE_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[5]);
        }
        if(otherStack.is(ModItems.LIME_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[6]);
        }
        if(otherStack.is(ModItems.GREEN_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[7]);
        }
        if(otherStack.is(ModItems.CYAN_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[8]);
        }
        if(otherStack.is(ModItems.LIGHT_BLUE_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[9]);
        }
        if(otherStack.is(ModItems.BLUE_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[10]);
        }
        if(otherStack.is(ModItems.PURPLE_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[11]);
        }
        if(otherStack.is(ModItems.MAGENTA_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[12]);
        }
        if(otherStack.is(ModItems.PINK_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[13]);
        }
        if(otherStack.is(ModItems.RED_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[14]);
        }
        if(otherStack.is(ModItems.ORANGE_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[15]);
        }
        if(otherStack.is(ModItems.YELLOW_UMBRELLA_COCKTAIL_GLASS.get())){
            newDrink = new ItemStack(l_drinks[16]);
        }
        if(otherStack.is(ModItems.GLASS_CUP.get())){
            newDrink = new ItemStack(l_drinks[17]);
        }
        return newDrink;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level plevel, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        pTooltipComponents.add(Component.translatable("tooltip.cozycafe.filled_shaker"));
        super.appendHoverText(pStack, plevel, pTooltipComponents, pTooltipFlag);
    }
}
