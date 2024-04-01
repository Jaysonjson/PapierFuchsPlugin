package jaysonjson.papierfuchs.paper.events.item;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemSpecialData;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.kyori.adventure.text.Component;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemPickup implements Listener {

    @EventHandler
    public void itemPickupEvent(EntityPickupItemEvent event) {
        ItemStack itemStack = event.getItem().getItemStack();
        if (event.getEntity() instanceof Player player) {
            NBTTagCompound tag = FuchsUtility.getItemTag(FuchsUtility.createNMSCopy(itemStack));
            FuchsMCItem mcItem = new FuchsMCItem(player, itemStack);
            FuchsItemSpecialData specialData = mcItem.specialData().get();
            FuchsItemGeneralData generalData = mcItem.generalData().get();
            if (FuchsUtility.isFuchsItem(itemStack)) {
                FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
                assert fuchsItem != null;
                if(fuchsItem.isInventoryLimited()) {
                    if(FuchsUtility.countItemInInventory(player.getInventory(), fuchsItem.createItem(player)) >= fuchsItem.inventoryLimit()) {
                        event.setCancelled(true);
                        return;
                    }
                }
               // System.out.println("Item geändert -> Pickup");
                ItemStack item = fuchsItem.createItem(player, itemStack);
                item.setAmount(itemStack.getAmount());
                fuchsItem.onItemPickup(event);
                FuchsMCItem fuchsMCItem = new FuchsMCItem(fuchsItem, player, itemStack);
                if (!fuchsItem.staysOpen() && fuchsMCItem.getData().isOpen()) {
                    fuchsMCItem.getData().toggleOpen();
                }
                event.getItem().setItemStack(item);
                player.updateInventory();
                fuchsItem.onItemPickup(event);
            }
            if(!FuchsUtility.isFuchsItem(itemStack)) {
                if (FuchsUtility.isAbstractVanillaItem(itemStack)) {
                    //System.out.println("Vanilla Item geändert -> Pickup");
                    ItemStack item = FuchsUtility.getAbstractVanillaOverride(itemStack).createItem(player);
                    item.setAmount(itemStack.getAmount());
                    event.getItem().setItemStack(item);
                    player.updateInventory();
                }
            }
            for (FuchsRegistryObject<FuchsEffect> value : FuchsRegistries.effects.values()) {
                if(FuchsUtility.itemHasEffect(itemStack, value.copy())) {
                    value.copy().onItemPickup(event, mcItem);
                }
            }
            if(specialData.hasBounty()) {
                if(specialData.getBountyOwner().equals(player.getUniqueId())) {
                    player.sendActionBar(Component.text(ChatColor.RED + "Du kannst deinen eigenen Kopf nicht einsammeln!"));
                    event.setCancelled(true);
                }
            }
        }
    }
}


