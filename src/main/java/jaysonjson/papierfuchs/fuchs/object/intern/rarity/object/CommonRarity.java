package jaysonjson.papierfuchs.fuchs.object.intern.rarity.object;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class CommonRarity extends FuchsRarity {
    public CommonRarity(String id) {
        super(id);
    }

    @Override
    public int getTier() {
        return 0;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString(ChatColor.GRAY + ChatColor.BOLD.toString(), "Verbreitet", "rarity_common");
    }

    @Override
    public float sellValueModifier() {
        return 0;
    }
}
