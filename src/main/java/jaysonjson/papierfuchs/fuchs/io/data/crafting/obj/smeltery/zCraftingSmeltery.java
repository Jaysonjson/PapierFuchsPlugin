package jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.smeltery;

import java.util.ArrayList;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

public class zCraftingSmeltery {

	
	public ArrayList<String> inputID = new ArrayList<String>();
	public String outputID = "";
	
	public transient ArrayList<FuchsItem> input = new ArrayList<FuchsItem>();
	public transient FuchsItem output = null;
	
}
