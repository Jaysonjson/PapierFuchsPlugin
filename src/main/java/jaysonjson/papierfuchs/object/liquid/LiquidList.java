package jaysonjson.papierfuchs.object.liquid;

import jaysonjson.papierfuchs.object.liquid.objects.*;
import jaysonjson.papierfuchs.object.liquid.objects.alcohol.AlcoholLiquid;
import jaysonjson.papierfuchs.object.liquid.objects.metal.MoltenCopperLiquid;
import jaysonjson.papierfuchs.object.liquid.objects.metal.MoltenGoldLiquid;
import jaysonjson.papierfuchs.object.liquid.objects.metal.MoltenIronLiquid;
import org.bukkit.ChatColor;

public class LiquidList {
    public static MoltenCopperLiquid MOLTEN_COPPER = new MoltenCopperLiquid();
    public static MoltenGoldLiquid MOLTEN_GOLD = new MoltenGoldLiquid();
    public static MoltenIronLiquid MOLTEN_IRON = new MoltenIronLiquid();
    public static EthanolLiquid ETHANOL = new EthanolLiquid();
    public static LavaLiquid LAVA = new LavaLiquid();
    public static MixedLiquid MIXED = new MixedLiquid();
    public static NoneLiquid NONE = new NoneLiquid();
    public static WaterLiquid WATER = new WaterLiquid();
    public static CokeLiquid COKE = new CokeLiquid();
    public static DrinkableWaterLiquid DRINKABLE_WATER = new DrinkableWaterLiquid();

    public static AlcoholLiquid BEER = new AlcoholLiquid("beer", ChatColor.RESET + "" + ChatColor.GOLD + "Bier", 5, 15);
    public static AlcoholLiquid WHISKY = new AlcoholLiquid("whisky", ChatColor.RESET + "" + ChatColor.GOLD + "Whisky", 5, 7);

}
