package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventorySize;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.old.inventories.backpack.BackPackNBTInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.Nullable;

public class MoneyBag extends FuchsItem {

    public static int size = InventorySize.TWENTY_SEVEN.getAsInt();

    public MoneyBag(String id, Material material) {
        super(id, material, UseTypeList.USE_ABLE.copy());
    }

    @Override
    public void setDefaultPersistentData(FuchsMCItem fuchsMCItem, @Nullable Player player) {
        FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
        generalData.setProperty(FIGProperty.IS_BACKPACK, true);
        fuchsMCItem.generalData().set(generalData);
        super.setDefaultPersistentData(fuchsMCItem, player);
    }

    @Override
    public boolean stackAble() {
        return false;
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event) {
        if(event.getHand().equals(EquipmentSlot.HAND)) {
            BackPackNBTInventory inventory = new BackPackNBTInventory(event.getItem(), size, this);
            inventory.openInventory(event.getPlayer());
        }
    }

    @Override
    public boolean isMoneyBag() {
        return true;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString("Geldbeutel");
    }

    @Override
    public int inventoryLimit() {
        return 3;
    }
}
