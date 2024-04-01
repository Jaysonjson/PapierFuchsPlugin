package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.holder;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FuchsItemKey;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class FuchsItemStats implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private HashMap<FuchsItemKey, Integer> requirements = new HashMap<>();
    private HashMap<FuchsItemKey, Integer> additions = new HashMap<>();
    private HashMap<FuchsItemKey, Integer> subtractions = new HashMap<>();

    public FuchsItemStats() {

    }

    public FuchsItemStats(FuchsItem fuchsItem) {
        for (FuchsItemKey key : fuchsItem.getStatAdditions().keySet()) {
            setAddition(key, fuchsItem.getStatAdditions().get(key));
        }

        for (FuchsItemKey key : fuchsItem.getStatSubtractions().keySet()) {
            setSubtraction(key, fuchsItem.getStatSubtractions().get(key));
        }

        for (FuchsItemKey key : fuchsItem.getStatRequirements().keySet()) {
            setRequirement(key, fuchsItem.getStatRequirements().get(key));
        }
    }

    public HashMap<FuchsItemKey, Integer> getAdditions() {
        return additions;
    }

    public HashMap<FuchsItemKey, Integer> getRequirements() {
        return requirements;
    }

    public HashMap<FuchsItemKey, Integer> getSubtractions() {
        return subtractions;
    }

    public void setAddition(FuchsItemKey key, int value) {
        if(value < 1) {
            additions.remove(key);
        } else {
            additions.put(key, value);
        }
    }

    public void setRequirement(FuchsItemKey key, int value) {
        if(value < 1) {
            requirements.remove(key);
        } else {
            requirements.put(key, value);
        }
    }

    public void setSubtraction(FuchsItemKey key, int value) {
        if(value < 1) {
            subtractions.remove(key);
        } else {
            subtractions.put(key, value);
        }
    }

    public int getAddition(FuchsItemKey key) {
        if(additions.containsKey(key)) {
            return additions.get(key);
        }
        return 0;
    }

    public int getRequirement(FuchsItemKey key) {
        if(requirements.containsKey(key)) {
            return requirements.get(key);
        }
        return 0;
    }

    public int getSubtractions(FuchsItemKey key) {
        if(requirements.containsKey(key)) {
            return requirements.get(key);
        }
        return 0;
    }

    public void setAdditions(HashMap<FuchsItemKey, Integer> additions) {
        this.additions = additions;
    }

    public void setRequirements(HashMap<FuchsItemKey, Integer> requirements) {
        this.requirements = requirements;
    }

    public void setSubtractions(HashMap<FuchsItemKey, Integer> subtractions) {
        this.subtractions = subtractions;
    }

    @Override
    public String toString() {
        return "FuchsItemStats{" +
                "requirements=" + requirements +
                ", additions=" + additions +
                ", subtractions=" + subtractions +
                '}';
    }
}
