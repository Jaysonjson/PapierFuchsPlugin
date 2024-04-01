package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.FuchsGas;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.GasList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventorySize;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.KneadItemList;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.LiquidList;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

@Deprecated
public class ItemListInventory extends ListInventory {

    FuchsItemCategory category = ItemCategoryList.all.copy();
    ItemCategoryInventory categoryInventory = null;
    FuchsRegistry fuchsRegistry;
    public ItemListInventory(String id) {
        super(id);
    }

    public FuchsItemCategory getCategory() {
        return category;
    }

    public void setCategory(FuchsItemCategory category) {
        this.category = category;
    }

    public void setCategoryInventory(ItemCategoryInventory categoryInventory) {
        this.categoryInventory = categoryInventory;
    }

    public void setFuchsRegistry(FuchsRegistry fuchsRegistry) {
        this.fuchsRegistry = fuchsRegistry;
    }

    public FuchsRegistry getFuchsRegistry() {
        return fuchsRegistry;
    }

    public ItemCategoryInventory getCategoryInventory() {
        return categoryInventory;
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        category.onListInventory(fuchsRegistry,this, itemStacks);
        if(!getCategory().getID().equalsIgnoreCase(ItemCategoryList.all.getID()) && !getCategory().getID().equalsIgnoreCase(ItemCategoryList.currency.getID()) && !getCategory().getID().equalsIgnoreCase(ItemCategoryList.projectile.getID())) {
        for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.items.values()) {
            for (FuchsItemCategory fuchsItemCategory : value.copy().getCategories()) {
                if(fuchsItemCategory.getID().equalsIgnoreCase(category.getID())) {
                    if(fuchsRegistry == null) {
                        itemStacks.add(value.copy().createItem(getPlayer()));
                    } else {
                        if(value.getFuchsPlugin().getRegistryID().equalsIgnoreCase(fuchsRegistry.fuchsPlugin.getRegistryID())) {
                            itemStacks.add(value.copy().createItem(getPlayer()));
                        }
                    }
                }
            }
        }
    }
        return itemStacks;
}

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            if (clickedItem.hasItemMeta()) {
                if (FuchsUtility.itemHasFuchsID(clickedItem)) {
                    event.getView().setCursor(clickedItem);
                    event.setCancelled(true);
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Nullable
    @Override
    public FuchsInventory getLastInventory() {
        if(categoryInventory != null) {
            categoryInventory.openInventory();
        }
        return categoryInventory;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c(ChatColor.GRAY, "Items");
    }

    @Override
    public void openInventory(int page) {
        if(pageContainer.pages.size() > page) {
            if (pageContainer.getPage(page).getStacks().length > 0) {
                setSize(getSizeFromInt(pageContainer.getPage(page).getStacks().length).getAsInt() + 9);
                setInventory(Bukkit.createInventory(getPlayer(), getSize(), Component.text(getDisplayName().get(getFuchsPlayer()))));
                currentPage = page;
                createPage(page);
                getPlayer().openInventory(getInventory());
            }
        }
    }

    @Override
    public InventorySize getSizeEnum() {
        return InventorySize.FIFTY_FOUR;
    }


}
