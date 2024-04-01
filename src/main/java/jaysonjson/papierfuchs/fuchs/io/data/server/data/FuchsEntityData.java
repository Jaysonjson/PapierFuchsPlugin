package jaysonjson.papierfuchs.fuchs.io.data.server.data;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.data.IHasUUID;

import java.util.UUID;

public class FuchsEntityData implements IHasUUID {

    public UUID uuid;
    public FuchsLocation location;

    public FuchsEntityData(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID randomUUID() {
        return UUID.randomUUID();
    }
}
