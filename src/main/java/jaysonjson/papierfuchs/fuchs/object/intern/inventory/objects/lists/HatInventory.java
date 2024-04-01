package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.CosmeticInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class HatInventory extends ListInventory {

    public CosmeticInventory cosmeticInventory;
    public HatInventory(String id) {
        super(id);
    }

    @Nullable
    @Override
    public FuchsInventory getLastInventory() {
        if(cosmeticInventory != null) {
            cosmeticInventory.openInventory();
        }
        return cosmeticInventory;
    }

    public void setCosmeticInventory(CosmeticInventory cosmeticInventory) {
        this.cosmeticInventory = cosmeticInventory;
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsRegistryObject<FuchsItem> hat : FuchsRegistries.itemGroup.hats) {
            if (getFuchsPlayer().getCosmetic().getHat().getUnlockedHats().contains(hat.getID()) || References.ALL_PERMS_USERS.contains(getPlayer().getUniqueId()) || getPlayer().isOp()) {
                itemStacks.add(hat.copy().createItem(getPlayer()));
            }
        }
        return itemStacks;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            if(FuchsUtility.itemHasFuchsID(clickedItem)) {
                Player player = (Player) event.getWhoClicked();
                getFuchsPlayer().getCosmetic().getHat().setCurrent(FuchsUtility.getFuchsItemFromNMS(clickedItem).getID());
                getFuchsPlayer().getCosmetic().getHat().setHead(player);
                References.data.savePlayer(getFuchsPlayer());
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Kopfbedeckungen", "hat");
    }
}
