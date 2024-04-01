package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemModListInventory extends ListInventory {

    public ItemModListInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> stacks = new ArrayList<>();
        ItemStack itemStack = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("Alles");
        itemStack.setItemMeta(itemMeta);
        stacks.add(itemStack);
        for (FuchsRegistry registry : FuchsRegistries.registries) {
            stacks.add(createRegistryStack(registry));
        }
        return stacks;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            if(clickedItem.hasItemMeta()) {
                String displayName = clickedItem.getItemMeta().getDisplayName();
                if(displayName.equalsIgnoreCase("Alles")) {
                    ItemCategoryInventory categoryInventory = InventoryList.itemCategoryList.copy();
                    categoryInventory.setFuchsRegistry(null);
                    categoryInventory.setModListInventory(this);
                    categoryInventory.createAndOpen(getPlayer());
                } else {
                    if(FuchsUtility.fuchsRegistryExists(displayName)) {
                        ItemCategoryInventory categoryInventory = InventoryList.itemCategoryList.copy();
                        categoryInventory.setFuchsRegistry(FuchsUtility.getRegistryById(displayName));
                        categoryInventory.setModListInventory(this);
                        categoryInventory.createAndOpen(getPlayer());
                    }
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

    public ItemStack createRegistryStack(FuchsRegistry fuchsRegistry) {
        ItemStack itemStack;
        ItemMeta itemMeta;
        if(fuchsRegistry.fuchsPlugin.getIcon() != null) {
            itemStack = new ItemStack(fuchsRegistry.fuchsPlugin.getIcon().getMaterial());
            itemMeta = itemStack.getItemMeta();
            itemMeta.setCustomModelData(fuchsRegistry.fuchsPlugin.getIcon().getCustomModelData());
        } else {
            itemStack = new ItemStack(Material.AIR);
            itemMeta = itemStack.getItemMeta();
            int i = 0;
            while (itemStack.getType().equals(Material.AIR)) {
                i++;
                for (FuchsRegistryObject<? extends FuchsObject> object : fuchsRegistry.objects) {
                    if (object.copy() instanceof FuchsItem) {
                        if (PapierFuchs.random.nextInt(15) == 1) {
                            itemStack.setType(((FuchsItem) object.copy()).getMaterial());
                            itemMeta = itemStack.getItemMeta();
                            itemMeta.setCustomModelData(((FuchsItem) object.copy()).getCustomModelData());
                        }
                    }
                }
                if(i >= 500) {
                    break;
                }
            }
        }
        itemMeta.setDisplayName(fuchsRegistry.fuchsPlugin.getRegistryID());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Mods");
    }
}
