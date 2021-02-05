package jaysonjson.papierfuchs.object.item.interfaces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IFuchsItemAbility {
    void ability(World world, Player player, ItemStack itemStack);
    boolean isAbilityItem();
    int requiredIntelligence();
}
