package jaysonjson.papierfuchs.object.effect.objects;

import jaysonjson.papierfuchs.object.effect.FuchsEffect;
import org.bukkit.ChatColor;

public class AntiDespawnEffect extends FuchsEffect {
    public AntiDespawnEffect(String id) {
        super(id);
    }

    @Override
    public String getDisplayName() {
        return ChatColor.GREEN + "Anti-Despawn";
    }
}
