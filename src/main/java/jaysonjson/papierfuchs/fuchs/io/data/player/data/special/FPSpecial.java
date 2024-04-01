package jaysonjson.papierfuchs.fuchs.io.data.player.data.special;

public class FPSpecial {

    private FPSAlcohol alcohol = new FPSAlcohol();
    private FPSDehydration dehydration = new FPSDehydration();
    private double bounty = 0;
    private FPSHealth health = new FPSHealth();


    public FPSHealth getHealth() {
        return health;
    }

    public void setHealth(FPSHealth health) {
        this.health = health;
    }

    public boolean hasBounty() {
        return bounty > 0;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }

    public FPSAlcohol getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(FPSAlcohol alcohol) {
        this.alcohol = alcohol;
    }

    public FPSDehydration getDehydration() {
        return dehydration;
    }

    public void setDehydration(FPSDehydration dehydration) {
        this.dehydration = dehydration;
    }
}
