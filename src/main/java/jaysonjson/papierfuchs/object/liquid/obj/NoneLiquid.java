package jaysonjson.papierfuchs.object.liquid.obj;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class NoneLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "none";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.RED + "Leer";
	}
	
	@Override
	public int getCustomModelData() {
		return 6;
	}

}
