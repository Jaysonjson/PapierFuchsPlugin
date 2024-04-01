package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.useable;

import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.admin.MakeVehicleStick;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChairStickItem extends MakeVehicleStick {

    public ChairStickItem() {
        super("chair_stick", UseTypeList.USE_ABLE.copy());
    }

    @Override
    public void onItemRightClickBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        super.onItemRightClickBlock(event);
        player.getEquipment().setItemInMainHand(FuchsUtility.damageFuchsItem(player, new FuchsMCItem(event.getItem())));
    }

    @Override
    public int getMaxDurability() {
        return 15;
    }
}
