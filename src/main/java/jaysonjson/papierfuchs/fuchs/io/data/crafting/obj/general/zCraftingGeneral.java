package jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.general;

import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;

import java.util.ArrayList;

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
