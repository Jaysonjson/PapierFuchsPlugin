package jaysonjson.papierfuchs.object.liquid.objects;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class LavaLiquid extends FuchsLiquid {

	@Override
	public String getID() {
		return "lava";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.DARK_RED + "Lava";
	}
	
	@Override
	public int getCustomModelData() {
		return 14;
	}

	@Override
	public Material getMinecraftEquivalent() {
		return Material.LAVA;
	}
}
