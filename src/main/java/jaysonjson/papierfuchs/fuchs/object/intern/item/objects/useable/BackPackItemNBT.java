package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.useable;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.old.inventories.backpack.BackPackNBTInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class BackPackItemNBT extends FuchsItem {

    int inventorySize;
    FuchsItemData fuchsItemData;
    int modeldata;
    public BackPackItemNBT(String id, Material material, IItemUseType itemUseType, int inventorySize, int modeldata) {
        super(id, material, itemUseType);
        this.inventorySize = inventorySize;
        this.modeldata = modeldata;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addToLore(inventorySize + " Slots");
        return fuchsItemData.setItem();
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Rucksack", "backpack");
    }

    @Override
    public void setDefaultPersistentData(FuchsMCItem fuchsMCItem, @Nullable Player player) {
        FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
        generalData.setProperty(FIGProperty.IS_BACKPACK, true);
        fuchsMCItem.generalData().set(generalData);
        super.setDefaultPersistentData(fuchsMCItem, player);
    }

    @Override
    public boolean canCraft() {
        return false;
    }

    @Override
    public boolean canCraftMinecraft() {
        return false;
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event) {
        if(event.getHand().equals(EquipmentSlot.HAND)) {
            BackPackNBTInventory inventory = new BackPackNBTInventory(event.getItem(), inventorySize, this);
            inventory.openInventory(event.getPlayer());
        }
    }


    @Override
    public boolean stackAble() {
        return false;
    }

    @Override
    public int getCustomModelData() {
        return modeldata;
    }

    @Override
    public int inventoryLimit() {
        return 1;
    }
}
