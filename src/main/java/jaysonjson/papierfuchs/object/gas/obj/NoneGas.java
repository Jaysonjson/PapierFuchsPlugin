package jaysonjson.papierfuchs.object.gas.obj;

import jaysonjson.papierfuchs.object.gas.FuchsGas;
import org.bukkit.ChatColor;

public class NoneGas extends FuchsGas {

	@Override
	public String getID() {
		return "none";
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.RED + "Leer";
	}

}
