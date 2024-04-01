package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.GasList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class GasItemCategory extends DefaultItemCategory {

    public GasItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        FuchsRegistries.gasses.values().forEach(fuchsGas -> {
            if(fuchsRegistry != null && !fuchsGas.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsRegistry.fuchsPlugin.getRegistryID())) {
                //return;
            } else
            if (fuchsGas.copy() != GasList.NONE.copy()) {
                ItemStack gasContainer = ItemList.gasContainer.copy().createItem(fuchsInventory.getPlayer(), null);
                FuchsMCItem fuchsMCItem = new FuchsMCItem(ItemList.gasContainer.copy(), fuchsInventory.getPlayer(), gasContainer);
                fuchsMCItem.getData().setGasID(fuchsGas.getID());
                fuchsMCItem.getData().setGasAmount(500f);
                itemStacks.add(fuchsMCItem.getItemStack());
            }
        });
    }

    @Nullable
    @Override
    public FuchsRegistryObject<? extends FuchsItem> getIcon() {
        return ItemList.gasContainer;
    }

}
