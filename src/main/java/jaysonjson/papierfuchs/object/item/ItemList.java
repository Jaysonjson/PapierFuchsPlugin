package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.object.item.type.ability.CraftingUpgradekitItem;
import jaysonjson.papierfuchs.object.item.type.ability.essence.fire.FireEs01BlazeRodItem;
import jaysonjson.papierfuchs.object.item.type.ability.essence.fire.WallBlazeRodItem;
import jaysonjson.papierfuchs.object.item.type.crafting.*;
import jaysonjson.papierfuchs.object.item.type.crafting.copper.CopperIngotItem;
import jaysonjson.papierfuchs.object.item.type.crafting.copper.CopperPipeItem;
import jaysonjson.papierfuchs.object.item.type.crafting.copper.CopperRodItem;
import jaysonjson.papierfuchs.object.item.type.crafting.copper.CopperSwordItem;
import jaysonjson.papierfuchs.object.item.type.crafting.vilum.VilumIngotItem;
import jaysonjson.papierfuchs.object.item.type.crafting.vilum.VilumSwordItem;
import jaysonjson.papierfuchs.object.item.type.currency.GoldBarItem;
import jaysonjson.papierfuchs.object.item.type.currency.GoldNuggetItem;
import jaysonjson.papierfuchs.object.item.type.currency.HackSilverItem;
import jaysonjson.papierfuchs.object.item.type.currency.ZoryhaShardItem;
import jaysonjson.papierfuchs.object.item.type.magic.MagicPowderItem;
import jaysonjson.papierfuchs.object.item.type.magic.MagicType;
import jaysonjson.papierfuchs.object.item.type.other.*;
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
    public static ItemWithTexture DARK_OAK_BOWL = new ItemWithTexture("darkOakBowl", Material.FEATHER, ItemUseType.DECO, "Schüssel", 21, "§8Dunkle Eiche");
    public static ItemWithTexture OAK_BOWL = new ItemWithTexture("oakBowl", Material.FEATHER, ItemUseType.DECO, "Schüssel", 23, "§8Eiche");
    public static LegendaryChestBook LEGENDARY_CHEST_BOOK = new LegendaryChestBook("legendaryChestBook", Material.WRITTEN_BOOK, ItemUseType.OTHER);
    public static IDCardItem ID_CARD = new IDCardItem("idCard", Material.WRITTEN_BOOK, ItemUseType.OTHER);

   /* public static BackPackItem BACKPACK_9 = new BackPackItem("backPackItem9", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 9);
    public static BackPackItem BACKPACK_18 = new BackPackItem("backPackItem18", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 18);
    public static BackPackItem BACKPACK_27 = new BackPackItem("backPackItem27", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 27);
    public static BackPackItem BACKPACK_36 = new BackPackItem("backPackItem36", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 36);
    public static BackPackItem BACKPACK_45 = new BackPackItem("backPackItem45", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 45);
    public static BackPackItem BACKPACK_54 = new BackPackItem("backPackItem54", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 54);
*/
    public static FireEs01BlazeRodItem FIRE_ESSENCE_01 = new FireEs01BlazeRodItem("fireEs01BlazeRodItem", Material.BLAZE_POWDER, ItemUseType.ABILITY);
    public static WallBlazeRodItem WALL_BLAZE_ROD = new WallBlazeRodItem("wallBlazeRodItem", Material.BLAZE_POWDER, ItemUseType.ABILITY);

    public static AlcoholTestItem ALCOHOL_TEST = new AlcoholTestItem("alcoholTestItem", Material.FEATHER, ItemUseType.ABILITY);

    public static MagicPowderItem FIRE_POWDER = new MagicPowderItem("fireMagicPowder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.FIRE, 1);
    public static MagicPowderItem WATER_POWDER = new MagicPowderItem("waterMagicPowder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.WATER, 4);
    public static MagicPowderItem EARTH_POWDER = new MagicPowderItem("earthMagicPowder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.EARTH, 3);
    public static MagicPowderItem AIR_POWDER = new MagicPowderItem("airMagicPowder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.AIR, 2);
    public static MagicPowderItem LIGHT_POWDER = new MagicPowderItem("lightMagicPowder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.LIGHT, 5);

    public static CraftingUpgradekitItem CRAFTING_UPGRADE_KIT = new CraftingUpgradekitItem("craftingUpgradeKit", Material.FEATHER, ItemUseType.ABILITY);

    public static CopperPipeItem COPPER_PIPE = new CopperPipeItem("copperPipeItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static CopperRodItem COPPER_ROD = new CopperRodItem("copperRodItem", Material.FEATHER, ItemUseType.CRAFTING);
    public static CopperSwordItem COPPER_SWORD = new CopperSwordItem("copperSwordItem", Material.FEATHER, ItemUseType.TOOL);

    public static VilumSwordItem VILUM_SWORD = new VilumSwordItem("vilumSwordItem", Material.FEATHER, ItemUseType.TOOL);

    public static BackPackItemNBT BACKPACK_9_NBT = new BackPackItemNBT("backPackItem9", Material.FEATHER, ItemUseType.ABILITY, 9);
    public static BackPackItemNBT BACKPACK_18_NBT = new BackPackItemNBT("backPackItem18", Material.FEATHER, ItemUseType.ABILITY, 18);
    public static BackPackItemNBT BACKPACK_27_NBT = new BackPackItemNBT("backPackItem27", Material.FEATHER, ItemUseType.ABILITY, 27);
    public static BackPackItemNBT BACKPACK_36_NBT = new BackPackItemNBT("backPackItem36", Material.FEATHER, ItemUseType.ABILITY, 36);
    public static BackPackItemNBT BACKPACK_45_NBT = new BackPackItemNBT("backPackItem45", Material.FEATHER, ItemUseType.ABILITY, 45);
    public static BackPackItemNBT BACKPACK_54_NBT = new BackPackItemNBT("backPackItem54", Material.FEATHER, ItemUseType.ABILITY, 54);

    public static TestSwordItem TEST_SWORD = new TestSwordItem("testSword", Material.FEATHER, ItemUseType.TOOL);

    public static BatBookItem BAT_BOOK = new BatBookItem("batBook", Material.FEATHER, ItemUseType.ABILITY);
}
