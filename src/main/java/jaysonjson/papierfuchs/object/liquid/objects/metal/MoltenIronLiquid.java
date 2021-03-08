package jaysonjson.papierfuchs.object.liquid.objects.metal;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class MoltenIronLiquid extends FuchsLiquid {

	public MoltenIronLiquid() {
		super("molten_iron");
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.DARK_GRAY + "Geschmolzenes Eisen";
	}
	
	@Override
	public int getCustomModelData() {
		return 19;
	}

}
