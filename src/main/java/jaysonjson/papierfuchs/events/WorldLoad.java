package jaysonjson.papierfuchs.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class WorldLoad implements Listener {

    @EventHandler
    public void Event(WorldLoadEvent event) {
        /*Utility.openOpenedChests(event.getWorld());
        Utility.setEntityMetadatas(event.getWorld());
        System.out.println("Welt geladen");
         */
    }
}
