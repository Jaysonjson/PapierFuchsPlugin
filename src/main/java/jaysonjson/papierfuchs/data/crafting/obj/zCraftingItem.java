package jaysonjson.papierfuchs.data.crafting.obj;

import jaysonjson.papierfuchs.object.item.FuchsItem;

import java.util.HashMap;


public class zCraftingItem {

	public String itemID = "";
	public String lore = "";
	public Integer amount = 0;
	public HashMap<String, Integer> stats = new HashMap<>();
	
	public transient FuchsItem item;
	
	public FuchsItem getItem() {
		return item;
	}
	
	public String getItemID() {
		return itemID;
	}
}
