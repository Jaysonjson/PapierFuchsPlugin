package jaysonjson.papierfuchs.object.liquid.obj.metal;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class MoltenIronLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "moltenIron";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Geschmolzenes Eisen";
	}
	
	@Override
	public int getCustomModelData() {
		return 19;
	}

}
