package jaysonjson.papierfuchs.object.item.interfaces;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IFuchsItem {

    ItemStack createItem(Player player, ItemStack stack);
    default ItemStack createItem() {
        return createItem(null, null);
    };
    default ItemStack createItem(Player player) {
        return createItem(player, null);
    };
    default ItemStack createItem(ItemStack itemStack) {
        return createItem(null, itemStack);
    };

    ItemStack update(Player player, ItemStack itemStack);
    IItemUseType getItemUse();
    @NotNull
    Material getMaterial();
    void setMaterial(Material material);
    NBTTagCompound getTag(NBTTagCompound tag);

}
