package jaysonjson.papierfuchs.events.item;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemUse implements Listener {

    @EventHandler
    public void itemUseEvent(PlayerInteractEvent event) {
        if(event.getItem() != null) {

           /* if(Utility.isBannedItem(event.getItem())) {
                event.setCancelled(true);
            }*/
            if (event.getItem().getType() != Material.AIR) {
                if (Utility.isAbilityItemAll(event.getPlayer(), event.getItem())) {
                    FuchsItem FuchsItem = Utility.getFuchsItemFromNMS(event.getItem());
                    if (FuchsItem != null && FuchsItem.isAbilityItem()) {
                        //FuchsItem.ability(event.getPlayer().getWorld(), event.getPlayer(), event.getItem());
                        FuchsItem.ability(event);
                    }
                }
            }
        }
    }
}
