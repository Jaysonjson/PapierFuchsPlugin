package jaysonjson.papierfuchs.data.player.data;

import java.util.HashMap;
import java.util.UUID;

public class FuchsPlayerSpecial {
    public double alcohol = 0;
    public double max_alcohol = 4;
    public HashMap<UUID, Integer> bounties = new HashMap<>();

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getMaxAlcohol() {
        return max_alcohol;
    }

    public void setMaxAlcohol(double max_alcohol) {
        this.max_alcohol = max_alcohol;
    }

    public HashMap<UUID, Integer> getBounties() {
        return bounties;
    }

    public void setBounties(HashMap<UUID, Integer> bounties) {
        this.bounties = bounties;
    }

    public void increaseAlcohol(double amount) {
        alcohol += amount;
    }

    public void decreaseAlcohol(double amount) {
        alcohol -= amount;
    }

    public void resetAlcohol() {
        alcohol = 0;
    }
}
