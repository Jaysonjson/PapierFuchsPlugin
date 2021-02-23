package jaysonjson.papierfuchs.object.item.objects.currency;


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

@Deprecated
public class GoldBarItem extends FuchsItem {

    double currencyValue;
    public GoldBarItem(String id, Material material, IItemUseType itemUseType) {
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
            currencyValue = 1250;
        }

        oItem.lore.add(ChatColor.GRAY + "" + currencyValue + "Φ");
        oItem.setItem(ChatColor.GOLD + "Gold Barren");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey(ItemNBT.HACKSILVER_AMOUNT)) {
            tag.setDouble(ItemNBT.HACKSILVER_AMOUNT, currencyValue);
        }
        return tag;
    }

}
