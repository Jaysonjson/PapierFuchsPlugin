package jaysonjson.papierfuchs.data.crafting.obj.brewery;

import java.util.ArrayList;

import jaysonjson.papierfuchs.data.crafting.obj.brewery.objs.zCraftingBreweryLiquidInput;
import jaysonjson.papierfuchs.data.crafting.obj.brewery.objs.zCraftingBreweryLiquidOutput;
import jaysonjson.papierfuchs.object.item.FuchsItem;

public class zCraftingBrewery {

	
	public ArrayList<String> inputsID = new ArrayList<String>();
	public zCraftingBreweryLiquidInput liquidInput = new zCraftingBreweryLiquidInput();
	public zCraftingBreweryLiquidOutput liquidOutput = new zCraftingBreweryLiquidOutput();
	public int level = 1;
	public double xpGain = 0;
	public transient ArrayList<FuchsItem> inputs = new ArrayList<FuchsItem>();
	
	
	
}
