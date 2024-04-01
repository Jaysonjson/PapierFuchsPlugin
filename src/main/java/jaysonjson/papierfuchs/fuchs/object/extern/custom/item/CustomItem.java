package jaysonjson.papierfuchs.fuchs.object.extern.custom.item;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.utility.FuchsAnsi;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;

public class CustomItem extends FuchsItem {

    CustomItemData customItemData;
    public CustomItem(CustomItemData customItemData) {
        super(customItemData.id, customItemData.type);
        this.customItemData = customItemData;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return customItemData.display_name;
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        if(FuchsUtility.rarityExists(customItemData.rarity)) {
            return FuchsUtility.getRarityByID(customItemData.rarity);
        } else {
            FuchsLog.warn("Item " + FuchsAnsi.CYAN + getID() + FuchsAnsi.YELLOW + " hat die Rarität " + FuchsAnsi.CYAN + customItemData.rarity + FuchsAnsi.YELLOW + ", diese Existiert aber nicht!");
        }
        return super.getDefaultRarity();
    }

    @Override
    public IItemUseType getItemUse() {
        if(FuchsUtility.useTypeExists(customItemData.use_type)) {
            return FuchsUtility.getUseTypeByID(customItemData.use_type);
        } else {
            FuchsLog.warn("Item " + FuchsAnsi.CYAN + getID() + FuchsAnsi.YELLOW + " hat das UseType  " + FuchsAnsi.CYAN + customItemData.use_type + FuchsAnsi.YELLOW + ", diese Existiert aber nicht!");
        }
        return super.getItemUse();
    }

    @Override
    public String getCurrencyType() {
        if(customItemData.strings.containsKey(CustomItemMap.CURRENCY_TYPE)) {
            return customItemData.strings.get(CustomItemMap.CURRENCY_TYPE);
        }
        return "";
    }

    @Override
    public float getCurrencyAmount() {
        if(customItemData.doubles.containsKey(CustomItemMap.CURRENCY_AMOUNT)) {
            return customItemData.floats.get(CustomItemMap.CURRENCY_AMOUNT);
        }
        return 0;
    }

    @Override
    public int getCustomModelData() {
        return customItemData.modelData;
    }
}
