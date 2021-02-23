package jaysonjson.papierfuchs.object.item.objects.other;


import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ScrapItem extends FuchsItem {

    double currencyValue;
    public ScrapItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        boolean exists = true;
        if(stack == null) {
            stack = new ItemStack(getMaterial());
            exists = false;
        }
        FuchsItemData oItem = new FuchsItemData(this, player, stack);

        if(exists) {
            NBTTagCompound tag = getTag(Utility.getItemTag(Utility.createNMSCopy(stack)));
            if(tag.hasKey(ItemNBT.HACKSILVER_AMOUNT)) {
                currencyValue = tag.getDouble(ItemNBT.HACKSILVER_AMOUNT);
            }
        } else {
            currencyValue = 0.25;
        }

        oItem.lore.add(ChatColor.GRAY + "" + currencyValue + "Φ");
        oItem.setItem(ChatColor.RESET + "Schrott");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        if(!tag.hasKey(ItemNBT.HACKSILVER_AMOUNT)) {
            tag.setDouble(ItemNBT.HACKSILVER_AMOUNT, currencyValue);
        }
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

}
