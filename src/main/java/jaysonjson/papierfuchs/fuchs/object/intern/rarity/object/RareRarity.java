package jaysonjson.papierfuchs.fuchs.object.intern.rarity.object;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class RareRarity extends FuchsRarity {

    public RareRarity(String id) {
        super(id);
    }

    @Override
    public int getTier() {
        return 2;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD,"Selten", "rarity_rare");
    }

    @Override
    public float sellValueModifier() {
        return 0;
    }

}
