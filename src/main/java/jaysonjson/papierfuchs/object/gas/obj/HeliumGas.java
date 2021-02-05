package jaysonjson.papierfuchs.object.gas.obj;

import jaysonjson.papierfuchs.object.gas.FuchsGas;
import org.bukkit.ChatColor;

public class HeliumGas extends FuchsGas {

	@Override
	public String getID() {
		return "helium";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GREEN + "Helium";
	}

}
