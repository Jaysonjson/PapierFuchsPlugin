package jaysonjson.papierfuchs.skillclass;

import jaysonjson.papierfuchs.object.item.ItemUseType;
import jaysonjson.papierfuchs.object.item.objects.generic.ItemWithTexture;
import jaysonjson.papierfuchs.skillclass.alchemist.zAlchemistClass;
import jaysonjson.papierfuchs.skillclass.fighter.zFighterClass;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public enum zClass {

    NONE("Keine Klasse", new zDefaultClass(),ChatColor.RED + "", new ItemStack(Material.AIR)),
    FIGHTER("Kämpfer",new zFighterClass(), ChatColor.RED + "", new ItemWithTexture("fighter_class_item", Material.NETHERITE_SWORD, ItemUseType.OTHER, ChatColor.RED + "Kämpfer",  -1, true).createItem()),
    FARMER("Farmer", new zDefaultClass(),ChatColor.RED + "", new ItemWithTexture("farmer_class_item", Material.NETHERITE_HOE, ItemUseType.OTHER, ChatColor.GOLD + "Farmer",  -1, true).createItem()),
    CRAFTER("Schmied", new zDefaultClass(),ChatColor.RED + "", new ItemWithTexture("crafter_class_item", Material.CRAFTING_TABLE, ItemUseType.OTHER, ChatColor.RED + "Hersteller",  -1, true).createItem()),
    TRAVELER("Wanderer", new zDefaultClass(),ChatColor.RED + "", new ItemWithTexture("traveler_class_item", Material.IRON_BOOTS, ItemUseType.OTHER, ChatColor.YELLOW + "Wanderer",  -1, true).createItem()),
    POTIONER("Brauer", new zDefaultClass(),ChatColor.RED + "", new ItemWithTexture("potioner_class_item", Material.LINGERING_POTION, ItemUseType.OTHER, ChatColor.BLUE + "Brauer",  -1, true).createItem()),
    ALCHEMIST("Alchemist", new zAlchemistClass(),ChatColor.RED + "", new ItemWithTexture("alchemist_class_item", Material.LAVA_BUCKET,  ItemUseType.OTHER, ChatColor.DARK_RED + "Alchemist",  -1, true).createItem());


    private String name;
    private zAbstractClass abstractClass;
    private String colors;
    private ItemStack symbol;
    zClass(String name, zAbstractClass abstractClass, String colors, ItemStack symbol) {
        this.name = name;
        this.colors = colors;
        this.symbol = symbol;
        this.abstractClass = abstractClass;
    }

    public String getName() {
        return name;
    }

    @Nullable
    public zAbstractClass getAbstractClass() {
        return abstractClass;
    }

    public String getColor() {
        return colors;
    }

    public ItemStack getSymbol() {
        return symbol;
    }
}
