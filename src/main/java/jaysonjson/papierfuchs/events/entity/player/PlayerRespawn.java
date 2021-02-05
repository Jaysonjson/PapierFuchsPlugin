package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.Utility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event) {
        Utility.refreshHearts(event.getPlayer());
    }
}
