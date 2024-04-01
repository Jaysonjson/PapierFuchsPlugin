package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.LiquidList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class LiquidItemCategory extends DefaultItemCategory {

    public LiquidItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        FuchsRegistries.liquids.values().forEach(fuchsLiquid -> {
            if(fuchsRegistry != null && !fuchsLiquid.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsRegistry.fuchsPlugin.getRegistryID())) {
            } else {
                if (fuchsLiquid.copy() != LiquidList.NONE.copy()) {
                    ItemStack liquidContainer = ItemList.liquidContainer.copy().createItem(fuchsInventory.getPlayer(), null);
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(ItemList.liquidContainer.copy(), fuchsInventory.getPlayer(), liquidContainer);
                    fuchsMCItem.getData().setLiquidID(fuchsLiquid.getID());
                    fuchsMCItem.getData().setLiquidAmount(500f);
                    itemStacks.add(fuchsMCItem.getItemStack());
                }
            }
        });
    }
}
