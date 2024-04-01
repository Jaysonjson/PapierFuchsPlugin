package jaysonjson.papierfuchs.paper.events.entity.player;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerSwapItem implements Listener {

    @EventHandler
    public void onEvent(PlayerSwapHandItemsEvent event) {
        ItemStack mainHandItem = event.getMainHandItem();
        ItemStack offHandItem = event.getOffHandItem();
        FuchsMCItem mainHandMCItem = new FuchsMCItem(mainHandItem);
        FuchsMCItem offHandMCItem = new FuchsMCItem(offHandItem);
        FuchsItemGeneralData mainItemData = mainHandMCItem.generalData().get();
        FuchsItemGeneralData offItemData = offHandMCItem.generalData().get();
        if((!mainItemData.getProperty(FIGProperty.CAN_MOVE) || !offItemData.getProperty(FIGProperty.CAN_MOVE))) {
              event.setCancelled(true);
        }
    }
}
