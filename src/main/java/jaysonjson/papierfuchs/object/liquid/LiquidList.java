package jaysonjson.papierfuchs.object.liquid;

import jaysonjson.papierfuchs.object.liquid.objects.*;
import jaysonjson.papierfuchs.object.liquid.objects.alcohol.BeerLiquid;
import jaysonjson.papierfuchs.object.liquid.objects.alcohol.WhiskyLiquid;
import jaysonjson.papierfuchs.object.liquid.objects.metal.MoltenCopperLiquid;
import jaysonjson.papierfuchs.object.liquid.objects.metal.MoltenGoldLiquid;
import jaysonjson.papierfuchs.object.liquid.objects.metal.MoltenIronLiquid;

public class LiquidList {
    public static MoltenCopperLiquid MOLTEN_COPPER = new MoltenCopperLiquid();
    public static MoltenGoldLiquid MOLTEN_GOLD = new MoltenGoldLiquid();
    public static MoltenIronLiquid MOLTEN_IRON = new MoltenIronLiquid();
    public static BeerLiquid BEER = new BeerLiquid();
    public static WhiskyLiquid WHISKY = new WhiskyLiquid();
    public static EthanolLiquid ETHANOL = new EthanolLiquid();
    public static LavaLiquid LAVA = new LavaLiquid();
    public static MixedLiquid MIXED = new MixedLiquid();
    public static NoneLiquid NONE = new NoneLiquid();
    public static WaterLiquid WATER = new WaterLiquid();
}
