package jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.items.data;

import jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown.FICData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.knead.FuchsKneadItem;
import org.bukkit.event.player.PlayerInteractEvent;

public class KneadCooldownData extends FICData<PlayerInteractEvent> {
    FuchsKneadItem kneadItem;
    public KneadCooldownData(PlayerInteractEvent event) {
        super(event);
    }

    public FuchsKneadItem getKneadItem() {
        return kneadItem;
    }

    public void setKneadItem(FuchsKneadItem kneadItem) {
        this.kneadItem = kneadItem;
    }
}
