package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EntityItemCategory extends DefaultItemCategory {

    public EntityItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        /*FuchsRegistries.entities.values().forEach(fuchsEntity -> {
            if(fuchsRegistry != null && !fuchsEntity.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsRegistry.fuchsPlugin.getRegistryID())) {

            } else {
                itemStacks.add(FuchsUtility.createSpawnEgg(fuchsEntity.copy(), fuchsInventory.getPlayer()));
            }
        });*/
    }


}
