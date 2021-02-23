package jaysonjson.papierfuchs.object.effect.objects;

import jaysonjson.papierfuchs.object.effect.FuchsEffect;
import org.bukkit.ChatColor;

public class SmeltEffect extends FuchsEffect {


    public SmeltEffect(String id) {
        super(id);
    }

    @Override
    public String getDisplayName() {
        return ChatColor.DARK_RED + "Schmelzen";
    }
}
