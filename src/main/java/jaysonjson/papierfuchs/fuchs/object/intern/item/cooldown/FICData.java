package jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown;

import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import org.bukkit.scheduler.BukkitRunnable;

public class FICData<T> {

    T event;
    FuchsMCItem fuchsMCItem;
    BukkitRunnable runnable;
    int itemSlot;
    public FICData(T event) {
        this.event = event;
    }

    public T getEvent() {
        return event;
    }

    public FuchsMCItem getFuchsMCItem() {
        return fuchsMCItem;
    }

    public BukkitRunnable getRunnable() {
        return runnable;
    }

    public void setFuchsMCItem(FuchsMCItem fuchsMCItem) {
        this.fuchsMCItem = fuchsMCItem;
    }

    public void setRunnable(BukkitRunnable runnable) {
        this.runnable = runnable;
    }

    public int getItemSlot() {
        return itemSlot;
    }

    public void setItemSlot(int itemSlot) {
        this.itemSlot = itemSlot;
    }
}
