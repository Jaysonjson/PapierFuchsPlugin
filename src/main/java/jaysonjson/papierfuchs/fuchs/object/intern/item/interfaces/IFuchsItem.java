package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemPlaceType;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public interface IFuchsItem {


    /**
     * Erstellt ein ItemStack aus den Werten eines FuchsItems
     * @param player Spieler
     * @param stack Origineller ItemStack, welcher überschrieben werden soll
     * @return Neues Itemstack
     */
    ItemStack createItem(Player player, ItemStack stack);
    /**
     * Erstellt ein ItemStack aus den Werten eines FuchsItems
     * @return Neues Itemstack
     */
    default ItemStack createItem() {
        return createItem(null, null);
    }
    /**
     * Erstellt ein ItemStack aus den Werten eines FuchsItems
     * @param player Spieler
     * @return Neues Itemstack
     */
    default ItemStack createItem(Player player) {
        return createItem(player, null);
    }
    /**
     * Erstellt ein ItemStack aus den Werten eines FuchsItems
     * @param stack Origineller ItemStack, welcher überschrieben werden soll
     * @return Neues Itemstack
     */
    default ItemStack createItem(ItemStack stack) {
        return createItem(null, stack);
    }

    IItemUseType getItemUse();
    @NotNull
    Material getMaterial();
    @Deprecated
    NBTTagCompound getTag(NBTTagCompound tag);
    //ItemMeta setDefaultPersistentData(ItemMeta itemMeta);
    default void setDefaultPersistentData(FuchsMCItem fuchsMCItem, @Nullable Player player) {
    }
    default boolean isOre() {
        return false;
    }

    FuchsRarity getDefaultRarity();
    default boolean stackAble() {
        return true;
    }
    IFuchsItem createCopy();
    default boolean hasCopy() {
        return createCopy() != null;
    }
    default boolean isIngot() {
        return false;
    }
    default boolean isSword() {
        return false;
    }
    default boolean isMagicPowder() {
        return false;
    }
    default boolean isArmor() {
        return false;
    }
    default boolean isExtraRanged() {
        return getExtraRange() > 0;
    }
    default boolean isPetItem() { return false; }
    default boolean staysOpen() { return false; }
    default boolean isKneadItem() { return false; }
    default boolean isBow() {
        return Arrays.asList(getCategories()).contains(ItemCategoryList.bow.copy());
    }
    default int getExtraRange() {
        return 0;
    }
    default boolean canDrop() { return true; }
    default boolean showInItemList() {
        return true;
    }

    default String getOreDictionary() {
    	return "";
    }

    default boolean hasOreDictionary() {
        return !getOreDictionary().equals("");
    }

    default boolean canPutOnHead() {
    	return false;
    }
    default boolean isHat() {
        return canPutOnHead();
    }
    default boolean isMoneyBag() {
        return false;
    }
    default boolean isAdminTool() {
        return false;
    }
    default boolean canCraft() {
    	return false;
    }
    default boolean canCraftMinecraft() {
        return false;
    }
    default boolean canSmeltMinecraft() { return false; }
    default boolean showIdInLore() {
        return true;
    }
    default boolean isEffectBook() {
        return false;
    }
    default boolean isCategoryIcon() {
        return true;
    }

    default int inventoryLimit() {
        return 0;
    }

    default boolean isInventoryLimited() {
        return inventoryLimit() > 0;
    }

    default ItemPlaceType getItemPlaceType() {
        return ItemPlaceType.RIGHT_ARM;
    }

    @Nullable
    default FuchsRegistryObject<? extends FuchsBlock> asBlock() {
        return null;
    }

    /*
    default ItemCategoryInventory.Category[] getCategories() {
        return new ItemCategoryInventory.Category[] {};
    }*/

    default FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] {};
    }

}
