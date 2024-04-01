package jaysonjson.papierfuchs.paper.events.item;

import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDespawn implements Listener {

    @EventHandler
    public void itemDespawnEvent(ItemDespawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        if(item.hasItemMeta()) {
            /*if(tag.hasKey(ItemNBT.IS_BACKPACK) && tag.hasKey(ItemNBT.ITEM_UUID)) {
                DataHandler.deleteBackPack(UUID.fromString(tag.getString(ItemNBT.ITEM_UUID)));
            }*/
            for (FuchsRegistryObject<FuchsEffect> effect : FuchsRegistries.effects.values()) {
                if(FuchsUtility.itemHasEffect(item, effect.copy())) {
                    effect.copy().onItemDespawn(event);
                }
            }
            if(FuchsUtility.isFuchsItem(item)) {
                FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(item);
                fuchsItem.onItemDespawn(event);
            }
        }
    }
}
