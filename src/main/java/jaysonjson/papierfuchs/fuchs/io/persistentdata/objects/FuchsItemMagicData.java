package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FuchsItemKey;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;

public class FuchsItemMagicData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private HashMap<FuchsItemKey, Float> keys = new HashMap<>();
    private String type = "";
    public FuchsItemMagicData() {

    }

    public FuchsItemMagicData(FuchsItem fuchsItem) {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setKey(FuchsItemKey key, float value) {
        if(value < 1) {
            getKeys().remove(key);
        } else {
            getKeys().put(key, value);
        }
    }

    public float getKey(FuchsItemKey key) {
        if(getKeys().containsKey(key)) {
            return getKeys().get(key);
        }
        return 0;
    }

    public HashMap<FuchsItemKey, Float> getKeys() {
        return keys;
    }

    public void setKeys(HashMap<FuchsItemKey, Float> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "FuchsItemMagicData{" +
                "keys=" + keys +
                '}';
    }
}
