package jaysonjson.papierfuchs.events.block;

import jaysonjson.papierfuchs.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;


public class BlockBreak implements Listener {

    @EventHandler
    public void blockBreakEvent(BlockBreakEvent event) {
        if(!Utility.canBreakBlock(event.getPlayer(), event.getBlock().getLocation(), event.getBlock().getWorld())) {
            event.setCancelled(true);
        }
    }

}
