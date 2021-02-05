package jaysonjson.papierfuchs.events.entity;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.drop.obj.zMobDrop;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Random;

public class EntityDeath implements Listener {

    @EventHandler
    public void entityDeathEvent(EntityDeathEvent event) {
        Random random = new Random();
        EntityType type = event.getEntityType();
        for (zMobDrop mobDrop : References.drops.getMobDrops()) {
            if (type.equals(mobDrop.type)) {
                for (FuchsItem FuchsItem : mobDrop.itemDrops.keySet()) {
                    if (random.nextInt(mobDrop.itemDrops.get(FuchsItem)) == 1) {
                        Player player = null;
                        if (event.getEntity().getKiller() != null) {
                            player = event.getEntity().getKiller();
                        }
                        Utility.spawnCustomItem(player, FuchsItem, event.getEntity().getWorld(), event.getEntity().getLocation());
                    }
                }
            }
        }
    }

}
