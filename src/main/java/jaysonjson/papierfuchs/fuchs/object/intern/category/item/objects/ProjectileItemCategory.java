package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ProjectileItemCategory extends DefaultItemCategory {

    public ProjectileItemCategory(String id, FuchsLanguageString languageString) {
        super(id, languageString);
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {
        for (FuchsRegistryObject<FuchsProjectile> value : FuchsRegistries.projectiles.values()) {
            if(fuchsRegistry == null) {
                itemStacks.add(FuchsUtility.createBowProjectiles(value.copy(), fuchsInventory.getPlayer()));
                itemStacks.add(value.copy().asItem(fuchsInventory.getPlayer(), null));
            } else {
                if(value.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsRegistry.fuchsPlugin.getRegistryID())) {
                    itemStacks.add(FuchsUtility.createBowProjectiles(value.copy(), fuchsInventory.getPlayer()));
                    itemStacks.add(value.copy().asItem(fuchsInventory.getPlayer(), null));
                }
            }
        }
    }
}
