package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects;

import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.LanguageListInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class FuchsPlayerSettingsInventory extends FuchsInventory {

    String langName = "";
    public boolean showMsg = true;
    public FuchsPlayerSettingsInventory(String id) {
        super(id);
    }

    @Override
    public void setContents() {
        fillWithGlass();
        getInventory().setItem(10, FuchsUtility.createInventoryWoolColor(getFuchsPlayer().getSettings().isHideScoreboard(), "Scoreboard verstecken", 1));
        getInventory().setItem(11, FuchsUtility.createInventoryWoolColor(getFuchsPlayer().getSettings().isHideItemLore(), "Itemlore verstecken", 1));
        ItemStack languageStack = getFuchsPlayer().getLanguage().getItem().createItem(getPlayer());
        getInventory().setItem(40, languageStack);
        langName = languageStack.getItemMeta().getDisplayName();
        getInventory().setItem(49, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Schließen"));
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            if (clickedItem != null && clickedItem.hasItemMeta()) {
                String displayName = clickedItem.getItemMeta().getDisplayName();
                switch (displayName) {
                    case "Scoreboard verstecken" -> {
                        getFuchsPlayer().getSettings().setHideScoreboard(!getFuchsPlayer().getSettings().isHideScoreboard());
                        setContents();
                    }
                    case "Itemlore verstecken" -> {
                        getFuchsPlayer().getSettings().setHideItemLore(!getFuchsPlayer().getSettings().isHideItemLore());
                        setContents();
                        FuchsUtility.updateInventory(getPlayer());
                    }
                    case "Schließen" -> getPlayer().closeInventory();
                }
                if (displayName.equalsIgnoreCase(langName)) {
                    LanguageListInventory settingsLanguage = InventoryList.languageList.copy();
                    settingsLanguage.setFuchsPlayerSettings(this);
                    settingsLanguage.create(getPlayer());
                    showMsg = false;
                    settingsLanguage.openInventory();
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @EventHandler
    public void InventoryClose(InventoryCloseEvent event) {
        if(event.getInventory().equals(getInventory())) {
            References.data.savePlayer(getFuchsPlayer());
            if(showMsg) {
                getPlayer().sendMessage(Component.text("Manche änderungen treten erst in Kraft, sobald du rejoinst."));
            }
        }
    }
}
