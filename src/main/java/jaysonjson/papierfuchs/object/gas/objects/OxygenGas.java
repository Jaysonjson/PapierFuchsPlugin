package jaysonjson.papierfuchs.object.gas.objects;

import jaysonjson.papierfuchs.object.gas.FuchsGas;
import org.bukkit.ChatColor;

public class OxygenGas extends FuchsGas {

	@Override
	public String getID() {
		return "oxygen";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.AQUA + "Sauerstoff";
	}

}
