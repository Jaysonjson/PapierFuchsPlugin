package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.References;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {

    @EventHandler
    public void PlayerJoinEvent(PlayerQuitEvent event) {
        References.data.savePlayer(event.getPlayer());
    }

}
