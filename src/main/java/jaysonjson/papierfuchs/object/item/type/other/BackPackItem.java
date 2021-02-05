package jaysonjson.papierfuchs.object.item.type.other;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.inventories.backpack.BackPackInventory;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BackPackItem extends FuchsItem {

    int inventorySize;
    String uuid;
    public BackPackItem(String id, Material material, IItemUseType itemUseType, int inventorySize) {
        super(id, material, itemUseType);
        this.inventorySize = inventorySize;
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
            if(tag.hasKey(ItemNBT.ITEM_UUID)) {
                uuid = tag.getString(ItemNBT.ITEM_UUID);
            }
        } else {
            uuid = generateUUID();
        }

        oItem.lore.add(inventorySize + " Slots");
        oItem.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + uuid + "«");
        oItem.setItem(ChatColor.RESET + "Rucksack");
        oItem.createNMSCopy();
        oItem.nmsCopy.setTag(getTag(oItem.getTagCompound()));
        oItem.item = CraftItemStack.asBukkitCopy(oItem.nmsCopy);
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.IS_BACKPACK, true);
        if(!tag.hasKey(ItemNBT.ITEM_UUID)) {
            tag.setString(ItemNBT.ITEM_UUID, uuid);
        }
        return tag;
    }

    private String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        if(DataHandler.backPackExists(UUID.fromString(uuid))) {
            uuid = generateUUID();
        }
        return uuid;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {
        BackPackInventory inventory = new BackPackInventory(itemStack, inventorySize);
        inventory.openInventory(player);
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }

}
