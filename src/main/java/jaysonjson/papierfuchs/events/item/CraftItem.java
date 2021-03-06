package jaysonjson.papierfuchs.events.item;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItem implements Listener {

    @EventHandler
    public void craftItemEvent(PrepareItemCraftEvent event) {
        for (ItemStack content : event.getInventory().getContents()) {
            if(content != null && !content.isSimilar(event.getInventory().getResult())) {
                NBTTagCompound tag = Utility.getItemTag(content);
                if(tag.hasKey(ItemNBT.CAN_CRAFT_MINECRAFT)) {
                    if (!tag.getBoolean(ItemNBT.CAN_CRAFT_MINECRAFT)) {
                        event.getInventory().setResult(new ItemStack(Material.AIR));
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
