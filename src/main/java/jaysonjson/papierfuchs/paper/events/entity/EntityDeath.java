package jaysonjson.papierfuchs.paper.events.entity;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.FuchsMobDrop;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.ItemDropChance;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.zMobDrop;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
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
        LivingEntity entity = event.getEntity();
        References.data.getByteWorld(entity.getWorld()).getEntityMetadatas().removeIf(entity_metadatum -> entity_metadatum.uuid.equals(event.getEntity().getUniqueId()));
        if(event.getEntity().getKiller() != null) {
            Player player = entity.getKiller();
            if(player.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if(FuchsUtility.isFuchsItem(itemStack)) {
                    FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
                    fuchsItem.onEntityKill(event);
                }
            }
        }
        /*for (zMobDrop mobDrop : References.drops.getMobDrops()) {
            if (type.equals(mobDrop.getType()) || entity.hasMetadata(EntityMetaData.ENTITY_ID) && entity.getMetadata(EntityMetaData.ENTITY_ID).get(0).asString().equalsIgnoreCase(mobDrop.getId())) {
                /*for (FuchsItem FuchsItem : mobDrop.itemDrops.keySet()) {
                    if (random.nextInt(mobDrop.itemDrops.get(FuchsItem)) == 1) {
                        Player player = null;
                        if (event.getEntity().getKiller() != null) {
                            player = event.getEntity().getKiller();
                        }
                        Utility.spawnCustomItem(player, FuchsItem, event.getEntity().getWorld(), event.getEntity().getLocation());
                    }
                }
                for (ItemDropChance itemDropChance : mobDrop.itemDropChances) {
                    if(random.nextInt(itemDropChance.getChance()) == 1) {
                        Player player;
                        if(entity.getKiller() != null) {
                            player = entity.getKiller();
                            ItemStack itemStack = itemDropChance.getFuchsItem().createItem(player);
                            itemStack.setAmount(ThreadLocalRandom.current().nextInt(itemDropChance.min_amount, itemDropChance.max_amount + 1));
                            player.getWorld().dropItemNaturally(entity.getLocation(), itemStack);
                            //Utility.spawnCustomItem(player, itemDropChance.getFuchsItem(), player.getWorld(), player.getLocation());
                        }
                    }
                }
            }
        }*/
        for (FuchsMobDrop value : References.data.mobDrops.values()) {
            if(!value.getEntity().equals(EntityType.EGG)) {
                if(value.getEntity().equals(entity.getType())) {
                    for (zCraftingItem item : value.getItems()) {
                        if (item.getChance() > PapierFuchs.random.nextDouble() * 100) {
                            entity.getWorld().dropItemNaturally(entity.getLocation(), item.getItem());
                        }
                    }
                }
            }
        }
    }

}
