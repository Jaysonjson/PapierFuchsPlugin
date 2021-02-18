package jaysonjson.papierfuchs.events.item;

import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItem implements Listener {

    @EventHandler
    public void craftItemEvent(CraftItemEvent event) {
        for (ItemStack content : event.getInventory().getContents()) {
            if(content != null && !content.isSimilar(event.getInventory().getResult())) {
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(content);
                if(nmsItem.hasTag()) {
                    NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                    if(tag.hasKey(ItemNBT.CAN_CRAFT_MINECRAFT)) {
                        if (!tag.getBoolean(ItemNBT.CAN_CRAFT_MINECRAFT)) {
                            event.setCancelled(true);
                        }
                    }
                }
                /*for (BannedItems value : BannedItems.values()) {
                    if(value.getMaterial().equals(content.getType()) || value.getMaterial().equals(event.getRecipe().getResult().getType())) {
                        event.setCancelled(true);
                    }
                }*/
            }
        }
    }

}
