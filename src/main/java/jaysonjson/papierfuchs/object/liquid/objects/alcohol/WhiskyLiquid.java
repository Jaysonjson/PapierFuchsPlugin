package jaysonjson.papierfuchs.object.liquid.objects.alcohol;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class WhiskyLiquid extends FuchsLiquid {

    public WhiskyLiquid(String id) {
        super(id);
    }

    @Override
    public String getID() {
        return "whisky";
    }

    @Override
    public String getDisplayName() {
        return ChatColor.RESET + "" + ChatColor.GOLD + "Whisky";
    }

    @Override
    public int getCustomModelData() {
        return 5;
    }

    @Override
    public boolean drinkAble() {
        return true;
    }

    @Override
    public void drinkAction(Player player, ItemStack itemStack) {
        Utility.defaultAlcoholDrinkAction(player.getWorld(), player, itemStack, 7);
    }

}
