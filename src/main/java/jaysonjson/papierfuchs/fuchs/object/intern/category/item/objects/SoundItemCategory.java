package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.sound.FuchsSound;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SoundItemCategory extends DefaultItemCategory {

    public SoundItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        for (FuchsRegistryObject<FuchsSound> value : FuchsRegistries.sounds.values()) {
            if(fuchsRegistry != null && value.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsRegistry.fuchsPlugin.getRegistryID())) {
            } else {
                itemStacks.add(FuchsUtility.createSoundDisc(value.copy(), fuchsInventory.getPlayer()));
            }
        }
    }
}
