package jaysonjson.papierfuchs.inventories;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.item.ItemList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;

public class LanguageInventory implements Listener {
    @Nullable
    public Inventory inventory;
    public FuchsPlayer fuchsPlayer;
    private Player player;
    public LanguageInventory() {
        Bukkit.getPluginManager().registerEvents(this, PapierFuchs.INSTANCE);
    }

    public void openInventory(Player player) {
        this.player = player;
        inventory = Bukkit.createInventory(player, 54, "Sprache");
        fuchsPlayer = References.data.getPlayer(player.getUniqueId());
        setContents();
        player.openInventory(inventory);
    }

    public void setContents() {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        inventory.setItem(20, ItemList.GERMAN_FLAG.get().createItem());
        inventory.setItem(29, Utility.createInventoryStack(Material.PAPER, 1, "German"));
        inventory.setItem(24, ItemList.UK_FLAG.get().createItem());
        inventory.setItem(33, Utility.createInventoryStack(Material.PAPER, 1, "Englisch"));
        Utility.createInventoryBorder(inventory, Material.WHITE_STAINED_GLASS_PANE);
    }

    @EventHandler
    public void InventoryClick(InventoryClickEvent event) {
        if(event.getInventory().equals(inventory) && Utility.isTopInventory(event)) {
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem != null) {
                ItemMeta clickedItemMeta = clickedItem.getItemMeta();
                if (clickedItemMeta.getDisplayName().equalsIgnoreCase("Deutsch")) {
                    //fuchsPlayer.setLanguage(Languages.GERMAN);
                    Utility.updateInventory(player);
                } else if (clickedItemMeta.getDisplayName().equalsIgnoreCase("English")) {
                    //fuchsPlayer.setLanguage(Languages.ENGLISH);
                    Utility.updateInventory(player);
                }
                //DataHandler.savePlayer(fuchsPlayer);
                player.updateInventory();
                event.getWhoClicked().closeInventory();
            }
        }
    }
}
