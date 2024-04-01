package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.useable;

import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.old.inventories.backpack.BackPackInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BackPackItem extends FuchsItem {

    int inventorySize;
    FuchsItemData fuchsItemData;
    public BackPackItem(String id, Material material, IItemUseType itemUseType, int inventorySize) {
        super(id, material, itemUseType);
        this.inventorySize = inventorySize;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        if(fuchsItemData.uuid.equalsIgnoreCase("")) {
            fuchsItemData.uuid = generateUUID();
        }

        fuchsItemData.addToLore(inventorySize + " Slots");
        fuchsItemData.addToLore(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + fuchsItemData.uuid + "«");
        fuchsItemData.setItem(ChatColor.RESET + "Rucksack");
        return fuchsItemData.item;
    }


    @Override
    public void setDefaultPersistentData(FuchsMCItem fuchsMCItem, @Nullable Player player) {
        FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
        generalData.setProperty(FIGProperty.IS_BACKPACK, true);
        fuchsMCItem.generalData().set(generalData);
        super.setDefaultPersistentData(fuchsMCItem, player);
    }

    private String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        if(DataHandler.backPackExists(UUID.fromString(uuid))) {
            uuid = generateUUID();
        }
        return uuid;
    }

    @Override
    public void onItemUse(PlayerInteractEvent event) {
        BackPackInventory inventory = new BackPackInventory(event.getItem(), inventorySize);
        inventory.openInventory(event.getPlayer());
    }

    @Override
    public boolean isAbilityItem() {
        return true;
    }

    @Override
    public boolean stackAble() {
        return false;
    }

    @Override
    public int inventoryLimit() {
        return 1;
    }
}
