package jaysonjson.papierfuchs.data.player.data;

import java.util.HashMap;
import java.util.UUID;

public class FuchsPlayerSpecial {
    public double alcohol = 0;
    public HashMap<UUID, Integer> bounties = new HashMap<>();

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

}
