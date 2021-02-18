package jaysonjson.papierfuchs.object.item.type.crafting.copper;


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
import java.util.Random;

public class CopperIngotItem extends FuchsItem {

    private int amount;
    public CopperIngotItem(String id, Material material, IItemUseType itemUseType) {
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
            if(tag.hasKey(ItemNBT.ITEM_AMOUNT)) {
                amount = tag.getInt(ItemNBT.ITEM_AMOUNT);
            }
        } else {
            amount = new Random().nextInt(400);
            amount += new Random().nextInt(100);
        }

        oItem.lore.add(ChatColor.GRAY + "" + amount + "g");
        oItem.setItem(ChatColor.GOLD + "Kupfer");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey(ItemNBT.ITEM_AMOUNT)) {
            tag.setInt(ItemNBT.ITEM_AMOUNT, amount);
        }
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
        return 1;
    }
}
