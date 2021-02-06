package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import org.bukkit.ChatColor;

public enum ItemUseType implements IItemUseType {

    CRAFTING(ChatColor.AQUA + "Herstellungsmaterial"),
    ABILITY(ChatColor.AQUA + "Benutzbar"),
    CURRENCY(ChatColor.AQUA + "Währung"),
    TOOL(ChatColor.AQUA + "Werkzeug"),
    DECO(ChatColor.AQUA + "Dekoration"),
    OTHER("");

    private final String loreText;
    ItemUseType(String loreText) {
        this.loreText = loreText;
    }

    @Override
    public String getLoreText() {
        return loreText;
    }
}
