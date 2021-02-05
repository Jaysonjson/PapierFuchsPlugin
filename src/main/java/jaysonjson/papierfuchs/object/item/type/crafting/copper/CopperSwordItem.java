package jaysonjson.papierfuchs.object.item.type.crafting.copper;


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
import org.bukkit.inventory.ItemStack;

public class CopperSwordItem extends FuchsItem {

    int durability;
    public CopperSwordItem(String id, Material material, IItemUseType itemUseType, int damageValue) {
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
            if(tag.hasKey(ItemNBT.ITEM_DURABILITY)) {
                durability = tag.getInt(ItemNBT.ITEM_DURABILITY);
            }
        } else {
            durability = getDurability();
        }

        oItem.lore.add(ChatColor.GRAY + "Ein Schwert aus Kupfer");
        oItem.lore.add(ChatColor.BLUE + "" + durability + "/" + getDurability());
        oItem.setItem(ChatColor.GOLD + "Kupfer Schwert");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        if(!tag.hasKey(ItemNBT.ITEM_DURABILITY)) {
            tag.setInt(ItemNBT.ITEM_DURABILITY, getDurability());
        }
        return tag;
    }

    public int getDurability() {
        return 435;
    }
}
