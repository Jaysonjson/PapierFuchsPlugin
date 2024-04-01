package jaysonjson.papierfuchs.fuchs.object.intern.item.cooldown;

import org.bukkit.inventory.ItemStack;

public interface IFuchsItemCooldown<T extends FICData<?>> {
    void onEnd();
    void onAction();
    ItemStack clearItem();
}
