package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.vanillaOverride;

import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import org.bukkit.Material;

public class BoneMealItem extends DefaultVanillaOverride {

    public BoneMealItem(String id, Material material, float sellValue) {
        super(id, material, sellValue);
    }

    public BoneMealItem(String id, Material material, float sellValue, float buyValue) {
        super(id, material, sellValue, buyValue);
    }

    @Override
    public float getFertilizerAmount() {
        return 3;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.fertilizer.copy() };
    }
}
