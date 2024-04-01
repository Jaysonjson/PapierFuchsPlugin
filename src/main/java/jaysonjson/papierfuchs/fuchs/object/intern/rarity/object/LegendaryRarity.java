package jaysonjson.papierfuchs.fuchs.object.intern.rarity.object;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class LegendaryRarity extends FuchsRarity {
    public LegendaryRarity(String id) {
        super(id);
    }

    @Override
    public int getTier() {
        return 3;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString(ChatColor.GOLD.toString() + ChatColor.BOLD, "Legendär", "rarity_legendary");
    }

    @Override
    public float sellValueModifier() {
        return 0;
    }
}
