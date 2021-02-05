package jaysonjson.papierfuchs.inventories.backpack;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.backpack.data.zBackPack;
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
import java.util.UUID;


public class BackPackInventory implements Listener {

    @Nullable
    private Inventory inventory = null;
    public zBackPack backPack;
    public ItemStack backPackItem;
    int inventorySize;
    public BackPackInventory(ItemStack backPack, int inventorySize) {
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
        UUID uuid = UUID.fromString(Utility.getItemTag(Utility.createNMSCopy(backPackItem)).getString(ItemNBT.ITEM_UUID));
        //Vielleicht als Item NBT Tag speichern?
        backPack = DataHandler.loadBackPack(uuid);
        inventory.setContents(backPack.getItemStacks());
        player.openInventory(inventory);
    }

    @EventHandler
    public void CloseInventory(InventoryCloseEvent event) {
        if(event.getInventory().equals(inventory)) {
            backPack.setItemStacks(inventory.getContents());
            DataHandler.saveBackPack(backPack);
        }
    }
}
