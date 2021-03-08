package jaysonjson.papierfuchs.object.liquid.objects;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class EthanolLiquid extends FuchsLiquid {

	public EthanolLiquid() {
		super("ethanol");
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
