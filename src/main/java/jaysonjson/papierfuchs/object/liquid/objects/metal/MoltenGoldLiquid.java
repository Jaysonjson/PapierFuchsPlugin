package jaysonjson.papierfuchs.object.liquid.objects.metal;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class MoltenGoldLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "moltenGold";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GOLD + "Geschmolzenes Gold";
	}
	
	@Override
	public int getCustomModelData() {
		return 18;
	}

}
