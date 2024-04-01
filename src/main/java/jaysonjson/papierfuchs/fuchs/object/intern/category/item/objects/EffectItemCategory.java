package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EffectItemCategory extends DefaultItemCategory {

    public EffectItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        FuchsRegistries.effects.values().forEach(effect -> {
            if(fuchsRegistry != null && !effect.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsRegistry.fuchsPlugin.getRegistryID())) {

            } else {
                itemStacks.add(FuchsUtility.createEffectBook(effect.copy(), fuchsInventory.getPlayer()));
            }
        });
    }
}
