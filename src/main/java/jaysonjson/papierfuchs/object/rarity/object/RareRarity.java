package jaysonjson.papierfuchs.object.rarity.object;

import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class RareRarity extends FuchsRarity {

    public RareRarity(String id) {
        super(id);
    }

    @Override
    public String getLoreText() {
        return ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD.toString() + "Selten";
    }

    @Override
    public String getLoreTextFromLanguage() {
        return "rarity_rare";
    }

}
