package jaysonjson.papierfuchs.paper.events.entity.player;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.FICData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.FuchsItemCooldown;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsPacketHandler;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerLeave implements Listener {

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        References.data.savePlayer(player);
        FuchsPacketHandler.remove(player);
        ItemStack[] inventoryContents = player.getInventory().getContents();
        for (int i = 0; i < inventoryContents.length; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if(stack != null) {
                if (stack.getType() != Material.AIR) {
                    FuchsItemGeneralData generalData = new FuchsMCItem(stack).generalData().get();
                    if (generalData.hasPreCooldown()) {
                        FuchsItemCooldown<FICData<?>> cooldown = FuchsItemCooldown.getFromArray(generalData.getPreCooldownId());
                        if (cooldown != null) {
                            if (cooldown.getData().getRunnable() != null) {
                                cooldown.getData().getRunnable().cancel();
                                player.getInventory().setItem(i, cooldown.clearItem());
                            }
                        }
                    }
                }
            }
        }
    }

}
