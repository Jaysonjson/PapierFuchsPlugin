package jaysonjson.papierfuchs.object.liquid;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IFuchsLiquid {
    boolean drinkAble();
    boolean drinkAction(Player player, ItemStack itemStack);
    Material getMinecraftEquivalent();
}
