package jaysonjson.papierfuchs.object.item.interfaces;

import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IFuchsItem {

    ItemStack createItem(Player player, ItemStack stack);
    default ItemStack createItem() {
        return createItem(null, null);
    }

    default ItemStack createItem(Player player) {
        return createItem(player, null);
    }

    default ItemStack createItem(ItemStack itemStack) {
        return createItem(null, itemStack);
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
    String getLanguageString();
    boolean stackAble();
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
