package jaysonjson.papierfuchs.fuchs.object.intern.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IFuchsInventory {
    default void openInventory(int page) {

    }
    default void openInventory() {
        openInventory(0);
    }
    void setContents();
    Inventory getInventory();
    void create(Player player);
    default InventorySize getSizeEnum() {
        return InventorySize.FIFTY_FOUR;
    }

    default InventorySize getSizeFromInt(int numb) {
        InventorySize size = InventorySize.NINE;
        if(numb > 9) {
            size = InventorySize.EIGHTEEN;
        }
        if(numb > 18) {
            size = InventorySize.TWENTY_SEVEN;
        }
        if(numb > 27) {
            size = InventorySize.THIRTY_SIX;
        }
        if(numb > 36) {
            size = InventorySize.FORTY_FIVE;
        }
        if(numb > 45) {
            size = InventorySize.FIFTY_FOUR;
        }
        return size;
    }

    default void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {

    }
}
