package jaysonjson.papierfuchs.object.liquid.obj.alcohol;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BeerLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "beer";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GOLD + "Bier";
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
	public boolean drinkAction(Player player, ItemStack itemStack) {
		return Utility.defaultAlcoholDrinkAction(player.getWorld(), player, itemStack, 15);
	}
}
