package jaysonjson.papierfuchs.fuchs.object.intern.group;

import org.bukkit.entity.Player;

public class FuchsGroupPlayer {

    private Player player;
    private boolean leader;

    public FuchsGroupPlayer(Player player, boolean leader) {
        this.player = player;
        this.leader = leader;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isLeader() {
        return leader;
    }

    public void setLeader(boolean leader) {
        this.leader = leader;
    }
}
