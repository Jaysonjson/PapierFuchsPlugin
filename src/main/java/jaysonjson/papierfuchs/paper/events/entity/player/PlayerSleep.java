package jaysonjson.papierfuchs.paper.events.entity.player;

import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class PlayerSleep implements Listener {

    @EventHandler
    public void playerSleepEvent(PlayerBedLeaveEvent event) {
        FuchsPlayer fuchsPlayer = References.data.getPlayer(event.getPlayer().getUniqueId());
        float alcohol = fuchsPlayer.getSpecial().getAlcohol().getCurrent();
        if(alcohol > 0) {
            fuchsPlayer.getSpecial().getAlcohol().setCurrent(alcohol  / 2);
            //DataHandler.savePlayer(fuchsPlayer);
        }

    }
}
