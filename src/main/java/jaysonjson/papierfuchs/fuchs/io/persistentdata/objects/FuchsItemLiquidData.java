package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class FuchsItemLiquidData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private float amount = 0;
    private String liquid = "";

    private ArrayList<String> liquids = new ArrayList<>();

    public FuchsItemLiquidData() {

    }

    public FuchsItemLiquidData(String liquid, float amount) {
        this.amount = amount;
        this.liquid = liquid;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setLiquid(String liquid) {
        this.liquid = liquid;
    }

    public float getAmount() {
        return amount;
    }

    public String getLiquid() {
        return liquid;
    }

    public ArrayList<String> getLiquids() {
        return liquids;
    }

    public void setLiquids(ArrayList<String> liquids) {
        this.liquids = liquids;
    }

    @Override
    public String toString() {
        return "FuchsItemLiquidData{" +
                "amount=" + amount +
                ", liquid='" + liquid + '\'' +
                ", liquids=" + liquids +
                '}';
    }
}
