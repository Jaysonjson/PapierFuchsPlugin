package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import org.bukkit.ChatColor;

public enum ItemUseType implements IItemUseType {

    CRAFTING(ChatColor.AQUA + "Herstellungsmaterial", "use_type_crafting"),
    ABILITY(ChatColor.AQUA + "Benutzbar","use_type_useable"),
    CURRENCY(ChatColor.AQUA + "Währung", "use_type_currency"),
    TOOL(ChatColor.AQUA + "Werkzeug", "use_type_tool"),
    DECO(ChatColor.AQUA + "Dekoration", "use_type_deco"),
    WEAPON(ChatColor.AQUA + "Waffe", "use_type_weapon"),
    OTHER("", "");

    private final String loreText;
    private final String languageLoreText;
    ItemUseType(String loreText, String languageLoreText) {
        this.loreText = loreText;
        this.languageLoreText = languageLoreText;
    }

    @Override
    public String getLoreText() {
        return loreText;
    }

    @Override
    public String getLoreTextFromLanguage() {
        return languageLoreText;
    }
}
