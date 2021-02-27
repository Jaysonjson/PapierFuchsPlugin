package jaysonjson.papierfuchs.events.entity.player;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.References;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.inventories.crafting.general.GeneralCraftingInventory;
import jaysonjson.papierfuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEntity implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(Utility.isFuchsItem(item)) {
            FuchsItem fuchsItem = Utility.getFuchsItemFromNMS(item);
            if (fuchsItem != null) {
                fuchsItem.onEntityInteract(event);
            }
        }

        if(entity instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) entity;
            if(armorStand.hasMetadata(EntityMetaData.ARMORSTAND_GENERAL_CRAFTING)) {
                GeneralCraftingInventory generalCraftingInventory = new GeneralCraftingInventory();
                generalCraftingInventory.createPageData(player);
                generalCraftingInventory.openInventory(player, 0);
            }

            if(player.isSneaking()) {
                if(armorStand.hasMetadata(EntityMetaData.CONTAINED_ITEM)) {
                    String itemID = entity.getMetadata(EntityMetaData.CONTAINED_ITEM).get(0).asString();
                    if(Utility.itemIDExists(itemID)) {
                        entity.getWorld().dropItemNaturally(entity.getLocation(), Utility.getFuchsItemByID(itemID).createItem(event.getPlayer()));
                    } else {
                        ItemStack itemStack = Utility.generateItemStack(itemID);
                        if(itemStack.getType() != Material.AIR) {
                            entity.getWorld().dropItemNaturally(entity.getLocation(), Utility.generateItemStack(itemID));
                        }
                    }
                    References.data.server.ENTITY_METADATA.removeIf(entity_metadatum -> entity_metadatum.uuid.equals(entity.getUniqueId()) && entity_metadatum.key.equals(EntityMetaData.CONTAINED_ITEM));
                    entity.removeMetadata(EntityMetaData.CONTAINED_ITEM, PapierFuchs.INSTANCE);
                    entity.remove();
                }
            }

            if(armorStand.hasMetadata(EntityMetaData.ARMORSTAND_DONT_REMOVE_ITEM_ON_RIGHTCLICK)) {
                event.setCancelled(true);
            }
        }
    }
}
