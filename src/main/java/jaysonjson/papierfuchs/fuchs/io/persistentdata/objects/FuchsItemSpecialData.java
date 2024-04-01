package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.fuchs.object.intern.currency.CurrencyList;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class FuchsItemSpecialData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private UUID bountyOwner;
    private double bountyAmount = 0.0;
    private String bountyCurrency = CurrencyList.FUCHSO.getID();
    private String soundKey = "";

    public String getSoundKey() {
        return soundKey;
    }

    public void setSoundKey(String soundKey) {
        this.soundKey = soundKey;
    }

    public boolean hasSound() {
        return !soundKey.equalsIgnoreCase("");
    }

    public double getBountyAmount() {
        return bountyAmount;
    }

    public UUID getBountyOwner() {
        return bountyOwner;
    }

    public void setBountyOwner(UUID bountyOwner) {
        this.bountyOwner = bountyOwner;
    }

    public void setBountyAmount(double bountyAmount) {
        this.bountyAmount = bountyAmount;
    }

    public boolean hasBounty() {
        return bountyOwner != null;
    }

    public String getBountyCurrency() {
        return bountyCurrency;
    }

    public void setBountyCurrency(String bountyCurrency) {
        this.bountyCurrency = bountyCurrency;
    }

}
