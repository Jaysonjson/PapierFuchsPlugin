package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.object.item.objects.ability.CraftingUpgradekitItem;
import jaysonjson.papierfuchs.object.item.objects.ability.essence.fire.FireEs01BlazeRodItem;
import jaysonjson.papierfuchs.object.item.objects.ability.essence.fire.WallBlazeRodItem;
import jaysonjson.papierfuchs.object.item.objects.crafting.*;
import jaysonjson.papierfuchs.object.item.objects.generic.ItemWithTexture;
import jaysonjson.papierfuchs.object.item.objects.generic.resource.OreItem;
import jaysonjson.papierfuchs.object.item.objects.generic.weapon.HammerItem;
import jaysonjson.papierfuchs.object.item.objects.generic.weapon.SwordItem;
import jaysonjson.papierfuchs.object.item.objects.resource.ZoryhaFighterSword;
import jaysonjson.papierfuchs.object.item.objects.resource.ZoryhaTear;
import jaysonjson.papierfuchs.object.item.objects.resource.copper.material.CopperIngotItem;
import jaysonjson.papierfuchs.object.item.objects.resource.copper.crafting.CopperPipeItem;
import jaysonjson.papierfuchs.object.item.objects.resource.copper.crafting.CopperRodItem;
import jaysonjson.papierfuchs.object.item.objects.resource.tin.material.TinIngotItem;
import jaysonjson.papierfuchs.object.item.objects.resource.vilum.material.VilumIngotItem;
import jaysonjson.papierfuchs.object.item.objects.currency.HackSilverItem;
import jaysonjson.papierfuchs.object.item.objects.currency.ZoryhaShardItem;
import jaysonjson.papierfuchs.object.item.objects.magic.MagicPowderItem;
import jaysonjson.papierfuchs.object.item.objects.magic.MagicType;
import jaysonjson.papierfuchs.object.item.objects.other.*;
import jaysonjson.papierfuchs.object.item.objects.resource.vilum.tool.VilumSwordItem;
import jaysonjson.papierfuchs.object.item.objects.vanillaOverride.IronOreItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ItemList {

    /*public static GoldBarItem GOLD_BAR = new GoldBarItem("goldBarItem", Material.GOLD_INGOT, ItemUseType.CURRENCY);
    public static GoldNuggetItem GOLD_NUGGET = new GoldNuggetItem("goldNuggetItem", Material.GOLD_NUGGET, ItemUseType.CURRENCY);*/
    public static HackSilverItem HACKSILVER = new HackSilverItem("hacksilver", Material.IRON_NUGGET, ItemUseType.CURRENCY);
    public static SkillBookItem SKILL_BOOK = new SkillBookItem("skillbook", Material.WRITTEN_BOOK, ItemUseType.OTHER);
    public static ZoryhaShardItem ZORYHA_SHARD = new ZoryhaShardItem("zoryha_shard", Material.NETHER_STAR, ItemUseType.CURRENCY);
    public static CopperIngotItem COPPER_INGOT = new CopperIngotItem("copper_ingot", Material.FEATHER, ItemUseType.CRAFTING);
    public static SilverIngotItem SILVER_INGOT = new SilverIngotItem("silver_ingot", Material.FEATHER, ItemUseType.CRAFTING);
    public static MaltItem MALT = new MaltItem("malt", Material.FEATHER, ItemUseType.CRAFTING);
    public static HopItem HOP = new HopItem("hop", Material.FEATHER, ItemUseType.CRAFTING);
    public static GlassItem GLASS = new GlassItem("glass", Material.FEATHER, ItemUseType.ABILITY);
    public static LiquidContainerItem LIQUID_CONTAINER = new LiquidContainerItem("liquid_container", Material.FEATHER, ItemUseType.CRAFTING);
    public static GasContainerItem GAS_CONTAINER = new GasContainerItem("gas_container", Material.FEATHER, ItemUseType.CRAFTING);
    public static VilumIngotItem VILUM_INGOT = new VilumIngotItem("vilum_ingot", Material.FEATHER, ItemUseType.CRAFTING);
    public static IronOreItem IRON_ORE = new IronOreItem("iron_ore", Material.IRON_ORE, ItemUseType.CRAFTING);
    public static ScrapItem SCRAP = new ScrapItem("scrap", Material.NETHERITE_SCRAP, ItemUseType.CURRENCY);
    public static ItemWithTexture DARK_OAK_BOWL = new ItemWithTexture("dark_oak_bowl", Material.FEATHER, ItemUseType.DECO, "Schüssel", 21, "§8Dunkle Eiche");
    public static ItemWithTexture OAK_BOWL = new ItemWithTexture("oak_bowl", Material.FEATHER, ItemUseType.DECO, "Schüssel", 23, "§8Eiche");
    public static LegendaryChestBook LEGENDARY_CHEST_BOOK = new LegendaryChestBook("legendary_chest_book", Material.WRITTEN_BOOK, ItemUseType.OTHER);
    public static IDCardItem ID_CARD = new IDCardItem("id_card", Material.WRITTEN_BOOK, ItemUseType.OTHER);

   /* public static BackPackItem BACKPACK_9 = new BackPackItem("backPackItem9", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 9);
    public static BackPackItem BACKPACK_18 = new BackPackItem("backPackItem18", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 18);
    public static BackPackItem BACKPACK_27 = new BackPackItem("backPackItem27", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 27);
    public static BackPackItem BACKPACK_36 = new BackPackItem("backPackItem36", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 36);
    public static BackPackItem BACKPACK_45 = new BackPackItem("backPackItem45", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 45);
    public static BackPackItem BACKPACK_54 = new BackPackItem("backPackItem54", Material.HEART_OF_THE_SEA, ItemUseType.ABILITY, 54);
*/
    public static FireEs01BlazeRodItem FIRE_ESSENCE_01 = new FireEs01BlazeRodItem("fireEs01BlazeRodItem", Material.BLAZE_POWDER, ItemUseType.ABILITY);
    public static WallBlazeRodItem WALL_BLAZE_ROD = new WallBlazeRodItem("wallBlazeRodItem", Material.BLAZE_POWDER, ItemUseType.ABILITY);

    public static AlcoholTestItem ALCOHOL_TEST = new AlcoholTestItem("alcohol_tester", Material.FEATHER, ItemUseType.ABILITY);

    public static MagicPowderItem FIRE_POWDER = new MagicPowderItem("fire_magic_powder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.FIRE, 1);
    public static MagicPowderItem WATER_POWDER = new MagicPowderItem("water_magic_powder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.WATER, 4);
    public static MagicPowderItem EARTH_POWDER = new MagicPowderItem("earth_magic_powder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.EARTH, 3);
    public static MagicPowderItem AIR_POWDER = new MagicPowderItem("air_magic_powder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.AIR, 2);
    public static MagicPowderItem LIGHT_POWDER = new MagicPowderItem("light_magic_powder", Material.GLOWSTONE_DUST, ItemUseType.CRAFTING, MagicType.LIGHT, 5);

    public static CraftingUpgradekitItem CRAFTING_UPGRADE_KIT = new CraftingUpgradekitItem("crafting_upgrade_kit", Material.FEATHER, ItemUseType.ABILITY);

    public static CopperPipeItem COPPER_PIPE = new CopperPipeItem("copper_pipe", Material.FEATHER, ItemUseType.CRAFTING);
    public static CopperRodItem COPPER_ROD = new CopperRodItem("copper_rod", Material.FEATHER, ItemUseType.CRAFTING);
    //public static CopperSwordItem COPPER_SWORD = new CopperSwordItem("copper_sword", Material.FEATHER, ItemUseType.TOOL);

    public static BackPackItemNBT BACKPACK_9_NBT = new BackPackItemNBT("backpack_9", Material.FEATHER, ItemUseType.ABILITY, 9);
    public static BackPackItemNBT BACKPACK_18_NBT = new BackPackItemNBT("backpack_18", Material.FEATHER, ItemUseType.ABILITY, 18);
    public static BackPackItemNBT BACKPACK_27_NBT = new BackPackItemNBT("backpack_27", Material.FEATHER, ItemUseType.ABILITY, 27);
    public static BackPackItemNBT BACKPACK_36_NBT = new BackPackItemNBT("backpack_36", Material.FEATHER, ItemUseType.ABILITY, 36);
    public static BackPackItemNBT BACKPACK_45_NBT = new BackPackItemNBT("backpack_45", Material.FEATHER, ItemUseType.ABILITY, 45);
    public static BackPackItemNBT BACKPACK_54_NBT = new BackPackItemNBT("backpack_54", Material.FEATHER, ItemUseType.ABILITY, 54);

    public static TestSwordItem TEST_SWORD = new TestSwordItem("test_sword", Material.FEATHER, ItemUseType.WEAPON);

    public static BatBookItem BAT_BOOK = new BatBookItem("bat_book", Material.BOOK, ItemUseType.ABILITY);

    public static TinIngotItem TIN_INGOT = new TinIngotItem("tin_ingot", Material.FEATHER, ItemUseType.CRAFTING);

    public static HammerItem SILVER_HAMMER = new HammerItem("silver_hammer", "Silber", 7, 5, -0.27f, 27, Material.FEATHER, ItemUseType.WEAPON);

    public static SwordItem TIN_SWORD = new SwordItem("tin_sword", ChatColor.GRAY + "Zinn", 5, 221, 1.2f, 28, Material.FEATHER, ItemUseType.WEAPON);
    public static SwordItem VILUM_SWORD = new VilumSwordItem("vilum_sword", ChatColor.LIGHT_PURPLE + "Vilum", 17, 120, 1.2f, 25, Material.FEATHER, ItemUseType.WEAPON);
    public static SwordItem SILVER_SWORD = new SwordItem("silver_sword", ChatColor.DARK_GRAY + "Silver", 8, 1225, 1.2f, 25, Material.FEATHER, ItemUseType.WEAPON);

    public static OreItem TIN_ORE = new OreItem("tin_ore", ChatColor.GRAY + "Zinn", -1, Material.FEATHER, ItemUseType.CRAFTING);
    public static ZoryhaFighterSword ZORYHA_FIGHTER_SWORD = new ZoryhaFighterSword("zoryha_fighter_sword", Material.FEATHER, ItemUseType.WEAPON);
    public static ZoryhaTear ZORYHA_TEAR = new ZoryhaTear("zoryha_tear", Material.FEATHER, ItemUseType.CRAFTING);

    public static ItemWithTexture UK_FLAG = new ItemWithTexture("uk_flag", Material.FEATHER, ItemUseType.OTHER, "English", 32, true);
    public static ItemWithTexture GERMAN_FLAG = new ItemWithTexture("german_flag", Material.FEATHER, ItemUseType.OTHER, "Deutsch", 31, true);
}
