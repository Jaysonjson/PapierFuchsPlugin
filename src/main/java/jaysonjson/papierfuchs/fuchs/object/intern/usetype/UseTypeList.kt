package jaysonjson.papierfuchs.fuchs.`object`.intern.usetype

import jaysonjson.papierfuchs.fuchs.`object`.FuchsLanguageString
import jaysonjson.papierfuchs.fuchs.registry.FRO
import org.bukkit.ChatColor

object UseTypeList {
    @JvmField var CRAFTING = FRO(FuchsUseType("crafting", FuchsLanguageString(ChatColor.AQUA, "Herstellungsmaterial", "use_type_crafting")))
    @JvmField var USE_ABLE = FRO(FuchsUseType("use_able", FuchsLanguageString(ChatColor.AQUA, "Benutzbar", "use_type_useable")))
    @JvmField var CURRENCY = FRO(FuchsUseType("currency", FuchsLanguageString(ChatColor.AQUA, "Währung", "use_type_currency")))
    @JvmField var TOOL = FRO(FuchsUseType("tool", FuchsLanguageString(ChatColor.AQUA, "Werkzeug", "use_type_tool")))
    @JvmField var DECORATION = FRO(FuchsUseType("decoration", FuchsLanguageString(ChatColor.AQUA, "Dekoration", "use_type_deco")))
    @JvmField var WEAPON = FRO(FuchsUseType("weapon", FuchsLanguageString(ChatColor.AQUA, "Waffe", "use_type_weapon")))
    @JvmField var COSMETIC = FRO(FuchsUseType("cosmetic", FuchsLanguageString(ChatColor.AQUA, "Kosmetik", "cosmetic")))
    @JvmField var PROJECTILE = FRO(FuchsUseType("projectile", FuchsLanguageString(ChatColor.AQUA, "Projektil", "projectile")))
    @JvmField var ARMOR = FRO(FuchsUseType("armor", FuchsLanguageString(ChatColor.AQUA, "Rüstung", "armor")))
    @JvmField var OTHER = FRO(FuchsUseType("other", FuchsLanguageString(ChatColor.AQUA, "Anderes", "other")))
    @JvmField var FOOD = FRO(FuchsUseType("food", FuchsLanguageString(ChatColor.AQUA, "Essen", "food")))
    @JvmField var NONE = FRO(FuchsUseType("none", FuchsLanguageString()))
    @JvmField var KNEAD = FRO(FuchsUseType("knead", FuchsLanguageString(ChatColor.AQUA, "Knete", "knead")))
    @JvmField var BOW = FRO(FuchsUseType("bow", FuchsLanguageString(ChatColor.AQUA, "Bogen", "bow")))
    @JvmField var ADMIN = FRO(FuchsUseType("admin", FuchsLanguageString(ChatColor.AQUA, "Admin")))
}