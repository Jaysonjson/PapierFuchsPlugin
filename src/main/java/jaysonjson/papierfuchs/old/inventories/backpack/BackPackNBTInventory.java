package jaysonjson.papierfuchs.old.inventories.backpack;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
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
    FuchsItem fuchsItem;
    public BackPackNBTInventory(ItemStack backPack, int inventorySize, FuchsItem fuchsItem) {
        this.backPackItem = backPack;
        this.inventorySize = inventorySize;
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
        this.fuchsItem = fuchsItem;
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory)) {
            ItemStack clickedItem = event.getCurrentItem();
            if(clickedItem != null) {
                if (clickedItem.hasItemMeta()) {
                    FuchsItemGeneralData generalData = new FuchsMCItem(clickedItem).generalData().get();
                    event.setCancelled(generalData.getProperty(FIGProperty.IS_BACKPACK));
                    if(fuchsItem.isMoneyBag()) {
                        FuchsMCItem fuchsMCItem = new FuchsMCItem((Player) event.getWhoClicked(), clickedItem);
                        if(!fuchsMCItem.currencyData().get().hasCurrency() && !fuchsMCItem.currencyData().get().isCurrencyEmpty()) {
                            event.setCancelled(true);
                        }
                    }
                } else
                if(fuchsItem.isMoneyBag()) {
                    event.setCancelled(true);
                }
            }
        }
    }
    
    public void openInventory(Player player) {
        inventory = Bukkit.createInventory(player, inventorySize, "Rucksack");
        inventory.setContents(FuchsUtility.generateInventoryContent(new FuchsMCItem(backPackItem).generalData().get().getInventoryContent()));
        player.openInventory(inventory);
    }

    @EventHandler
    public void CloseInventory(InventoryCloseEvent event) {
        if(event.getInventory().equals(inventory)) {
            FuchsMCItem fuchsItem = new FuchsMCItem(FuchsUtility.getFuchsItemFromNMS(backPackItem), (Player) event.getPlayer(), backPackItem);
            FuchsItemGeneralData generalData = fuchsItem.generalData().get();
            generalData.setInventoryContent(FuchsUtility.createInventoryContent(event.getInventory().getContents()));
            fuchsItem.generalData().set(generalData);
            event.getPlayer().getInventory().setItemInMainHand(fuchsItem.getItemStack());
        }
    }
}
