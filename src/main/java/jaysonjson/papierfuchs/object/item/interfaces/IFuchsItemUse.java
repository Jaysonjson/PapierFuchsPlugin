package jaysonjson.papierfuchs.object.item.interfaces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface IFuchsItemUse {
    @Deprecated
    default void ability(PlayerInteractEvent event) {
        ability(event.getPlayer().getWorld(), event.getPlayer(), event.getItem());
    }
    @Deprecated
    void ability(World world, Player player, ItemStack itemStack);
    @Deprecated
    boolean isAbilityItem();
    int requiredIntelligence();
    void onItemRightClickAir();
    void onItemLeftClickAir();
    void onItemRightClickBlock();
    void onItemLeftClickBlock();
}
