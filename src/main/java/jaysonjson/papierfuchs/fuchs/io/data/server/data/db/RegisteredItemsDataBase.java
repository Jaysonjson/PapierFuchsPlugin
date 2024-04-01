package jaysonjson.papierfuchs.fuchs.io.data.server.data.db;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.io.database.FuchsDataBase;
import jaysonjson.papierfuchs.fuchs.io.database.FuchsTable;
import jaysonjson.papierfuchs.fuchs.io.database.FuchsTableEntry;
import jaysonjson.papierfuchs.fuchs.io.database.FuchsTableEntryBase;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;

import java.time.Instant;
import java.util.ArrayList;

public class RegisteredItemsDataBase {

    public final String key = "object";
    public FuchsTableEntry time = new FuchsTableEntry("time", FuchsTableEntryBase.VARCHAR32);
    public FuchsTableEntry keyId = new FuchsTableEntry("key", FuchsTableEntryBase.VARCHAR32);
    public FuchsTableEntry papierfuchs = new FuchsTableEntry("papierfuchs", FuchsTableEntryBase.VARCHAR32);
    public FuchsTableEntry type = new FuchsTableEntry("type", FuchsTableEntryBase.VARCHAR32);
    public FuchsTableEntry mod = new FuchsTableEntry("mod", FuchsTableEntryBase.VARCHAR32);
    public FuchsTableEntry permission = new FuchsTableEntry("permission", FuchsTableEntryBase.VARCHAR32);
    public FuchsTable all = new FuchsTable("all", key, mod, keyId, type, papierfuchs, time, permission);
    public FuchsDataBase dataBase;

    public ArrayList<FuchsTable> tables = new ArrayList<>();

    public RegisteredItemsDataBase()  {
        tables.add(all);
    }

    public boolean exists(FuchsObject item) {
        return (dataBase.getData(all, keyId, item.createObjectId()) instanceof Integer);
    }

    public <T extends FuchsObject> void add(FuchsRegistry fuchsRegistry, T object) {
        String objectId = object.createObjectId();
        for (FuchsTable table : tables) {
            if(table.getName().equalsIgnoreCase(fuchsRegistry.getFuchsPlugin().getRegistryID()) || table.getName().equalsIgnoreCase("all")) {
                dataBase.createData(table, objectId);
                dataBase.updateData(table, keyId, objectId, object.getID());
                dataBase.updateData(table, type, objectId, object.getType().toString());
                dataBase.updateData(table, papierfuchs, objectId, PapierFuchs.getBuild());
                dataBase.updateData(table, time, objectId, Instant.now().toString());
                dataBase.updateData(table, permission, objectId, object.getPermission());
                if(table.getName().equalsIgnoreCase("all")) {
                    dataBase.updateData(table, mod, objectId, fuchsRegistry.getFuchsPlugin().getRegistryID());
                }
            }
        }
    }

    public FuchsTable createTable(FuchsRegistry fuchsRegistry) {
        FuchsTable fuchsTable = new FuchsTable(fuchsRegistry.getFuchsPlugin().getRegistryID(), key, keyId, type, papierfuchs, time, permission);
        tables.add(fuchsTable);
        return fuchsTable;
    }

    public void createDataBase() {
        dataBase = new FuchsDataBase(FileHandler.SERVER_DIR + "items", tables);
        dataBase.load();
    }

}
