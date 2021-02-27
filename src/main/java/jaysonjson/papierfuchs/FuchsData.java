package jaysonjson.papierfuchs;

import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class FuchsData {

    public HashMap<UUID, FuchsPlayer> players = new HashMap<>();
    public FuchsServer server = new FuchsServer();


    public void loadServer() {
        server = DataHandler.loadServer();
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

    public void savePlayers() {
        for (FuchsPlayer value : players.values()) {
            DataHandler.savePlayer(value);
            players.put(value.getUUID(), value);
        }
    }

    public void savePlayer(FuchsPlayer fuchsPlayer) {
        DataHandler.savePlayer(fuchsPlayer);
        players.put(fuchsPlayer.getUUID(), fuchsPlayer);
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

    public void saveServer() {
        DataHandler.saveServer(server);
    }
}
