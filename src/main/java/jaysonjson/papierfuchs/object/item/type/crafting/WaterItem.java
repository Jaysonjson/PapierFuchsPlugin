package jaysonjson.papierfuchs.object.item.type.crafting;


import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class WaterItem extends FuchsItem {


    private int amount;
    public WaterItem(String id, Material material, IItemUseType itemUseType) {
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
            if(tag.hasKey(ItemNBT.LIQUID_AMOUNT)) {
                amount = tag.getInt(ItemNBT.LIQUID_AMOUNT);
            }
        } else {
            amount = new Random().nextInt(75);
            amount += new Random().nextInt(25);
        }
        oItem.lore.add(ChatColor.GRAY + "" + amount + "ml");
        oItem.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        oItem.setItem(ChatColor.AQUA + "Wasser");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, true);
        if(!tag.hasKey(ItemNBT.LIQUID_AMOUNT)) {
            tag.setInt(ItemNBT.LIQUID_AMOUNT, amount);
        }
        return tag;
    }

}
