package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.KneadItemList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class KneadItemCategory extends DefaultItemCategory {

    public KneadItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        itemStacks.add(KneadItemList.knead.copy().createItem(fuchsInventory.getPlayer()));
    }

}
