package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.FuchsMaterial;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import org.bukkit.Material;

public class FuchsFoodItem extends FuchsItem {

    int foodLevel;
    FuchsLanguageString displayName;
    int modeldata = FuchsMaterial.POTATO.getFallbackModelData();
    public FuchsFoodItem(String id, Material material, int foodLevel, int modeldata, FuchsLanguageString displayName) {
        super(id, material, UseTypeList.FOOD.copy());
        this.foodLevel = foodLevel;
        this.displayName = displayName;
        this.modeldata = modeldata;
    }

    public FuchsFoodItem(String id, Material material, int foodLevel, FuchsLanguageString displayName) {
        super(id, material, UseTypeList.FOOD.copy());
        this.foodLevel = foodLevel;
        this.displayName = displayName;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return displayName;
    }

    @Override
    public int getFoodLevel() {
        return foodLevel;
    }

    @Override
    public boolean isFood() {
        return true;
    }

    @Override
    public int getCustomModelData() {
        return modeldata;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.food.copy() };
    }
}
