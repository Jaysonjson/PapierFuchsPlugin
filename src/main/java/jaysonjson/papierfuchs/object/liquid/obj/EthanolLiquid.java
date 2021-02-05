package jaysonjson.papierfuchs.object.liquid.obj;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class EthanolLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "ethanol";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.WHITE + "Ethanol";
	}
	
	@Override
	public int getCustomModelData() {
		return 16;
	}

}
