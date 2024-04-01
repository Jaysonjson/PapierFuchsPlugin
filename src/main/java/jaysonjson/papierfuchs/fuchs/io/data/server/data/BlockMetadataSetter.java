package jaysonjson.papierfuchs.fuchs.io.data.server.data;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import org.bukkit.Material;

import java.io.Serializable;

public class BlockMetadataSetter implements IMetadataSetter, Serializable {
    public FuchsLocation location;
    public Material type;
    public String key;
    public Object value;

    public BlockMetadataSetter(FuchsLocation location, Material type, String key, Object value) {
        this.location = location;
        this.key = key;
        this.type = type;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
