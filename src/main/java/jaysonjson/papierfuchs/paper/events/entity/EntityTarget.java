package jaysonjson.papierfuchs.paper.events.entity;

import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.UUID;

public class EntityTarget implements Listener {

    @EventHandler
    public void EntitySpawn(EntityTargetEvent event) {
        Entity entity = event.getEntity();
        if(entity.hasMetadata(EntityMetaData.DONT_TARGET_ENTITY)) {
            if(event.getTarget() != null) {
                UUID target = UUID.fromString(entity.getMetadata(EntityMetaData.DONT_TARGET_ENTITY).get(0).asString());
                if(event.getTarget().getUniqueId().equals(target)) {
                    event.setCancelled(true);
                }
            }
        }
        if(entity.hasMetadata(EntityMetaData.SOUL_OF_CORRUPT) && event.getTarget() != null && event.getTarget().hasMetadata(EntityMetaData.SOUL_OF_CORRUPT)) {
            if(entity.getMetadata(EntityMetaData.SOUL_OF_CORRUPT).get(0).asString().equalsIgnoreCase(event.getTarget().getMetadata(EntityMetaData.SOUL_OF_CORRUPT).get(0).asString())) {
                event.setCancelled(true);
            }
        }
    }

}
