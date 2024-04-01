package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class CurrencyItemCategory extends DefaultItemCategory{

    public CurrencyItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.itemGroup.currenciesSorted.values()) {
            if(fuchsRegistry == null) {
                itemStacks.add(value.copy().createItem(fuchsInventory.getPlayer()));
            } else {
                if(value.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsRegistry.fuchsPlugin.getRegistryID())) {
                    itemStacks.add(value.copy().createItem(fuchsInventory.getPlayer()));
                }
            }
        }
    }
}
