package jaysonjson.papierfuchs;


import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.crafting.data.zCrafting;
import jaysonjson.papierfuchs.data.drop.data.zDrops;

public class References {

    public static FuchsData data = new FuchsData();
    public static zDrops drops = new zDrops();
    public static zCrafting craftings = new zCrafting();

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
