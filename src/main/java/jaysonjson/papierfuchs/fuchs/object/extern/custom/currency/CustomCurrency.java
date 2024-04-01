package jaysonjson.papierfuchs.fuchs.object.extern.custom.currency;

import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;

public class CustomCurrency extends FuchsCurrency {

    public CustomCurrencyData customCurrencyData;
    public CustomCurrency(CustomCurrencyData customCurrencyData) {
        super(customCurrencyData.id);
        this.customCurrencyData = customCurrencyData;
    }


    @Override
    public String getSymbol() {
        return customCurrencyData.symbol;
    }
}
