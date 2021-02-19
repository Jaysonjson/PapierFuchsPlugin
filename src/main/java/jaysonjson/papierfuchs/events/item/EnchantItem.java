package jaysonjson.papierfuchs.events.item;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantItem implements Listener {

    @EventHandler
    public void craftItemEvent(EnchantItemEvent event) {
        for (ItemStack content : event.getInventory().getContents()) {
            if(content != null) {
                NBTTagCompound tag = Utility.getItemTag(content);
                if(tag.hasKey(ItemNBT.CAN_ENCHANT_MINECRAFT)) {
                    if (!tag.getBoolean(ItemNBT.CAN_ENCHANT_MINECRAFT)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
