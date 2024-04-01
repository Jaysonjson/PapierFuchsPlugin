package jaysonjson.papierfuchs.paper.events.item;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemStack;

public class EnchantItem implements Listener {

    @EventHandler
    public void craftItemEvent(EnchantItemEvent event) {
        for (ItemStack content : event.getInventory().getContents()) {
            NBTTagCompound tag = FuchsUtility.getItemTag(content);
            if(tag.hasKey(ItemNBT.CAN_ENCHANT_MINECRAFT)) {
                if (!tag.getBoolean(ItemNBT.CAN_ENCHANT_MINECRAFT)) {
                    event.setCancelled(true);
                }
            }

            if(FuchsUtility.isFuchsItem(content)) {
                FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(content);
                fuchsItem.onItemEchant(event);
            }
        }
    }

}
