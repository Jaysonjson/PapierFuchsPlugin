package jaysonjson.papierfuchs.paper.events.entity.player;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEat implements Listener {

    @EventHandler
    public void onEvent(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        if(FuchsUtility.isFuchsItem(itemStack)) {
            FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(itemStack);
            if (fuchsItem != null && fuchsItem.isFood()) {
                player.setFoodLevel(player.getFoodLevel() - fuchsItem.getFoodLevelFromMaterial()); //Kartoffel
                player.setFoodLevel(player.getFoodLevel() + fuchsItem.getFoodLevel());
                //itemStack.setAmount(itemStack.getAmount() - 1);
                //event.setReplacement(itemStack);
                //event.setCancelled(true);
            }
        }
    }

}
