package jaysonjson.papierfuchs.fuchs.io.data;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.event.FuchsSaveEvent;
import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsArea;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.FuchsMobDrop;
import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuild;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.ByteFuchsServer;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.ByteFuchsWorld;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsWorld;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.workbench.FuchsWorkbenchRecipe;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.utility.FuchsAnsi;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class FuchsData {

    public HashMap<UUID, FuchsPlayer> players = new HashMap<>();
    public HashMap<UUID, FuchsGuild> guilds = new HashMap<>();
    public HashMap<UUID, FuchsArea> areas = new HashMap<>();
    public FuchsServer server = new FuchsServer();
    public ByteFuchsServer byteServer = new ByteFuchsServer();
    public PapierFuchsData papierFuchs = new PapierFuchsData();
    public HashMap<UUID, FuchsWorld> worlds = new HashMap<>();
    public HashMap<UUID, ByteFuchsWorld> byteWorlds = new HashMap<>();
    public HashMap<String, FuchsMobDrop> mobDrops = new HashMap<>();

    public void loadServer() {
        server = DataHandler.loadServer();
    }

    public void loadPapierFuchs() {
        papierFuchs = DataHandler.loadPapierFuchs();
    }

    public void loadPlayer(UUID uuid) {
       /* if(!players.containsKey(uuid)) {
            players.put(uuid, DataHandler.loadPlayer(uuid));
            Utility.log("Spieler mit der UUID " + uuid + " geladen!");
        } else {
            Utility.log("Spieler mit der UUID " + uuid + " existiert bereits!");
        }*/
        players.put(uuid, DataHandler.loadPlayer(uuid));
    }

    public void loadPlayer(FuchsPlayer fuchsPlayer) {
       loadPlayer(fuchsPlayer.getUUID());
    }

    public void loadArea(UUID uuid) {
        FuchsLog.log(FuchsAnsi.GREEN + "FuchsArea " + FuchsAnsi.CYAN + uuid + FuchsAnsi.GREEN + " geladen!");
        areas.put(uuid, DataHandler.loadArea(uuid));
    }

    public void loadGuild(UUID uuid) {
        guilds.put(uuid, DataHandler.loadGuild(uuid));
    }

    public void loadWorld(UUID uuid) {
        FuchsLog.log(FuchsAnsi.GREEN + "FuchsWelt " + FuchsAnsi.CYAN + uuid + FuchsAnsi.GREEN + " geladen!");
        worlds.put(uuid, DataHandler.loadWorld(uuid));
    }

    public void loadByteWorld(UUID uuid) {
        FuchsLog.log(FuchsAnsi.GREEN + "ByteFuchsWelt " + FuchsAnsi.CYAN + uuid + FuchsAnsi.GREEN + " geladen!");
        byteWorlds.put(uuid, DataHandler.loadByteWorld(uuid));
    }

    public void loadByteServer() {
        FuchsLog.log(FuchsAnsi.GREEN + "ByteFuchsServer geladen!");
        byteServer = DataHandler.loadByteServer();
    }

    public void saveByteServer() {
        FuchsLog.log(FuchsAnsi.GREEN + "ByteFuchsServer gespeichert!");
        DataHandler.saveByteServer(byteServer);
    }

    public void savePlayers() {
        for (FuchsPlayer value : players.values()) {
            savePlayer(value);
        }
    }


    public void saveGuilds() {
        for (FuchsGuild value : guilds.values()) {
            saveGuild(value);
        }
    }

    public void saveWorlds() {
        for (FuchsWorld value : worlds.values()) {
            saveWorld(value);
        }
    }

    public void saveByteWorlds() {
        for (ByteFuchsWorld value : byteWorlds.values()) {
            saveByteWorld(value);
        }
    }

    public void saveAreas() {
        for (FuchsArea value : areas.values()) {
            saveArea(value);
        }
    }

    public void refreshGuilds() {
        for (FuchsGuild value : guilds.values()) {
            value.refresh();
        }
    }

    public void saveGuild(FuchsGuild guild) {
        FuchsLog.log(FuchsAnsi.GREEN + "Guilde " + FuchsAnsi.CYAN + guild.getName() + " (" + guild.getUUID() + ")" + FuchsAnsi.GREEN + " gespeichert!");
        DataHandler.saveGuild(guild);
        guilds.put(guild.getUUID(), guild);
    }

    public void savePlayer(FuchsPlayer fuchsPlayer) {
        if(fuchsPlayer.getPlayer() != null) {
            FuchsLog.log(FuchsAnsi.GREEN + "FuchsSpieler " + FuchsAnsi.CYAN + fuchsPlayer.getPlayer().getName() + FuchsAnsi.GREEN + " (" + FuchsAnsi.CYAN + fuchsPlayer.getUUID() + FuchsAnsi.GREEN + ") gespeichert!");
        } else {
            FuchsLog.log(FuchsAnsi.GREEN + "FuchsSpieler " + FuchsAnsi.CYAN + fuchsPlayer.getUUID() + " (" + fuchsPlayer.getPlayerName() + ")" + FuchsAnsi.GREEN + " gespeichert!");
        }
        //FuchsLog.log(FuchsAnsi.GREEN + "Spieler " + FuchsAnsi.CYAN + fuchsPlayer.getUUID() + FuchsAnsi.GREEN + " gespeichert!");
        DataHandler.savePlayer(fuchsPlayer);
        players.put(fuchsPlayer.getUUID(), fuchsPlayer);
    }

    public void saveArea(FuchsArea fuchsArea) {
        FuchsLog.log(FuchsAnsi.GREEN + "FuchsArea " + FuchsAnsi.CYAN + fuchsArea.getDisplayName() + " (" + fuchsArea.getUuid() + ") " + FuchsAnsi.GREEN + " gespeichert!");
        DataHandler.saveArea(fuchsArea);
        areas.put(fuchsArea.getUuid(), fuchsArea);
    }

    public void saveWorld(FuchsWorld world) {
        FuchsLog.log(FuchsAnsi.GREEN + "FuchsWelt " + FuchsAnsi.CYAN + world.getWorldUuid() + FuchsAnsi.GREEN + " gespeichert!");
        DataHandler.saveWorld(world);
        worlds.put(world.getWorldUuid(), world);
    }

    public void saveByteWorld(ByteFuchsWorld world) {
        FuchsLog.log(FuchsAnsi.GREEN + "ByteFuchsWelt " + FuchsAnsi.CYAN + world.getWorldUuid() + FuchsAnsi.GREEN + " gespeichert!");
        DataHandler.saveByteWorld(world);
        byteWorlds.put(world.getWorldUuid(), world);
    }

    public FuchsWorld getWorld(UUID uuid) {
        return worlds.get(uuid);
    }

    public FuchsWorld getWorld(World world) {
        return worlds.get(world.getUID());
    }

    public ByteFuchsWorld getByteWorld(World world) {
        return getByteWorld(world.getUID());
    }

    public ByteFuchsWorld getByteWorld(UUID uuid) {
        return byteWorlds.get(uuid);
    }

    public void savePlayer(Player player) {
        savePlayer(getPlayer(player.getUniqueId()));
    }

    public void savePlayer(UUID uuid) {
        savePlayer(getPlayer(uuid));
    }

    public FuchsPlayer getPlayer(UUID uuid) {
        return players.get(uuid);
    }

    public FuchsPlayer getPlayer(String uuid) {
        return players.get(UUID.fromString(uuid));
    }

    public FuchsPlayer getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

    public FuchsGuild getGuild(UUID uuid) {
        return guilds.get(uuid);
    }

    public FuchsArea getArea(UUID uuid) {
        return areas.get(uuid);
    }

    @Nullable
    public FuchsGuild getGuild(String name) {
        for (FuchsGuild value : getGuilds().values()) {
            if(value.getName().equalsIgnoreCase(name)) {
                return value;
            }
        }
        return null;
    }

    public void saveServer() {
        DataHandler.saveServer(server);
        FuchsLog.log(ChatColor.GREEN + "Server gespeichert!");
    }

    public void savePapierFuchs() {
        DataHandler.savePapierFuchs(papierFuchs);
        FuchsLog.log(ChatColor.GREEN + "PapierFuchs gespeichert!");
    }

    public void save() {
            saveGuilds();
            saveServer();
            savePlayers();
            saveWorlds();
            savePapierFuchs();
            saveByteWorlds();
            saveAreas();
            saveByteServer();
            PapierFuchs.INSTANCE.eventHandler.fire(new FuchsSaveEvent());
    }

    public FuchsServer getServer() {
        return server;
    }

    public ByteFuchsServer getByteServer() {
        return byteServer;
    }

    public HashMap<UUID, FuchsGuild> getGuilds() {
        return guilds;
    }

    public HashMap<UUID, FuchsPlayer> getPlayers() {
        return players;
    }

    public void reloadMobDrops() {
        for (File file : new File(FileHandler.MOBDROPS_DIR).listFiles()) {
            if(file.getName().contains(".json")) {
                registerMobDrop(DataHandler.readDataISO(file), file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("plugins")), "unknown");
            }
        }
        for (FuchsRegistry registry : FuchsRegistries.registries) {
            if(registry.fuchsPlugin.getClass().getResource("/loot_recipes/drops/mobs/") != null) {
                for (String fileNamesResource : FuchsUtility.getFileNamesResources(registry.fuchsPlugin.getClass(),"loot_recipes/drops/mobs/")) {
                    if(fileNamesResource.contains(".json")) {
                        registerMobDrop(FuchsUtility.getContentFromResource(registry.fuchsPlugin.getClass(), "/" + fileNamesResource), fileNamesResource, registry.fuchsPlugin.getRegistryID());
                    }
                }
            }
        }
    }

    public void registerMobDrop(String content, String name, String mod) {
        FuchsMobDrop fuchsMobDrop = DataHandler.gson.fromJson(content, FuchsMobDrop.class);
        fuchsMobDrop.setId(mod + ":" + fuchsMobDrop.getId());
        if(!mobDrops.containsKey(fuchsMobDrop.getId())) {
            for (zCraftingItem item : fuchsMobDrop.getItems()) {
                item.generate(name);
            }
            mobDrops.put(fuchsMobDrop.getId(), fuchsMobDrop);
            FuchsLog.log(FuchsAnsi.GREEN + "Mob-Drop " + FuchsAnsi.CYAN + fuchsMobDrop.getId() + FuchsAnsi.GREEN + " geladen!");
        } else {
            FuchsLog.warn("Doppeltes Mob-Drop mit ID " + FuchsAnsi.CYAN + fuchsMobDrop.getId());
        }
    }
}
