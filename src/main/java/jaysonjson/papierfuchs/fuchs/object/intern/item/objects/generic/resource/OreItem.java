package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.resource;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
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
        fuchsItemData.setItem(ChatColor.DARK_GRAY + resource + " Erz");
        return fuchsItemData.item;
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
