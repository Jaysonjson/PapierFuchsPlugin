package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.object.item.type.crafting.*;
import jaysonjson.papierfuchs.object.item.type.crafting.copper.CopperIngotItem;
import jaysonjson.papierfuchs.object.item.type.currency.GoldBarItem;
import jaysonjson.papierfuchs.object.item.type.currency.GoldNuggetItem;
import jaysonjson.papierfuchs.object.item.type.currency.HackSilverItem;
import jaysonjson.papierfuchs.object.item.type.currency.ZoryhaShardItem;
import jaysonjson.papierfuchs.object.item.type.other.GasContainerItem;
import jaysonjson.papierfuchs.object.item.type.other.LiquidContainerItem;
import jaysonjson.papierfuchs.object.item.type.other.ScrapItem;
import jaysonjson.papierfuchs.object.item.type.other.SkillBookItem;
import jaysonjson.papierfuchs.object.item.type.vanillaOverride.IronOreItem;
import org.bukkit.Material;

public class ItemList {

    public static GoldBarItem GOLD_BAR = new GoldBarItem("goldBarItem", Material.GOLD_INGOT, ItemUseType.CURRENCY);
    public static GoldNuggetItem GOLD_NUGGET = new GoldNuggetItem("goldNuggetItem", Material.GOLD_NUGGET, ItemUseType.CURRENCY);
    public static HackSilverItem HACKSILVER = new HackSilverItem("hackSilverItem", Material.IRON_NUGGET, ItemUseType.CURRENCY);
    public static SkillBookItem SKILL_BOOK = new SkillBookItem("skillBookItem", Material.WRITTEN_BOOK, ItemUseType.OTHER);
    public static ZoryhaShardItem ZORYHA_SHARD = new ZoryhaShardItem("zoryhaShardItem", Material.NETHER_STAR, ItemUseType.CURRENCY);
    public static CopperIngotItem COPPER_INGOT = new CopperIngotItem("copperIngotItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static SilverIngotItem SILVER_INGOT = new SilverIngotItem("silverIngotItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static MaltItem MALT = new MaltItem("maltItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static HopItem HOP = new HopItem("hopItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static GlassItem GLASS = new GlassItem("glassItem", Material.FEATHER, ItemUseType.ABILITY);
    public static LiquidContainerItem LIQUID_CONTAINER = new LiquidContainerItem("liquidContainerItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static GasContainerItem GAS_CONTAINER = new GasContainerItem("gasContainerItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static VilumIngotItem VILUM_INGOT = new VilumIngotItem("vilumIngotItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static IronOreItem IRON_ORE = new IronOreItem("ironOreItem", Material.IRON_ORE, ItemUseType.CRAFTING);
    public static ScrapItem SCRAP = new ScrapItem("scrapItem", Material.NETHERITE_SCRAP, ItemUseType.CURRENCY);

}
