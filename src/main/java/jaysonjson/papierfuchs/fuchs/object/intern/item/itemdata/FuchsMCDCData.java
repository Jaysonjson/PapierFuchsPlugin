package jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

public class
FuchsMCDCData<T> {

    private final T def;
    private final FuchsMCItem mcItem;
    private final NamespacedKey key;
    private final PersistentDataType<byte[], Object> persistentData;

    public FuchsMCDCData(T def, NamespacedKey key, PersistentDataType<byte[], Object> persistentData, FuchsMCItem mcItem) {
        this.def = def;
        this.mcItem = mcItem;
        this.key = key;
        this.persistentData = persistentData;
    }

    public T get() {
        if(has()) {
            try {
                return (T) mcItem.getDataContainer().get(key, persistentData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return def;
    }

    public boolean has() {
        if(mcItem.getDataContainer() != null) {
            return mcItem.getDataContainer().has(key, persistentData);
        }
        return false;
    }

    public void set(T data) {
        if(mcItem.getDataContainer() != null) {
            mcItem.getDataContainer().set(key, persistentData, data);
            mcItem.setOriginalItemMeta();
        }
    }

    public NamespacedKey getKey() {
        return key;
    }

    public FuchsMCItem getMcItem() {
        return mcItem;
    }

    public PersistentDataType<byte[], Object> getPersistentData() {
        return persistentData;
    }

    public T getDef() {
        return def;
    }
}
