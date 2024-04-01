package jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.brewery.objs;


import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

public class zCraftingBreweryLiquidInput {

	public String liquidInputID = "";
	public double liquidAmount = 0.0;
	
	
	private transient FuchsItem liquidInput;
	
	public FuchsItem getLiquidInput() {
		return liquidInput;
	}
}
