package jaysonjson.papierfuchs.fuchs.object.intern.liquid;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IFuchsLiquid {
    boolean drinkAble();
    void drinkAction(Player player, ItemStack itemStack);
    Material getMinecraftEquivalent();
    FuchsItem getEmptyBottle();
    int getEmptyModelData();
}
