package jaysonjson.papierfuchs.data.server.data;

import java.util.UUID;

public class EntityMetadataSetter implements IMetadataSetter {
    public UUID uuid;
    public String key;
    public Object value;

    public EntityMetadataSetter(UUID uuid, String key, Object value) {
        this.uuid = uuid;
        this.key = key;
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
