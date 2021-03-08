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
    void onItemRightClickAir(PlayerInteractEvent event);
    void onItemLeftClickAir(PlayerInteractEvent event);
    void onItemRightClickBlock(PlayerInteractEvent event);
    void onItemLeftClickBlock(PlayerInteractEvent event);
    void onItemUse(PlayerInteractEvent event);
    void onItemRightClick(PlayerInteractEvent event);
    void onItemLeftClick(PlayerInteractEvent event);
    boolean isPlaceAble();
}
