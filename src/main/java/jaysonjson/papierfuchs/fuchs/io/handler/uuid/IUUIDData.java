package jaysonjson.papierfuchs.fuchs.io.handler.uuid;

import java.io.File;
import java.util.UUID;

public interface IUUIDData {

    String path();

    default UUID generateUUID() {
        UUID uuid = UUID.randomUUID();
        if(new File(path() + ".json").exists()) {
            uuid = generateUUID();
        }
        return uuid;
    }

}
