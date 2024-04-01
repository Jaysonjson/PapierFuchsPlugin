package jaysonjson.papierfuchs.fuchs.utility;

import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsData;
import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsArea;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.data.zCrafting;
import jaysonjson.papierfuchs.fuchs.io.data.drop.data.zDrops;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.db.RegisteredItemsDataBase;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.db.LockedItemDataBase;
import jaysonjson.papierfuchs.fuchs.object.intern.group.FuchsGroup;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.*;

public class References {

    public static FuchsData data = new FuchsData();
    public static zDrops drops = new zDrops();
    public static zCrafting craftings = new zCrafting();
    public static ArrayList<UUID> pets = new ArrayList<>();
    public static HashMap<UUID, FuchsGroup> groups = new HashMap<>();
    public static HashMap<UUID, ArrayList<FuchsArea>> nextAreas = new HashMap<>();
    public static LockedItemDataBase lockedItemDataBase = new LockedItemDataBase();
    public static RegisteredItemsDataBase registeredItemsDataBase = new RegisteredItemsDataBase();

    public static ArrayList<UUID> ALL_PERMS_USERS = new ArrayList<>();

    public static void reloadDrops() {
        drops = DataHandler.loadDrops();
    }
    @Deprecated
    public static void reloadCraftings() {
        craftings = DataHandler.loadCrafting();
    }

    public static void loadServerData() {
        data.loadServer();
    }

    public static void loadData() {
        data.loadServer();
        data.loadPapierFuchs();
        for (File file : new File(FileHandler.PLAYER_DIR).listFiles()) {
            String name = file.getName().replaceAll(".json", "");
            if(name.length() == 36) {
                data.loadPlayer(UUID.fromString(name));
                FuchsPlayer fuchsPlayer = data.getPlayer(name);
                fuchsPlayer.setPlayer(Bukkit.getOfflinePlayer(UUID.fromString(name)).getPlayer());
                if(fuchsPlayer.getPlayer() != null) {
                    FuchsLog.log(FuchsAnsi.GREEN + "FuchsSpieler " + FuchsAnsi.CYAN + fuchsPlayer.getPlayer().getName() + FuchsAnsi.GREEN + " (" + FuchsAnsi.CYAN + fuchsPlayer.getUUID() + FuchsAnsi.GREEN + ") geladen!");
                } else {
                    FuchsLog.log(FuchsAnsi.GREEN + "FuchsSpieler " + FuchsAnsi.CYAN + fuchsPlayer.getUUID() + " (" + fuchsPlayer.getPlayerName() + ")" + FuchsAnsi.GREEN + " geladen!");
                }
            }
        }

        for (File file : new File(FileHandler.GUILD_DIR).listFiles()) {
            String name = file.getName().replaceAll(".json", "");
            if(name.length() == 36) {
                data.loadGuild(UUID.fromString(name));
                FuchsLog.log(FuchsAnsi.GREEN + "Guilde " + FuchsAnsi.CYAN + data.getGuild(UUID.fromString(name)).getName() + " (" + data.getGuild(UUID.fromString(name)).getUUID() + ")" + FuchsAnsi.GREEN + " geladen!");
            }
        }

        for (File file : new File(FileHandler.WORLD_DIR).listFiles()) {
            String name = file.getName().replaceAll(".json", "");
            if(name.length() == 36) {
                data.loadWorld(UUID.fromString(name));
            }
        }

        for (File file : new File(FileHandler.UNEDITABLE_WORLD).listFiles()) {
            String name = file.getName().replaceAll(".json", "");
            if(name.length() == 36) {
                data.loadByteWorld(UUID.fromString(name));
            }
        }

        for (File file : new File(FileHandler.AREA_DIR).listFiles()) {
            String name = file.getName().replaceAll(".json", "");
            if(name.length() == 36) {
                data.loadArea(UUID.fromString(name));
            }
        }
        data.refreshGuilds();
        data.reloadMobDrops();
        data.loadByteServer();
    }


    public static void reload() {
        reloadCraftings();
        reloadDrops();
    }
}
