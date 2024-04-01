package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

public class FuchsBlockItem extends FuchsItem {

    FuchsRegistryObject<? extends FuchsBlock> fuchsBlock;
    int modelData;
    public FuchsBlockItem(String id, Material material, IItemUseType itemUseType, FuchsRegistryObject<? extends FuchsBlock> fuchsBlock, int modelData) {
        super(id, material, itemUseType);
        this.fuchsBlock = fuchsBlock;
        this.modelData = modelData;
    }

    @Override
    public int getCustomModelData() {
        return modelData;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return fuchsBlock.copy().getDisplayName();
    }

    @Nullable
    @Override
    public FuchsRegistryObject<? extends FuchsBlock> asBlock() {
        return fuchsBlock;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.block.copy() };
    }
}
