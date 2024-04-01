package jaysonjson.papierfuchs.fuchs.io.data.guild.data;

import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.io.data.IHasUUID;
import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.FuchsInvite;
import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.zGuildBanner;
import jaysonjson.papierfuchs.fuchs.io.data.guild.obj.FuchsGuildRank;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.io.handler.UUIDHandler;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.CurrencyList;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class FuchsGuild implements IHasUUID {

    public transient static final double GUILD_COST = 50000;
    public transient static final FuchsCurrency CURRENCY = CurrencyList.FUCHSO.copy();
    public transient static final int CHAR_LIMIT = 17;

    private UUID owner;
    private String name = "";
    private ArrayList<FuchsGuildPlayer> members = new ArrayList<>();
    private UUID uuid;
    private zGuildBanner banner = new zGuildBanner();
    private final ArrayList<FuchsInvite> pendingInvites = new ArrayList<>();

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID randomUUID() {
        return UUIDHandler.AREA_UUID.generateUUID();
    }
    
    public zGuildBanner getBanner() {
		return banner;
	}
    
    public ArrayList<FuchsGuildPlayer> getMembers() {
		return members;
	}
    
    public String getName() {
		return name;
	}
    
    public UUID getOwner() {
		return owner;
	}
    
    
    public void setBanner(zGuildBanner banner) {
		this.banner = banner;
	}
    
    public void setMembers(ArrayList<FuchsGuildPlayer> members) {
		this.members = members;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setOwner(UUID owner) {
		this.owner = owner;
	}

	@Nullable
	public static FuchsGuild create(String name, UUID owner) {
        if(name.length() < CHAR_LIMIT) {
            FuchsGuild guild = new FuchsGuild();
            guild.setName(name);
            guild.setOwner(owner);
            guild.setUUID(guild.randomUUID());
            guild.getMembers().add(new FuchsGuildPlayer(owner, FuchsGuildRank.OWNER));
            DataHandler.saveGuild(guild);
            References.data.loadGuild(guild.getUUID());
            return guild;
        }
        return null;
    }

    public void delete() {
        new File(FileHandler.GUILD_DIR + uuid.toString() + ".json").delete();
        for (FuchsGuildPlayer member : getMembers()) {
            FuchsPlayer fuchsPlayer = member.getFuchsPlayer();
            fuchsPlayer.setGuildUUID(null);
            FuchsLanguageString text = FuchsLanguageString.c("Die Guilde " + getName() + " wurde aufgelöst!");
            if(fuchsPlayer.getPlayer() != null) {
                fuchsPlayer.getPlayer().sendMessage(Component.text(text.get(fuchsPlayer)));
            } else {
                fuchsPlayer.getPendingMessages().add(text);
            }
        }
        References.data.guilds.remove(uuid);
    }

    @Nullable
    public FuchsGuildPlayer getMember(UUID uuid) {
        for (FuchsGuildPlayer member : getMembers()) {
            if(member.getUuid().equals(uuid)) {
                return member;
            }
        }
        return null;
    }

    @Nullable
    public FuchsGuildPlayer getMember(Player player) {
        return getMember(player.getUniqueId());
    }

    public void removeMember(Player player) {
        if(getMember(player) != null) {
            getMembers().remove(getMember(player));
        }
        refresh();
    }

    public void removeMember(FuchsGuildPlayer player) {
        if(getMember(player.getPlayer()) != null) {
            getMembers().remove(getMember(player.getPlayer()));
        }
        refresh();
    }

    public void refresh() {
        for (FuchsGuildPlayer member : getMembers()) {
            FuchsPlayer fuchsPlayer = member.getFuchsPlayer();
            if(fuchsPlayer.getGuildUUID() != null) {
                if (!fuchsPlayer.getGuildUUID().equals(getUUID())) {
                    FuchsLog.log(ChatColor.YELLOW + "Spieler " + fuchsPlayer.getUUID() + " hatte falsche Guilden-Uuid, aktualisiert!");
                    fuchsPlayer.setGuildUUID(getUUID());
                }
            } else {
                fuchsPlayer.setGuildUUID(getUUID());
            }
            member.setGuild(this);
        }
        if(getMembers().size() < 1) {
            delete();
        }
    }

    public void addMember(Player player, FuchsGuildRank rank) {
        if(getMember(player) == null) {
            getMembers().add(new FuchsGuildPlayer(player.getUniqueId(), rank));
            refresh();
        }
    }

    public void addMember(Player player) {
        addMember(player, FuchsGuildRank.MEMBER);
    }

    public ArrayList<FuchsInvite> getPendingInvites() {
        return pendingInvites;
    }

    @Nullable
    public FuchsInvite getInviteFromInvitee(UUID invitee) {
        for (FuchsInvite pendingInvite : getPendingInvites()) {
            if(pendingInvite.getInvitee().equals(invitee)) {
                return pendingInvite;
            }
        }
        return null;
    }

    public boolean isMember(UUID uuid) {
        return getMember(uuid) != null;
    }

    public boolean isMember(Player player) {
        return isMember(player.getUniqueId());
    }
}
