package jaysonjson.papierfuchs.events.block;

import jaysonjson.papierfuchs.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlace implements Listener {

    @EventHandler
    public void blockPlaceEvent(BlockPlaceEvent event) {
        if(!Utility.canPlaceBlock(event.getPlayer(), event.getBlock().getLocation(), event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

}
