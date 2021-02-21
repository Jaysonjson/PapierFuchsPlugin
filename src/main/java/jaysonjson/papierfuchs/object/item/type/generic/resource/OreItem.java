package jaysonjson.papierfuchs.object.item.type.generic.resource;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OreItem extends FuchsItem {

    String resource;
    int cmd;

    public OreItem(String id, String resource, int cmd, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
        this.resource = resource;
        this.cmd = cmd;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addToLore(ChatColor.GRAY + "Ein Erz aus " + resource);
        fuchsItemData.setItem(ChatColor.DARK_GRAY.toString() + resource + " Erz");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int getCustomModelData() {
        return cmd;
    }

    @Override
    public boolean hasCustomModelData() {
        return getCustomModelData() != -1;
    }

    @Override
    public boolean isOre() {
        return true;
    }
}
