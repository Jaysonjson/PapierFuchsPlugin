package jaysonjson.papierfuchs.data.crafting.obj.brewery.objs;


import jaysonjson.papierfuchs.object.item.FuchsItem;

public class zCraftingBreweryLiquidInput {

	public String liquidInputID = "";
	public double liquidAmount = 0.0;
	
	
	private transient FuchsItem liquidInput;
	
	public FuchsItem getLiquidInput() {
		return liquidInput;
	}
}
