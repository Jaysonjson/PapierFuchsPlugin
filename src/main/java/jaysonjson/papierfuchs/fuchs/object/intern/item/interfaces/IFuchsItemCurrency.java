package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import jaysonjson.papierfuchs.fuchs.object.intern.currency.CurrencyList;

public interface IFuchsItemCurrency {
    default float getCurrencyAmount() {
        return 0;
    }
    default String getCurrencyType() {
        return "";
    }
    default float getDefaultBuyValue() {
        return 0;
    }
    default float getDefaultSellValue() {
        return 0;
    }
    default boolean isCurrency() {
        return !getCurrencyType().equals("");
    }

    default String getDefaultTradeCurrency() {
        return CurrencyList.FUCHSO.getID();
    }
}
