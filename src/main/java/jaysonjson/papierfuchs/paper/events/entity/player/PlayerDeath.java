package jaysonjson.papierfuchs.paper.events.entity.player;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemSpecialData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.FICData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.FuchsItemCooldown;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        FuchsPlayer fuchsPlayer = References.data.getPlayer(event.getEntity().getUniqueId());
        FuchsUtility.decreasePlayerHearts(player, fuchsPlayer, 1, false);
        fuchsPlayer.getSpecial().getAlcohol().reset();
        int i = 0;
        for (ItemStack drop : event.getDrops()) {
            NBTTagCompound tag = FuchsUtility.getItemTag(drop);
            FuchsMCItem fuchsMCItem = new FuchsMCItem(drop);
            FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
            if(tag.hasKey(ItemNBT.COSMETIC_SET_HAT) && tag.getBoolean(ItemNBT.COSMETIC_SET_HAT)) {
                drop.setAmount(0);
            }
            if(tag.hasKey(ItemNBT.IN_ACTION)) {
                if(tag.getBoolean(ItemNBT.IN_ACTION)) {
                    //drop.setAmount(0);
                }
            }
            if (generalData.hasPreCooldown()) {
                FuchsItemCooldown<FICData<?>> cooldown = FuchsItemCooldown.getFromArray(generalData.getPreCooldownId());
                //cooldown.onEnd();
                if(cooldown != null) {
                    if(cooldown.getData().getRunnable() != null) {
                        cooldown.getData().getRunnable().cancel();
                        event.getDrops().set(i, cooldown.clearItem());
                    }
                }
            }
            i++;
        }

        if(fuchsPlayer.getSpecial().hasBounty()) {
            FuchsMCItem mcItem = new FuchsMCItem(player, ItemList.bountyHead.copy().createItem(player, FuchsUtility.getPlayerHead(player)));
            FuchsItemSpecialData specialData = mcItem.specialData().get();
            specialData.setBountyOwner(player.getUniqueId());
            specialData.setBountyAmount(fuchsPlayer.getSpecial().getBounty());
            mcItem.specialData().set(specialData);
            player.getWorld().dropItemNaturally(player.getLocation(), ItemList.bountyHead.copy().createItem(player, mcItem.getOriginal()));
            fuchsPlayer.getSpecial().setBounty(0);
        }
        /*FuchsNPCData fuchsNPCData = NPC.createNPC(player, "Klick hier", player.getName(), true);
        fuchsNPCData.setCorpseInventory(FuchsUtility.createInventoryContent(event.getDrops().toArray(new ItemStack[0])));
        event.getDrops().clear();
        */
/*
        if(fuchsPlayer.getHealth().health > 0) {
            FuchsNPC fuchsNPC = NPC.createNPC(player, "Klick hier", player.getName(), true);
            fuchsNPC.setCorpseInventory(FuchsUtility.createInventoryContent(event.getDrops().toArray(new ItemStack[0])));
            event.getDrops().clear();
            /*player.teleport(player.getWorld().getSpawnLocation());
            fuchsPlayer.getHealth().health -= 2;
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(fuchsPlayer.getHealth().health);
            player.setHealth(fuchsPlayer.getHealth().health);
            //player.setHealth(fuchsPlayer.getHealth().getHealth());
            event.setCancelled(true);
        } else {
           //fuchsPlayer.getHealth().setHealth(fuchsPlayer.getHealth().getMaxHealth());
            FuchsNPC fuchsNPC = NPC.createNPC(player, "", player.getName(), true);
            FuchsUtility.addEntityMetadata(fuchsNPC.getEntity().getBukkitEntity(), EntityMetaData.CONTAINED_ITEMS, FuchsUtility.createInventoryContent(event.getDrops().toArray(new ItemStack[0])));
            event.getDrops().clear();
            event.getDrops().clear();
        }
        //DataHandler.savePlayer(fuchsPlayer);
        //Utility.RefreshHearts(event.getEntity(), player);*/
    }

}
