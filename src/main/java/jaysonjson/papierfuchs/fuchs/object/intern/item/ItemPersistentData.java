package jaysonjson.papierfuchs.fuchs.object.intern.item;

import jaysonjson.papierfuchs.PapierFuchs;
import org.bukkit.NamespacedKey;

public class ItemPersistentData {

    //PLUGIN
    private static final PapierFuchs I = PapierFuchs.INSTANCE;

    //INTS
    //public static NamespacedKey ITEM_DURABILITY = k("itemDurability");

    //STRINGS
    //public static NamespacedKey ITEM_RARITY = k("itemRarity");
    //public static NamespacedKey CREATIVE_GET_USER = k("creativeGetUser");
    //public static NamespacedKey CURRENCY_TYPE = k("currencyType");

    //DOUBLES
    //public static NamespacedKey ATTACK_DAMAGE = k("attackDamage");

    //BOOLS
   // public static NamespacedKey CAN_CRAFT = k("canCraft");

    //BYTE ARRAYS
    public static NamespacedKey FUCHS_ITEM_GENERAL = k("fuchsItemGeneral");
    public static NamespacedKey FUCHS_TOOL_GENERAL = k("fuchsItemTool");
    public static NamespacedKey FUCHS_ARMOR_GENERAL = k("fuchsItemArmor");
    public static NamespacedKey FUCHS_CURRENCY_GENERAL = k("fuchsItemCurrency");
    public static NamespacedKey FUCHS_ALCHEMY_GENERAL = k("fuchsItemAlchemy");
    public static NamespacedKey FUCHS_MAGIC_GENERAL = k("fuchsItemMagic");
    public static NamespacedKey FUCHS_LIQUID_GENERAL = k("fuchsItemLiquid");
    public static NamespacedKey FUCHS_GAS_GENERAL = k("fuchsItemGas");
    public static NamespacedKey FUCHS_BLOCK_GENERAL = k("fuchsItemBlock");
    public static NamespacedKey FUCHS_PROJECTILE_GENERAL = k("fuchsItemProjectile");
    public static NamespacedKey FUCHS_SPECIAL = k("fuchsItemSpecial");
    public static NamespacedKey FUCHS_PLANT = k("fuchsItemPlant");

    public static NamespacedKey k(String key) {
        return new NamespacedKey(I, key);
    }

}
