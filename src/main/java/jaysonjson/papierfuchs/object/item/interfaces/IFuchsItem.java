package jaysonjson.papierfuchs.object.item.interfaces;

import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

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
    NBTTagCompound getTag(NBTTagCompound tag);

    default boolean isOre() {
        return false;
    }

    FuchsRarity getDefaultRarity();
    String getDefaultDisplayName();
    String getDisplayName();
    void setDisplayName(String displayName);
    String getLanguageString();
    boolean stackAble();
    IFuchsItem createCopy();
    default boolean hasCopy() {
        return createCopy() != null;
    }
    default boolean hasLanguageString() {
    	return getLanguageString() != "";
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
}
