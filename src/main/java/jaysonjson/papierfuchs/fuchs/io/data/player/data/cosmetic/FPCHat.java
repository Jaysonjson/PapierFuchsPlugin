package jaysonjson.papierfuchs.fuchs.io.data.player.data.cosmetic;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FPCHat {
    private final ArrayList<String> unlocked_hats = new ArrayList<>();
    private String current = "";

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public ArrayList<String> getUnlockedHats() {
        return unlocked_hats;
    }

    public void setHead(Player player) {
        FuchsItem fuchsItem = FuchsUtility.getFuchsItemByID(getCurrent());
        if(fuchsItem != null) {
            if(fuchsItem.canPutOnHead()) {
                if(player.getEquipment() == null || player.getEquipment().getHelmet() == null) {
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(fuchsItem, player);
                    FuchsItemGeneralData fuchsItemGeneralData = fuchsMCItem.generalData().get();
                    fuchsItemGeneralData.setProperty(FIGProperty.CAN_DROP, false);
                    fuchsMCItem.setAmount(1);
                    fuchsMCItem.changeBooleanTag(ItemNBT.COSMETIC_SET_HAT, true);
                    fuchsMCItem.generalData().set(fuchsItemGeneralData);
                    player.getEquipment().setHelmet(fuchsMCItem.getItemStack());
                }
            }
        }
    }

    public void unlockHat(String id) {
        if(!hasHatUnlocked(id)) {
            unlocked_hats.add(id);
        }
    }

    public boolean hasHatUnlocked(String id) {
        return unlocked_hats.contains(id);
    }
}
