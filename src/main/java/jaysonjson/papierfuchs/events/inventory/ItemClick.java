package jaysonjson.papierfuchs.events.inventory;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemClick implements Listener {
    @EventHandler
    public void ClickEvent(InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        int clickedSlot = event.getRawSlot();
        if(Utility.isTopInventory(event)) {
            if(itemStack != null) {
                if (itemStack.hasItemMeta()) {
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
                    if (nmsItem.hasTag()) {
                        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
                        if (tag.hasKey(ItemNBT.CAN_MOVE)) {
                            if (!tag.getBoolean(ItemNBT.CAN_MOVE)) {
                                event.setCancelled(true);
                            }
                        }
                    }
                }

                if (event.getWhoClicked() instanceof Player) {
                    Player player = (Player) event.getWhoClicked();
                    NBTTagCompound tag = Utility.getItemTag(Utility.createNMSCopy(itemStack));
                    if (Utility.isFuchsItem(itemStack)) {
                        System.out.println("Item geändert -> Click");
                        ItemStack item = Utility.getFuchsItemFromNMS(itemStack).createItem(player, itemStack);
                        item.setAmount(itemStack.getAmount());
                        event.getInventory().setItem(clickedSlot, item);
                    }
                    if (!tag.hasKey(ItemNBT.ITEM_ID)) {
                    	if (Utility.isAbstractVanillaItem(itemStack)) {
                    		System.out.println("Vanilla Item geändert -> Click");
                        	ItemStack item = Utility.getAbstractVanillaOverride(itemStack).createItem(player);
                        	item.setAmount(itemStack.getAmount());
                        	event.getInventory().setItem(clickedSlot, item);
                        	player.updateInventory();
                    	}
                }
            }
        }
        }
    }
}
