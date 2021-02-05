package jaysonjson.papierfuchs.data.crafting.obj.brewery.objs;


import jaysonjson.papierfuchs.object.item.FuchsItem;

public class zCraftingBreweryLiquidOutput {
    public String liquidOutputID = "";
    public double liquidAmount = 0.0;


    private transient FuchsItem liquidOutput;
    
    public FuchsItem getLiquidOutput() {
		return liquidOutput;
	}
}
