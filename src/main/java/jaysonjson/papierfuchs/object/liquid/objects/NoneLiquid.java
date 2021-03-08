package jaysonjson.papierfuchs.object.liquid.objects;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class NoneLiquid extends FuchsLiquid {

	public NoneLiquid() {
		super("none");
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
