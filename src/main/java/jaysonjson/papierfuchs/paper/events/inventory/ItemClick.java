package jaysonjson.papierfuchs.paper.events.inventory;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ItemClick implements Listener {
    @EventHandler
    public void ClickEvent(InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        int clickedSlot = event.getRawSlot();
        NBTTagCompound tag = FuchsUtility.getItemTag(itemStack);
        FuchsMCItem fuchsMCItem = new FuchsMCItem(itemStack);
        FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
        if (!generalData.getProperty(FIGProperty.CAN_MOVE)) {
            event.setCancelled(true);
        }
        if(!event.getInventory().getType().getDefaultTitle().equalsIgnoreCase("Crafting")) {
            if(FuchsUtility.isTopInventory(event)) {
                if (itemStack != null) {
                    if (!generalData.getProperty(FIGProperty.SHOULD_NOT_UPDATE)) {
                        if (event.getWhoClicked() instanceof Player player) {
                            if (itemStack.hasItemMeta()) {
                                if (FuchsUtility.isFuchsItem(itemStack)) {
                                    //System.out.println("Item geändert -> Click");
                                    FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
                                    ItemStack item = fuchsItem.createItem(player, itemStack);
                                    item.setAmount(itemStack.getAmount());
                                    event.getInventory().setItem(clickedSlot, item);
                                }
                                if (!FuchsUtility.itemHasFuchsID(itemStack)) {
                                    if (FuchsUtility.isAbstractVanillaItem(itemStack)) {
                                        //System.out.println("Vanilla Item geändert -> Click");
                                        ItemStack item = FuchsUtility.getAbstractVanillaOverride(itemStack).createItem(player);
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
        }
        if(itemStack != null) {
            if (tag.hasKey(ItemNBT.COSMETIC_SET_HAT) && tag.getBoolean(ItemNBT.COSMETIC_SET_HAT)) {
                itemStack.setAmount(0);
            }
        }
        cancelInventoryLimit(event, event.getRawSlot(), itemStack, event.getView());
    }

    @EventHandler
    public static void openEvent(InventoryOpenEvent event) {
        if(PapierFuchs.INSTANCE.getServer().getTPS()[0] > 19.5) {
            FuchsUtility.updateInventory(event.getInventory(), (Player) event.getPlayer());
        }
    }

    @EventHandler
    public static void closeEvent(InventoryCloseEvent event) {
        if(PapierFuchs.INSTANCE.getServer().getTPS()[0] > 19.5) {
            FuchsUtility.updateInventory((Player) event.getPlayer());
        }
    }

    @EventHandler
    public static void pickupItem(InventoryPickupItemEvent event) {
        if (FuchsUtility.isFuchsItem(event.getItem().getItemStack())) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(event.getItem().getItemStack());
            assert fuchsItem != null;
            if (fuchsItem.isInventoryLimited()) {
                if (FuchsUtility.countItemInInventory(event.getInventory(), fuchsItem.createItem()) >= fuchsItem.inventoryLimit()) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public static void moveItem(InventoryMoveItemEvent event) {
        if (FuchsUtility.isFuchsItem(event.getItem())) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(event.getItem());
            assert fuchsItem != null;
            if (fuchsItem.isInventoryLimited()) {
                if (FuchsUtility.countItemInInventory(event.getDestination(), fuchsItem.createItem()) >= fuchsItem.inventoryLimit()) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler
    public static void itemDrag(InventoryDragEvent event) {
        cancelInventoryLimit(event, (Integer) event.getRawSlots().toArray()[0], event.getCursor(), event.getView());
    }

    public static void cancelInventoryLimit(Cancellable cancellable, int rawSlot, ItemStack itemStack, InventoryView view) {
        if(FuchsUtility.isFuchsItem(itemStack)) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
            assert fuchsItem != null;
            if (fuchsItem.isInventoryLimited()) {
                if (FuchsUtility.isTopInventory(rawSlot, view.getTopInventory().getSize())) {
                    if (FuchsUtility.countItemInInventory(view.getBottomInventory(), fuchsItem.createItem()) >= fuchsItem.inventoryLimit()) {
                        cancellable.setCancelled(true);
                        return;
                    }
                } else {
                    if (FuchsUtility.countItemInInventory(view.getTopInventory(), fuchsItem.createItem()) >= fuchsItem.inventoryLimit()) {
                        cancellable.setCancelled(true);
                        return;
                    }
                }
            }
        }
    }

}
