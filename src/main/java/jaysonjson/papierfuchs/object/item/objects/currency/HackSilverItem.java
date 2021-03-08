package jaysonjson.papierfuchs.object.item.objects.currency;


import jaysonjson.papierfuchs.object.currency.CurrencyList;
import jaysonjson.papierfuchs.object.item.CurrencyType;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HackSilverItem extends FuchsItem {

    FuchsItemData fuchsItemData;
    public HackSilverItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addToLore(ChatColor.GRAY + "" + fuchsItemData.currency_value + "Φ");
        fuchsItemData.setItem(ChatColor.GRAY + "Hacksilber");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey(ItemNBT.CURRENCY_AMOUNT)) {
            tag.setDouble(ItemNBT.CURRENCY_AMOUNT, fuchsItemData.currency_value);
        }
        tag.setString(ItemNBT.CURRENCY_TYPE, getCurrencyType());
        return tag;
    }

    @Override
    public double getCurrencyAmount() {
        return 1;
    }

    @Override
    public String getCurrencyType() {
        return CurrencyList.HACKSILVER.getID();
    }

}
