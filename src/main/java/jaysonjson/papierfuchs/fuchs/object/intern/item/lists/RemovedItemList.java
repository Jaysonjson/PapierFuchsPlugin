package jaysonjson.papierfuchs.fuchs.object.intern.item.lists;

import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.resource.OreItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.weapon.HammerItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.weapon.SwordItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.magic.MagicPowderItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.magic.MagicType;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.registry.FRO;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class RemovedItemList {

    public static FuchsRegistryObject<HammerItem> silverHammer = new FuchsRegistryObject<>(new HammerItem("silver_hammer", "Silber", 7, 5, -0.27f, 27, Material.FEATHER, UseTypeList.WEAPON.copy()));

    public static FuchsRegistryObject<SwordItem> tinSword = new FuchsRegistryObject<>(new SwordItem("tin_sword", ChatColor.GRAY + "Zinn", 5, 221, 1.2f, 28, Material.FEATHER, UseTypeList.WEAPON.copy()));
    public static FuchsRegistryObject<SwordItem> silverSword = new FuchsRegistryObject<>(new SwordItem("silver_sword", ChatColor.DARK_GRAY + "Silver", 8, 1225, 1.2f, 25, Material.FEATHER, UseTypeList.WEAPON.copy()));
    public static FuchsRegistryObject<OreItem> tinOre = new FuchsRegistryObject<>(new OreItem("tin_ore", ChatColor.GRAY + "Zinn", -1, Material.FEATHER, UseTypeList.CRAFTING.copy()));
    //public static FuchsRegistryObject<TestPlaceItem> testPlace = new FuchsRegistryObject<>(new TestPlaceItem("test_place", Material.DIAMOND, UseTypeList.OTHER.copy()));

    public static FRO<MagicPowderItem> fireMagicPowder = new FRO<>(new MagicPowderItem("fire_magic_powder", Material.GLOWSTONE_DUST, UseTypeList.CRAFTING.copy(), MagicType.FIRE, 1));
    public static FRO<MagicPowderItem> waterMagicPowder = new FRO<>(new MagicPowderItem("water_magic_powder", Material.GLOWSTONE_DUST, UseTypeList.CRAFTING.copy(), MagicType.WATER, 4));
    public static FRO<MagicPowderItem> earthMagicPowder = new FRO<>(new MagicPowderItem("earth_magic_powder", Material.GLOWSTONE_DUST, UseTypeList.CRAFTING.copy(), MagicType.EARTH, 3));
    public static FRO<MagicPowderItem> airMagicPowder = new FRO<>(new MagicPowderItem("air_magic_powder", Material.GLOWSTONE_DUST, UseTypeList.CRAFTING.copy(), MagicType.AIR, 2));
    public static FRO<MagicPowderItem> lightMagicPowder = new FRO<>(new MagicPowderItem("light_magic_powder", Material.GLOWSTONE_DUST, UseTypeList.CRAFTING.copy(), MagicType.LIGHT, 5));

}
