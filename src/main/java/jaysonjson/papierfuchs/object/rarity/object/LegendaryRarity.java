package jaysonjson.papierfuchs.object.rarity.object;

import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class LegendaryRarity extends FuchsRarity {
    public LegendaryRarity(String id) {
        super(id);
    }

    @Override
    public String getLoreText() {
        return ChatColor.GOLD.toString() + ChatColor.BOLD.toString() + "Legendär";
    }

    @Override
    public String getLoreTextFromLanguage() {
        return "rarity_legendary";
    }
}
