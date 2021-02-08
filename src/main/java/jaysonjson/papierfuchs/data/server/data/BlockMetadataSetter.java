package jaysonjson.papierfuchs.data.server.data;

import jaysonjson.papierfuchs.data.FuchsLocation;
import org.bukkit.Material;

public class BlockMetadataSetter implements IMetadataSetter {
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
