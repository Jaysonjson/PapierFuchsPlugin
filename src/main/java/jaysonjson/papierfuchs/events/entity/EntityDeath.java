package jaysonjson.papierfuchs.events.entity;

import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.drop.obj.ItemDropChance;
import jaysonjson.papierfuchs.data.drop.obj.zMobDrop;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EntityDeath implements Listener {

    @EventHandler
    public void entityDeathEvent(EntityDeathEvent event) {
        Random random = new Random();
        EntityType type = event.getEntityType();
        FuchsServer fuchsServer = DataHandler.loadServer();
        fuchsServer.ENTITY_METADATA.removeIf(entity_metadatum -> entity_metadatum.uuid.equals(event.getEntity().getUniqueId()));
        DataHandler.saveServer(fuchsServer);
        for (zMobDrop mobDrop : References.drops.getMobDrops()) {
            if (type.equals(mobDrop.type)) {
                /*for (FuchsItem FuchsItem : mobDrop.itemDrops.keySet()) {
                    if (random.nextInt(mobDrop.itemDrops.get(FuchsItem)) == 1) {
                        Player player = null;
                        if (event.getEntity().getKiller() != null) {
                            player = event.getEntity().getKiller();
                        }
                        Utility.spawnCustomItem(player, FuchsItem, event.getEntity().getWorld(), event.getEntity().getLocation());
                    }
                }*/
                for (ItemDropChance itemDropChance : mobDrop.itemDropChances) {
                    if(random.nextInt(itemDropChance.getChance()) == 1) {
                        Player player;
                        if(event.getEntity().getKiller() != null) {
                            player = event.getEntity().getKiller();
                            ItemStack itemStack = itemDropChance.getFuchsItem().createItem(player);
                            itemStack.setAmount(ThreadLocalRandom.current().nextInt(itemDropChance.min_amount, itemDropChance.max_amount + 1));
                            player.getWorld().dropItemNaturally(event.getEntity().getLocation(), itemStack);
                            //Utility.spawnCustomItem(player, itemDropChance.getFuchsItem(), player.getWorld(), player.getLocation());
                        }
                    }
                }
            }
        }
    }

}
