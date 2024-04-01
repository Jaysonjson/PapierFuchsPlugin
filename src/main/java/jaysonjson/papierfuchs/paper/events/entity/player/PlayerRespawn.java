package jaysonjson.papierfuchs.paper.events.entity.player;

import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event) {
        FuchsUtility.refreshHearts(event.getPlayer());
    }
}
