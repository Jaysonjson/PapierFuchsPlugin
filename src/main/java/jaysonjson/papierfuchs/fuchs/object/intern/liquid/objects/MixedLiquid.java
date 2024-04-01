package jaysonjson.papierfuchs.fuchs.object.intern.liquid.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class MixedLiquid extends FuchsLiquid {

	public MixedLiquid() {
		super("mixed");
	}

	private ArrayList<String> containedLiquids = new ArrayList<>();
	
	public ArrayList<String> getContainedLiquids() {
		return containedLiquids;
	}
	
	public void setContainedLiquids(ArrayList<String> containedLiquids) {
		this.containedLiquids = containedLiquids;
	}
	
	public ArrayList<FuchsLiquid> getContainedAbstractLiquids() {
		ArrayList<FuchsLiquid> liquids = new ArrayList<>();
		for(String id : containedLiquids) {
			if(FuchsUtility.liquidExists(id)) {
				liquids.add(FuchsUtility.getLiquidByID(id));
			}
		}
		return liquids;
	}

	@Override
	public FuchsLanguageString getDisplayName() {
		StringBuilder liquids = new StringBuilder(ChatColor.RESET + "" + ChatColor.WHITE + "Gemischt");
		for(FuchsLiquid liquid : getContainedAbstractLiquids()) {
			liquids.append("\n").append(liquid.getDisplayName());
		}
		return new FuchsLanguageString(liquids.toString());
	}

	@Override
	public int getCustomModelData() {
		return 16;
	}

}
