package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class PlayerSleep implements Listener {

    @EventHandler
    public void playerSleepEvent(PlayerBedLeaveEvent event) {
        FuchsPlayer fuchsPlayer = References.data.getPlayer(event.getPlayer().getUniqueId());
        if(fuchsPlayer.getPlayerSpecial().getAlcohol() > 0) {
            fuchsPlayer.getPlayerSpecial().alcohol /= 2;
            //DataHandler.savePlayer(fuchsPlayer);
        }

    }
}
