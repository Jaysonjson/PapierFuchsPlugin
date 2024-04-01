package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventorySize;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class AddEffectInventory extends FuchsInventory {

    public int effectSlot = 11;
    public int fuchsItemSlot = 29;
    public int resultSlot = 24;

    public AddEffectInventory(String id) {
        super(id);
    }

    @Override
    public void setContents() {
        fillWithFiller();
        //getInventory().setItem(effectSlot - 1, createStack(115));
        getInventory().setItem(effectSlot, null);
        //getInventory().setItem(fuchsItemSlot - 1, createStack(116));
        getInventory().setItem(fuchsItemSlot, null);
        //getInventory().setItem(resultSlot - 1, ItemList.slotRight.copy().createItem());
        getInventory().setItem(resultSlot, null);
    }

    public static ItemStack createStack(int md) {
        ItemStack itemStack = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(md);
        itemMeta.setDisplayName("");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            int slot = event.getSlot();
            if (slot != effectSlot && slot != fuchsItemSlot && slot != resultSlot) {
                event.setCancelled(true);
            }
            if(slot == resultSlot && getInventory().getItem(resultSlot) != null) {
                FuchsUtility.addItemOrDrop(getPlayer(), getInventory().getItem(resultSlot));
                /*event.getView().setCursor(getInventory().getItem(resultSlot).clone());*/
                getInventory().setItem(effectSlot, null);
                getInventory().setItem(fuchsItemSlot, null);
                getInventory().setItem(resultSlot, null);
            }
        } else {
            event.setCancelled(event.isShiftClick());
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public void create(Player player) {
        super.create(player);
        new BukkitRunnable() {
            @Override
            public void run() {
                if(FuchsUtility.isFuchsItem(getInventory().getItem(fuchsItemSlot)) && FuchsUtility.isFuchsItem(getInventory().getItem(effectSlot))) {
                    if(FuchsUtility.getFuchsItemFromNMS(getInventory().getItem(effectSlot)).isEffectBook()) {
                        if (getInventory().getItem(effectSlot) != null && getInventory().getItem(fuchsItemSlot) != null && getInventory().getItem(resultSlot) == null) {
                            ItemStack effectBook = getInventory().getItem(effectSlot);
                            FuchsMCItem mcItem = new FuchsMCItem(getInventory().getItem(fuchsItemSlot));
                            getInventory().setItem(resultSlot, FuchsUtility.getFuchsItemFromNMS(getInventory().getItem(fuchsItemSlot)).createItem(getPlayer(), FuchsUtility.applyEffectToItem(mcItem, effectBook, FuchsUtility.getEffectByID(new FuchsMCItem(effectBook).generalData().get().getContainedEffect()), getPlayer())));
                        }
                    }
                }
                if((getInventory().getItem(effectSlot) == null || getInventory().getItem(fuchsItemSlot) == null) && getInventory().getItem(resultSlot) != null) {
                    getInventory().setItem(resultSlot, null);
                }
            }
        }.runTaskTimer(PapierFuchs.INSTANCE, 0L, 5L);
    }

    @Override
    public InventorySize getSizeEnum() {
        return InventorySize.FORTY_FIVE;
    }
}
