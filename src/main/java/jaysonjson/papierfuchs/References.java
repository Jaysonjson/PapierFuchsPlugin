package jaysonjson.papierfuchs;


import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.drop.data.zDrops;

public class References {

    public static zDrops drops;

    public static void reloadDrops() {
        drops = DataHandler.loadDrops();
    }

}
