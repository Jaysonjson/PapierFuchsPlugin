package jaysonjson.papierfuchs.fuchs.object.intern.liquid.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class NoneLiquid extends FuchsLiquid {

	public NoneLiquid() {
		super("none");
	}

	@Override
	public FuchsLanguageString getDisplayName() {
		return new FuchsLanguageString(ChatColor.RESET + "" + ChatColor.RED + "Leer");
	}

	@Override
	public int getCustomModelData() {
		return 6;
	}

}
