package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventorySize;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.HatInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists.PetInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CosmeticInventory extends FuchsInventory {

    public CosmeticInventory(String id) {
        super(id);
    }

    @Override
    public void setContents() {
        fillWithGlass();
        getInventory().setItem(10, FuchsUtility.createInventoryStackWithTag(Material.AXOLOTL_BUCKET, 1, FuchsLanguageString.c("Haustier", "pet").get(getFuchsPlayer()), "pet", ""));
        getInventory().setItem(11, FuchsUtility.createInventoryStackWithTag(Material.LEATHER_HELMET, 1, FuchsLanguageString.c("Kopfbedeckung", "hat").get(getFuchsPlayer()), "hat", ""));
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if (FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            NBTTagCompound tag = FuchsUtility.getItemTag(event.getCurrentItem());
            if (tag.hasKey("pet")) {
                PetInventory petInventory = InventoryList.pet.copy();
                petInventory.setCosmeticInventory(this);
                petInventory.createAndOpen(getPlayer());
            }
            if (tag.hasKey("hat")) {
                HatInventory hatInventory = InventoryList.hat.copy();
                hatInventory.setCosmeticInventory(this);
                hatInventory.createAndOpen(getPlayer());
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Kosmetik", "cosmetic");
    }

    @Override
    public InventorySize getSizeEnum() {
        return InventorySize.TWENTY_SEVEN;
    }
}
