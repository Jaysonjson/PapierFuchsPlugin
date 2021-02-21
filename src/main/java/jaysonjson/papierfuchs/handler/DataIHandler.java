package jaysonjson.papierfuchs.handler;

import jaysonjson.papierfuchs.data.FileHandler;
import jaysonjson.papierfuchs.handler.uuid.UUIDData;

public class DataIHandler {

    public static UUIDData AREA_UUID = new UUIDData(FileHandler.AREA_DIR);
    @Deprecated
    public static UUIDData BACKPACK_UUID = new UUIDData(FileHandler.BACKPACK_DIR);

}
