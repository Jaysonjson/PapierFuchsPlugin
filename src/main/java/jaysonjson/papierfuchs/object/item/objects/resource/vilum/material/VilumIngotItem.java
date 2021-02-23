package jaysonjson.papierfuchs.object.item.objects.resource.vilum.material;


import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VilumIngotItem extends FuchsItem {

    public VilumIngotItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player, stack);
        oItem.setItem(ChatColor.LIGHT_PURPLE + "Vilum");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int getEarthValue() {
        return 2;
    }

    @Override
    public int getWaterValue() {
        return 6;
    }

    @Override
    public int getCustomModelData() {
        return 20;
    }
}
