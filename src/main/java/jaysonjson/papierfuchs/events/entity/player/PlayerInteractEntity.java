package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEntity implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if(player.getActiveItem() != null) {
            ItemStack item = player.getActiveItem();
            FuchsItem fuchsItem = Utility.getFuchsItemFromNMS(item);
            if (fuchsItem != null) {
                fuchsItem.onEntityInteract(event);
            }
        }
    }
}
