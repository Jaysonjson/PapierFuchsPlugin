package jaysonjson.papierfuchs.fuchs.object.intern.group;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class FuchsGroup {

    public final int MAX = 5;

    private final ArrayList<FuchsGroupPlayer> players = new ArrayList<>();
    private final ArrayList<FuchsGroupInvite> pendingInvites = new ArrayList<>();

    public ArrayList<FuchsGroupPlayer> getPlayers() {
        return players;
    }

    public ArrayList<FuchsGroupInvite> getPendingInvites() {
        return pendingInvites;
    }

    public void addPlayer(Player player) {
        getPlayers().add(new FuchsGroupPlayer(player, false));
    }

    public boolean hasPlayer(Player player) {
        for (FuchsGroupPlayer fuchsGroupPlayer : getPlayers()) {
            if(fuchsGroupPlayer.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(MAX, players, pendingInvites);
    }
}
