package jaysonjson.papierfuchs.object.gas.objects;

import jaysonjson.papierfuchs.object.gas.FuchsGas;
import org.bukkit.ChatColor;

public class AirGas extends FuchsGas {

	public AirGas() {
		super("air");
	}


	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.WHITE + "Luft";
	}

}
