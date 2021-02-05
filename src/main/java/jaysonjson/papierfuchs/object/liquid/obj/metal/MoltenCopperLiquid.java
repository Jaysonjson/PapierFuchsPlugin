package jaysonjson.papierfuchs.object.liquid.obj.metal;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class MoltenCopperLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "moltenCopper";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GOLD + "Geschmolzenes Kupfer";
	}
	
	@Override
	public int getCustomModelData() {
		return 17;
	}

}
