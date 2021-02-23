package jaysonjson.papierfuchs.object.rarity.object;

import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import org.bukkit.ChatColor;

public class CommonRarity extends FuchsRarity {
    public CommonRarity(String id) {
        super(id);
    }

    @Override
    public String getLoreText() {
        return ChatColor.GRAY.toString() + ChatColor.BOLD.toString() + "Verbreitet";
    }
}
