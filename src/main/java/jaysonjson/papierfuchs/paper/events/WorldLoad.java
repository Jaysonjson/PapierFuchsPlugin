package jaysonjson.papierfuchs.paper.events;

import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsWorld;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class WorldLoad implements Listener {

    @EventHandler
    public void Event(WorldLoadEvent event) {
        if(!References.data.worlds.containsKey(event.getWorld().getUID())) {
            FuchsWorld fuchsWorld = new FuchsWorld();
            fuchsWorld.setWorldUuid(event.getWorld().getUID());
            References.data.saveWorld(fuchsWorld);
        }
    }


}
