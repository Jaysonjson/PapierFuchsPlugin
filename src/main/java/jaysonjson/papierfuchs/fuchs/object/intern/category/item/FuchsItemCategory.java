package jaysonjson.papierfuchs.fuchs.object.intern.category.item;

import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public abstract class FuchsItemCategory extends FuchsObject implements IFuchsItemCategory, IFuchsDisplayName {

    FuchsRegistryObject<? extends FuchsItem> icon = null;
    public FuchsItemCategory(String id) {
        super(id, RegistryType.ITEM_CATEGORY);
    }

    public FuchsItemCategory(String id, FuchsRegistryObject<? extends FuchsItem> icon) {
        super(id, RegistryType.ITEM_CATEGORY);
        this.icon = icon;
    }

    public void setIcon(FuchsRegistryObject<? extends FuchsItem> icon) {
        this.icon = icon;
    }

    @Override
    @Nullable
    public FuchsRegistryObject<? extends FuchsItem> getIcon() {
        return icon;
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }

    @Override
    public void onListInventory(FuchsRegistry fuchsRegistry, FuchsInventory fuchsInventory, ArrayList<ItemStack> itemStacks) {

    }
}
