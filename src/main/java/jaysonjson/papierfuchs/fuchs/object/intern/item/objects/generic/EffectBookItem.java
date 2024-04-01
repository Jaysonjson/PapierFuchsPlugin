package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces.IFuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class EffectBookItem extends FuchsItem {

    public EffectBookItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.setItem("Effektbuch");
        return fuchsItemData.item;
    }

    @Override
    @Deprecated
    public @Nullable IFuchsItem createCopy() {
        return new EffectBookItem(getID(), getMaterial(), getItemUse());
    }

    @Override
    public boolean isEffectBook() {
        return true;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.effect.copy() };
    }

}
