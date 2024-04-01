package jaysonjson.papierfuchs.fuchs.io.data.server.data.db;

import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.io.database.FuchsDataBase;
import jaysonjson.papierfuchs.fuchs.io.database.FuchsTable;
import jaysonjson.papierfuchs.fuchs.io.database.FuchsTableEntry;
import jaysonjson.papierfuchs.fuchs.io.database.FuchsTableEntryBase;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsKey;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.entity.Player;

public class LockedItemDataBase {

    public final String key = "item";
    public FuchsTableEntry player = new FuchsTableEntry("player", FuchsTableEntryBase.VARCHAR32);
    public FuchsTable all = new FuchsTable("all", key, player);
    public FuchsDataBase dataBase = new FuchsDataBase(FileHandler.DATA_ROOT + "unique_items", all);

    public LockedItemDataBase() {
        dataBase.load();
    }

    public boolean isAvailable(FuchsKey item) {
        return (dataBase.getData(all, player, item.toString()) instanceof Integer);
    }

    public void replaceItem(FuchsItem item, Player player) {
        References.lockedItemDataBase.dataBase.createData(all, item.getID());
        References.lockedItemDataBase.dataBase.updateData(all, this.player, item.getID(), player.getUniqueId().toString());
    }

}
