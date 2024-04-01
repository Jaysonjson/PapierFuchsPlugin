package jaysonjson.papierfuchs.fuchs.io.data;

import java.util.UUID;

public interface IHasUUID {
    UUID getUUID();
    void setUUID(UUID uuid);
    UUID randomUUID();
}
