package jaysonjson.papierfuchs.fuchs.object.intern.rarity.object;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class UniqueRarity extends FuchsRarity {
    public UniqueRarity(String id) {
        super(id);
    }

    @Override
    public int getTier() {
        return 0;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c(ChatColor.AQUA.toString() + ChatColor.BOLD, "Einzigartig", "unique");
    }

    @Override
    public float sellValueModifier() {
        return 0;
    }
}
