package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.io.Serial;
import java.io.Serializable;

public class FuchsItemCurrencyData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private String currencyType = "";
    private float currencyAmount = 0f;
    private float sellValue = 0f;
    private float buyValue = 0f;
    private String tradeCurrency = "";

    public FuchsItemCurrencyData() {

    }

    public FuchsItemCurrencyData(FuchsItem fuchsItem) {
        this.currencyType = fuchsItem.getCurrencyType();
        this.currencyAmount = fuchsItem.getCurrencyAmount();
        this.sellValue = fuchsItem.getDefaultSellValue();
        this.buyValue = fuchsItem.getDefaultBuyValue();
        this.tradeCurrency = fuchsItem.getDefaultTradeCurrency();
    }

    public float getBuyValue() {
        return buyValue;
    }

    public float getCurrencyAmount() {
        return currencyAmount;
    }

    public float getSellValue() {
        return sellValue;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setBuyValue(float buyValue) {
        this.buyValue = buyValue;
        if(getBuyValue() < 0) {
            setBuyValue(0);
        }
    }

    public void setCurrencyAmount(float currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public void setSellValue(float sellValue) {
        this.sellValue = sellValue;
        if(getSellValue() < 0) {
            setSellValue(0);
        }
    }

    public boolean hasCurrency() {
        return !getCurrencyType().equals("");
    }

    public boolean isCurrencyEmpty() {
        return getCurrencyAmount() <= 0;
    }

    public String getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(String tradeCurrency) {
        this.tradeCurrency = tradeCurrency;
    }

    @Override
    public String toString() {
        return "FuchsItemCurrencyData{" +
                "currencyType='" + currencyType + '\'' +
                ", currencyAmount=" + currencyAmount +
                ", sellValue=" + sellValue +
                ", buyValue=" + buyValue +
                ", tradeCurrency='" + tradeCurrency + '\'' +
                '}';
    }
}
