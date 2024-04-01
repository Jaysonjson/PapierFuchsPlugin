package jaysonjson.papierfuchs.fuchs.utility;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerUtil {

    public static boolean isCreative(Player player) {
        return player.getGameMode().equals(GameMode.CREATIVE);
    }

    public static boolean isSurvival(Player player) {
        return player.getGameMode().equals(GameMode.SURVIVAL);
    }

    public static boolean isAdventure(Player player) {
        return player.getGameMode().equals(GameMode.ADVENTURE);
    }

    public static boolean isSpectator(Player player) {
        return player.getGameMode().equals(GameMode.SPECTATOR);
    }

    public static FuchsPlayer asFuchs(Player player) {
        return References.data.getPlayer(player);
    }

}
