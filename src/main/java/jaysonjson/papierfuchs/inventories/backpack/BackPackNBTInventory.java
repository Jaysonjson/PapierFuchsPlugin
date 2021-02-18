package jaysonjson.papierfuchs.inventories.backpack;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class BackPackNBTInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public ItemStack backPackItem;
    int inventorySize;
    public BackPackNBTInventory(ItemStack backPack, int inventorySize) {
        this.backPackItem = backPack;
        this.inventorySize = inventorySize;
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory)) {
            ItemStack clickedItem = event.getCurrentItem();
            if(clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(clickedItem));
                    if (tag.hasKey(ItemNBT.IS_BACKPACK)) {
                        if (tag.getBoolean(ItemNBT.IS_BACKPACK)) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }
    
    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, inventorySize, "Rucksack");
        String contents = Utility.getItemTag(Utility.createNMSCopy(backPackItem)).getString(ItemNBT.INVENTORY_CONTENT);
        inventory.setContents(Utility.generateInventoryContent(contents));
        player.openInventory(inventory);
    }

    @EventHandler
    public void CloseInventory(InventoryCloseEvent event) {
        if(event.getInventory().equals(inventory)) {
            FuchsMCItem fuchsItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(backPackItem), backPackItem);
            fuchsItem.changeStringTag(ItemNBT.INVENTORY_CONTENT, Utility.createInventoryContent(event.getInventory().getContents()));
            event.getPlayer().getInventory().setItemInMainHand(fuchsItem.getItemStack());
        }
    }
}
