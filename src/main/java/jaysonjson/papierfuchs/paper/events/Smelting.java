package jaysonjson.papierfuchs.paper.events;

import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Smelting implements Listener {

    @EventHandler
    public void smeltingEvent(FurnaceSmeltEvent event) {
        Furnace furnace = (Furnace) event.getBlock().getState();
        Inventory inventory = furnace.getInventory();
        ItemStack result = event.getResult();
        int amount = 0;
        if(inventory.getItem(2) != null) {
            amount = inventory.getItem(2).getAmount();
        }
        if(FuchsUtility.isAbstractVanillaItem(result)) {
            ItemStack itemStack = FuchsUtility.getAbstractVanillaOverride(result).createItem(result);
            itemStack.setAmount(amount + 1);
            furnace.getInventory().setResult(itemStack);
            if(amount >= 2) {
                furnace.getInventory().getSmelting().subtract();
            }
        }
        if(FuchsUtility.isFuchsItem(result)) {
            ItemStack itemStack = FuchsUtility.getFuchsItemFromNMS(result).createItem(result);
            itemStack.setAmount(amount + 1);
            furnace.getInventory().setResult(itemStack);
            if(amount >= 2) {
                furnace.getInventory().getSmelting().subtract();
            }
        }
    }

}

