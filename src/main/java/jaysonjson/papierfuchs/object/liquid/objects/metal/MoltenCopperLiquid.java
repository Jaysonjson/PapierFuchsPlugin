package jaysonjson.papierfuchs.object.liquid.objects.metal;

import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

public class MoltenCopperLiquid extends FuchsLiquid {

	public MoltenCopperLiquid() {
		super("molten_copper");
	}

	@Override
	public String getDisplayName() {
		return ChatColor.RESET + "" + ChatColor.GOLD + "Geschmolzenes Kupfer";
	}
	
	@Override
	public int getCustomModelData() {
		return 17;
	}

}
