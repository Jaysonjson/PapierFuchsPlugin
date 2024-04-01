package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import org.bukkit.Material;

public class SlotRightItem extends FuchsItem {

    public SlotRightItem() {
        super("slot_right", Material.FEATHER);
    }


    @Override
    public int getCustomModelData() {
        return 114;
    }

    @Override
    public boolean showIdInLore() {
        return false;
    }

    @Override
    public boolean showInItemList() {
        return false;
    }
}
