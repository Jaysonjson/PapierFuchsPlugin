package jaysonjson.papierfuchs.paper.events.item;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropItem implements Listener {

    @EventHandler
    public void dropItemEvent(PlayerDropItemEvent event) {
        /*if(event.getEntity() instanceof Player player) {
            if (!FuchsUtility.canDropItem(player, player.getLocation(), player.getWorld())) {
                event.setCancelled(true);
            }
        }*/
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItemDrop().getItemStack();
        FuchsMCItem fuchsMCItem = new FuchsMCItem(itemStack);
        if(fuchsMCItem.generalData().has()) {
            FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
            if (!generalData.getProperty(FIGProperty.CAN_DROP)) {
                event.setCancelled(true);
            }
        }
        if(FuchsUtility.isFuchsItem(itemStack)) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
            fuchsItem.onPlayerItemDrop(event);
        }
        //Scoreboard.updateScoreboard(event.getPlayer());
    }


    @EventHandler
    public void dropItemEntityEvent(EntityDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if(FuchsUtility.isFuchsItem(itemStack)) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
            fuchsItem.onItemDrop(event);
            if(fuchsItem.canDrop()) {
                event.setCancelled(true);
            }
        }
        //Scoreboard.updateScoreboard(event.getPlayer());
    }
}
