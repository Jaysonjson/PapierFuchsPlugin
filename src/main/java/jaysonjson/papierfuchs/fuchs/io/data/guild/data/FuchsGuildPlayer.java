package jaysonjson.papierfuchs.fuchs.io.data.guild.data;

import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.FuchsGuildRank;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FuchsGuildPlayer {

    private FuchsGuildRank rank = FuchsGuildRank.MEMBER;
    private UUID uuid;
    private transient FuchsGuild guild;

    public FuchsGuildPlayer(UUID uuid, FuchsGuildRank rank) {
        this.uuid = uuid;
        this.rank = rank;
    }

    public FuchsGuildPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public FuchsPlayer getFuchsPlayer() {
        return References.data.getPlayer(uuid);
    }

    public Player getPlayer() {
        return Bukkit.getOfflinePlayer(uuid).getPlayer();
    }

    public FuchsGuildRank getRank() {
        return rank;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setRank(FuchsGuildRank rank) {
        this.rank = rank;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public FuchsGuild getGuild() {
        return guild;
    }

    public void setGuild(FuchsGuild guild) {
        this.guild = guild;
    }

    public boolean isOwner() {
        return getRank().equals(FuchsGuildRank.OWNER);
    }
}
