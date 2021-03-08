package jaysonjson.papierfuchs.object.gas.objects;

import jaysonjson.papierfuchs.object.gas.FuchsGas;
import org.bukkit.ChatColor;

public class NoneGas extends FuchsGas {

	public NoneGas() {
		super("none");
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.RED + "Leer";
	}

}
