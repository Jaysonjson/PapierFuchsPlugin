package jaysonjson.papierfuchs.events.item;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class ItemDespawn implements Listener {
    @EventHandler
    public void itemDespawnEvent(ItemDespawnEvent event) {
        ItemStack item = event.getEntity().getItemStack();
        if(item.hasItemMeta()) {
            NBTTagCompound tag = Utility.getItemTag(item);
            if(tag.hasKey(ItemNBT.IS_BACKPACK) && tag.hasKey(ItemNBT.ITEM_UUID)) {
                DataHandler.deleteBackPack(UUID.fromString(tag.getString(ItemNBT.ITEM_UUID)));
            }
        }
    }
}
