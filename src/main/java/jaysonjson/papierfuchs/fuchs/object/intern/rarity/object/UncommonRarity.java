package jaysonjson.papierfuchs.fuchs.object.intern.rarity.object;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class UncommonRarity extends FuchsRarity {
    public UncommonRarity(String id) {
        super(id);
    }

    @Override
    public int getTier() {
        return 1;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString(ChatColor.BLUE.toString() + ChatColor.BOLD, "Ungewöhnlich", "rarity_uncommon");
    }

    @Override
    public float sellValueModifier() {
        return 0;
    }
}
