package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.inventories.crafting.general.GeneralCraftingInventory;
import jaysonjson.papierfuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEntity implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(Utility.isFuchsItem(item)) {
            FuchsItem fuchsItem = Utility.getFuchsItemFromNMS(item);
            if (fuchsItem != null) {
                fuchsItem.onEntityInteract(event);
            }
        }

        if(entity instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) entity;
            if(armorStand.hasMetadata(EntityMetaData.ARMORSTAND_GENERAL_CRAFTING)) {
                GeneralCraftingInventory generalCraftingInventory = new GeneralCraftingInventory();
                generalCraftingInventory.createPageData(player);
                generalCraftingInventory.openInventory(player, 0);
            }
        }
    }
}
