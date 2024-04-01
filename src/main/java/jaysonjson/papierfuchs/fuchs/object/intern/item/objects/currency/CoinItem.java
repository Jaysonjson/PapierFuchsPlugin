package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.currency;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import org.bukkit.Material;

public class CoinItem extends FuchsItem {

    final FuchsLanguageString fuchsLanguageString;
    final int modeldata;
    final float currencyamount;
    final FuchsCurrency currency;
    public CoinItem(String id, Material material, FuchsCurrency currency, FuchsLanguageString fuchsLanguageString, int modeldata, float currencyamount) {
        super(id, material, UseTypeList.CURRENCY.copy());
        this.fuchsLanguageString = fuchsLanguageString;
        this.modeldata = modeldata;
        this.currencyamount = currencyamount;
        this.currency = currency;
    }

    @Override
    public String getCurrencyType() {
        return currency.getID();
    }

    @Override
    public float getCurrencyAmount() {
        return currencyamount;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return fuchsLanguageString;
    }

    @Override
    public int getCustomModelData() {
        return modeldata;
    }

    @Override
    public boolean isCurrency() {
        return true;
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.none.copy();
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.currency.copy() };
    }

}

