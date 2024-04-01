package jaysonjson.papierfuchs.fuchs.object.intern.gas;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import org.bukkit.ChatColor;

public class NoneGas extends FuchsGas {

	public NoneGas() {
		super("none");
	}

	@Override
	public FuchsLanguageString getDisplayName() {
		return new FuchsLanguageString(ChatColor.RESET + "" + ChatColor.RED + "Leer");
	}
}
