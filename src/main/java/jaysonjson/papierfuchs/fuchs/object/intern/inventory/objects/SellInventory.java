package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects;


import jaysonjson.papierfuchs.fuchs.object.intern.currency.CurrencyList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventorySize;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SellInventory extends FuchsInventory {
    public SellInventory(String id) {
        super(id);
    }

    @Override
    public void setContents() {
        for (int i = 36; i < 45; i++) {
            getInventory().setItem(i, new ItemStack(Material.GLASS_PANE));
        }
        getInventory().setItem(42, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Verkaufspreis Berechnen"));
        getInventory().setItem(43, FuchsUtility.createInventoryStack(Material.PAPER, 1, "Verkaufspreis: " + FuchsUtility.formatFloat(FuchsUtility.countItemTradeValue(getInventory().getContents(), CurrencyList.FUCHSO.getID(), true)) + CurrencyList.FUCHSO.copy().getSymbol()));
        getInventory().setItem(44, FuchsUtility.createInventoryStack(Material.GREEN_WOOL, 1, "Verkaufen"));
    }

    @Override
    public InventorySize getSizeEnum() {
        return InventorySize.FORTY_FIVE;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
       if(FuchsUtility.isTopInventory(event)) {
            for (int i = 36; i < 45; i++) {
                if(event.getSlot() == i) {
                    event.setCancelled(true);
                }
            }
            if(clickedItem.hasItemMeta()) {
                String displayName = clickedItem.getItemMeta().getDisplayName();
                switch (displayName) {
                    case "Verkaufspreis Berechnen" -> setContents();
                    case "Verkaufen" -> {
                        FuchsUtility.addCurrency(CurrencyList.FUCHSO.copy(), FuchsUtility.countItemTradeValue(getInventory().getContents(), CurrencyList.FUCHSO.getID(), true), getPlayer());
                        for (ItemStack content : getInventory().getContents()) {
                            if(content != null) {
                                FuchsMCItem mcItem = new FuchsMCItem(content);
                                if (mcItem.currencyData().get().getSellValue() > 0) {
                                    content.setAmount(0);
                                }
                            }
                        }
                    }
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }
}
