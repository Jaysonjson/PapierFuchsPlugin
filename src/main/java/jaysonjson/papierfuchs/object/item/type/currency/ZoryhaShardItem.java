package jaysonjson.papierfuchs.object.item.type.currency;

import jaysonjson.papierfuchs.Utility;
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

public class ZoryhaShardItem extends FuchsItem {

    FuchsItemData fuchsItemData;
    public ZoryhaShardItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addToLore(ChatColor.GRAY + "" + fuchsItemData.currency_value + "¢");
        fuchsItemData.setItem(ChatColor.AQUA + "Zoryha Bruckstück");
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
        return CurrencyType.ZORYHA_SHARD.getId();
    }
}
