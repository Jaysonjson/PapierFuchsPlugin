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
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BackPackItem extends FuchsItem {

    int inventorySize;
    FuchsItemData fuchsItemData;
    public BackPackItem(String id, Material material, IItemUseType itemUseType, int inventorySize) {
        super(id, material, itemUseType);
        this.inventorySize = inventorySize;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        if(fuchsItemData.uuid.equalsIgnoreCase("")) {
            fuchsItemData.uuid = generateUUID();
        }

        fuchsItemData.lore.add(inventorySize + " Slots");
        fuchsItemData.lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + fuchsItemData.uuid + "«");
        fuchsItemData.setItem(ChatColor.RESET + "Rucksack");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.IS_BACKPACK, true);
        if(!tag.hasKey(ItemNBT.ITEM_UUID)) {
            tag.setString(ItemNBT.ITEM_UUID, fuchsItemData.uuid);
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
    public void onItemUse(PlayerInteractEvent event) {
        BackPackInventory inventory = new BackPackInventory(event.getItem(), inventorySize);
        inventory.openInventory(event.getPlayer());
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }

}
