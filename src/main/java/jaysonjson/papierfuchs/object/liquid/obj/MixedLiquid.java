package jaysonjson.papierfuchs.object.liquid.obj;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import org.bukkit.ChatColor;

import java.util.ArrayList;

public class MixedLiquid extends FuchsLiquid {

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
			if(Utility.liquidExists(id)) {
				liquids.add(Utility.getLiquidByID(id));
			}
		}
		return liquids;
	}
	
	@Override
	public String getID() {
		return "mixed";
	}

	@Override
	public String getDisplayName() {
		StringBuilder displayName = new StringBuilder(ChatColor.RESET + "" + ChatColor.WHITE + "Gemischt");
		for(FuchsLiquid liquid : getContainedAbstractLiquids()) {
			displayName.append("\n").append(liquid.getDisplayName());
		}
		return displayName.toString();
	}
	
	@Override
	public int getCustomModelData() {
		return 16;
	}

}
