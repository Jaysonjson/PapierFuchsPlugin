package jaysonjson.papierfuchs.paper.events.item;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemProjectileData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

public class BowShootEvent implements Listener {

    @EventHandler
    public void onEvent(EntityShootBowEvent event) {
        ItemStack itemStack = event.getBow();
        if(FuchsUtility.isFuchsItem(itemStack)) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
            fuchsItem.onBowShoot(event);
        }
        ItemStack itemStack1 = event.getConsumable();
        //Keine FuchsProjektile schießen
        FuchsItemProjectileData projectileData = new FuchsMCItem(itemStack1).projectileData().get();
        if(projectileData.isProjectile()) {
            event.setCancelled(true);
        }
    }


}
