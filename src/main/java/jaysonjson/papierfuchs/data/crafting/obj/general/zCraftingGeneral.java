package jaysonjson.papierfuchs.data.crafting.obj.general;

import java.util.ArrayList;

import jaysonjson.papierfuchs.data.crafting.obj.zCraftingItem;

public class zCraftingGeneral {

	
	public ArrayList<zCraftingItem> inputs = new ArrayList<>();
	public zCraftingItem output = new zCraftingItem();


	@Override
	public String toString() {
		return "zCraftingGeneral{" +
				"inputs=" + inputs +
				", output=" + output +
				'}';
	}
}
