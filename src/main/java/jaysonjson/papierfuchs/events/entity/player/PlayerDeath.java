package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        //Player player = event.getEntity();
        FuchsPlayer fuchsPlayer = References.data.getPlayer(event.getEntity().getUniqueId());
        fuchsPlayer.getHealth().health -= 2;
        fuchsPlayer.getPlayerSpecial().resetAlcohol();
        //DataHandler.savePlayer(fuchsPlayer);
        //Utility.RefreshHearts(event.getEntity(), player);
    }

}
