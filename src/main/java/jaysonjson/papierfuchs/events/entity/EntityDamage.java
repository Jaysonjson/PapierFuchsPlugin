package jaysonjson.papierfuchs.events.entity;

import jaysonjson.papierfuchs.Constant;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamage implements Listener {
    @EventHandler
    public void entityDamageEvent(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        if(entity.hasMetadata(EntityMetaData.HIT_ABLE)) {
            boolean hitAble = entity.getMetadata(EntityMetaData.HIT_ABLE).get(0).asBoolean();
            if(!hitAble) {
                event.setCancelled(true);
            }
        }
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
            event.setDamage(event.getDamage() + (fuchsPlayer.getStats().getStrength() * Constant.DAMAGE_MODIFIER));
            if(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if(Utility.isFuchsItem(itemStack)) {
                    FuchsItem fuchsItem = Utility.getFuchsItemFromNMS(itemStack);
                    event.setDamage(event.getFinalDamage() + fuchsItem.getToolDamage());
                    player.getInventory().setItemInMainHand(Utility.damageFuchsItem(player, fuchsItem, itemStack));
                }
            }
        }
    }
}

