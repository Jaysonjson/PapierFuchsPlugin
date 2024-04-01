package jaysonjson.papierfuchs.fuchs.object.intern.currency.objects;

import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;

public class FuchsDefaultCurrency extends FuchsCurrency {

    final String symbol;
    final boolean defaultCurrency;
    public FuchsDefaultCurrency(String id, String symbol, boolean defaultCurrency) {
        super(id);
        this.symbol = symbol;
        this.defaultCurrency = defaultCurrency;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean isDefault() {
        return defaultCurrency;
    }
}
