package jaysonjson.papierfuchs.object.item.objects.resource.tin.material;

import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemList;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import jaysonjson.papierfuchs.object.item.objects.generic.resource.IngotItem;
import jaysonjson.papierfuchs.object.item.objects.generic.resource.OreItem;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class TinIngotItem extends IngotItem {

    public TinIngotItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player, stack);
        oItem.setItem(ChatColor.GRAY + "Zinn");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int getCustomModelData() {
        return 26;
    }

    @Override
    public int getFireValue() {
        return 1;
    }

    @Override
    public int getMetalValue() {
        return 3;
    }

    @Nullable
    @Override
    public OreItem getOre() {
        return ItemList.TIN_ORE.get();
    }
}
