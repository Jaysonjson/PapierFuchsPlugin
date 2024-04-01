package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.FuchsPlayerSettingsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.language.FuchsLanguage;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class LanguageListInventory extends ListInventory {

    FuchsPlayerSettingsInventory fuchsPlayerSettings;
    public LanguageListInventory(String id) {
        super(id);
    }

    public FuchsPlayerSettingsInventory getFuchsPlayerSettings() {
        return fuchsPlayerSettings;
    }

    public void setFuchsPlayerSettings(FuchsPlayerSettingsInventory fuchsPlayerSettings) {
        this.fuchsPlayerSettings = fuchsPlayerSettings;
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsRegistryObject<FuchsLanguage> value : FuchsRegistries.languages.values()) {
            FuchsMCItem fuchsMCItem = new FuchsMCItem(value.copy().getItem(), getPlayer(), value.copy().getItem().createItem(getPlayer()));
            fuchsMCItem.changeStringTag("Language", value.getID());
            itemStacks.add(fuchsMCItem.getItemStack());
        }
        return itemStacks;
    }

    @Override
    public void setContents() {
        super.setContents();
        if(fuchsPlayerSettings != null) {
            getInventory().setItem(getSize() - 5, FuchsUtility.createInventoryStack(Material.FEATHER, 1, "Zurück"));
        }
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            if (clickedItem.hasItemMeta()) {
                net.minecraft.world.item.ItemStack nmsCopy = FuchsUtility.createNMSCopy(clickedItem);
                NBTTagCompound tag = FuchsUtility.getItemTag(nmsCopy);
                if (tag.hasKey("Language")) {
                    getFuchsPlayer().setLanguage(FuchsUtility.getFuchsLanguageByID(tag.getString("Language")));
                    FuchsUtility.updateInventory((Player) event.getWhoClicked());
                    event.getWhoClicked().closeInventory();
                    if(fuchsPlayerSettings != null) {
                        fuchsPlayerSettings.setContents();
                        fuchsPlayerSettings.showMsg = true;
                        fuchsPlayerSettings.openInventory();
                    }
                }
                if (clickedItem.getItemMeta().getDisplayName().equals("Zurück")) {
                    fuchsPlayerSettings.setContents();
                    fuchsPlayerSettings.showMsg = true;
                    fuchsPlayerSettings.openInventory();
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }
}
