package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class FuchsItemGasData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private float amount = 0f;
    private String gas = "";

    private ArrayList<String> gasses = new ArrayList<>();


    public FuchsItemGasData() {

    }

    public FuchsItemGasData(String gas, float amount)  {
        this.amount = amount;
        this.gas = gas;
    }

    public float getAmount() {
        return amount;
    }

    public String getGas() {
        return gas;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public ArrayList<String> getGasses() {
        return gasses;
    }

    public void setGasses(ArrayList<String> gasses) {
        this.gasses = gasses;
    }

    @Override
    public String toString() {
        return "FuchsItemGasData{" +
                "amount=" + amount +
                ", gas='" + gas + '\'' +
                ", gasses=" + gasses +
                '}';
    }
}
