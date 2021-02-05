package jaysonjson.papierfuchs.object.gas.obj;

import jaysonjson.papierfuchs.object.gas.FuchsGas;
import org.bukkit.ChatColor;

public class AirGas extends FuchsGas {

	@Override
	public String getID() {
		return "air";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.WHITE + "Luft";
	}

}
