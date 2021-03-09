package jaysonjson.papierfuchs;


import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FileHandler;
import jaysonjson.papierfuchs.data.area.data.zArea;
import jaysonjson.papierfuchs.data.crafting.data.zCrafting;
import jaysonjson.papierfuchs.data.drop.data.zDrops;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

public class References {

    public static FuchsData data = new FuchsData();
    public static zDrops drops = new zDrops();
    public static zCrafting craftings = new zCrafting();
    public static ArrayList<zArea> areas = new ArrayList<>();

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

    public static void reloadAreas() {
        areas.clear();
        HashMap<String, zArea> areaHash = new HashMap<>();
        ArrayList<String> sortedHash = new ArrayList<>();
        for (File file : new File(FileHandler.AREA_DIR).listFiles()) {
            ///^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/
            try {
                zArea area = DataHandler.loadArea(UUID.fromString(file.getName().replaceAll(".json", "")));
                areaHash.put(area.getArrayName(), area);
                sortedHash.add(area.getArrayName());
                DataHandler.saveArea(area);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(sortedHash);
        Collections.reverse(sortedHash);
        for (String hash : sortedHash) {
            areas.add(areaHash.get(hash));
        }
    }
}
