package jaysonjson.papierfuchs.object.liquid.obj;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class WaterLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "water";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.AQUA + "Wasser";
	}
	
	@Override
	public int getCustomModelData() {
		return 13;
	}

	@Override
	public Material getMinecraftEquivalent() {
		return Material.WATER;
	}
}
