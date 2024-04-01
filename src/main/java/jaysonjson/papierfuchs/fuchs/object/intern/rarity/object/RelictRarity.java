package jaysonjson.papierfuchs.fuchs.object.intern.rarity.object;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class RelictRarity extends FuchsRarity {

    public RelictRarity(String id) {
        super(id);
    }

    @Override
    public int getTier() {
        return 4;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD, "Relikt", "relict");
    }

    @Override
    public float sellValueModifier() {
        return 0;
    }
}
