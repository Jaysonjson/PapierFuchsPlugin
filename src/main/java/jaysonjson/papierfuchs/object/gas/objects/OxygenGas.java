package jaysonjson.papierfuchs.object.gas.objects;

import jaysonjson.papierfuchs.object.gas.FuchsGas;
import org.bukkit.ChatColor;

public class OxygenGas extends FuchsGas {


	public OxygenGas() {
		super("oxygen");
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.AQUA + "Sauerstoff";
	}

}
