package jaysonjson.papierfuchs;


import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.crafting.data.zCrafting;
import jaysonjson.papierfuchs.data.drop.data.zDrops;

import java.util.ArrayList;
import java.util.UUID;

public class References {

    public static FuchsData data = new FuchsData();
    public static zDrops drops = new zDrops();
    public static zCrafting craftings = new zCrafting();

    public static ArrayList<UUID> ALL_PERMS_USERS = new ArrayList<>();

    public static void reloadDrops() {
        drops = DataHandler.loadDrops();
    }
    public static void reloadCraftings() {
        craftings = DataHandler.loadCrafting();
    }

    public static void loadServerData() {
        data.loadServer();
    }
}
