package jaysonjson.papierfuchs.object.item.interfaces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface IFuchsItemAbility {
    default void ability(PlayerInteractEvent event) {
        ability(event.getPlayer().getWorld(), event.getPlayer(), event.getItem());
    }
    void ability(World world, Player player, ItemStack itemStack);
    boolean isAbilityItem();
    int requiredIntelligence();
}
