package jaysonjson.papierfuchs.old.inventories.backpack;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.backpack.data.zBackPack;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
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
                    FuchsItemGeneralData generalData = new FuchsMCItem(clickedItem).generalData().get();
                    event.setCancelled(generalData.getProperty(FIGProperty.IS_BACKPACK));
                }
            }
        }
    }

    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, inventorySize, "Rucksack");
        UUID uuid = UUID.fromString(FuchsUtility.getItemTag(FuchsUtility.createNMSCopy(backPackItem)).getString(ItemNBT.ITEM_UUID));
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
