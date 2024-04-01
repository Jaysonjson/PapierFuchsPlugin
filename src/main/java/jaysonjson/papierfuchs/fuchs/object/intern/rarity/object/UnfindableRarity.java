package jaysonjson.papierfuchs.fuchs.object.intern.rarity.object;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class UnfindableRarity extends FuchsRarity {
    public UnfindableRarity(String id) {
        super(id);
    }

    @Override
    public int getTier() {
        return -1;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString(ChatColor.GREEN + ChatColor.BOLD.toString(), "Unfindbar", "rarity_unfindable");
    }

    @Override
    public float sellValueModifier() {
        return 0;
    }
}
