package jaysonjson.papierfuchs.handler;

import jaysonjson.papierfuchs.handler.uuid.IUUIDData;

import java.io.File;
import java.util.UUID;

public class UUIDHandler {

    public static UUID generateUUID(IUUIDData uuidData) {
        UUID uuid = UUID.randomUUID();
        if(new File(uuidData.path() + ".json").exists()) {
            uuid = generateUUID(uuidData);
        }
        return uuid;
    }

}
