package jaysonjson.papierfuchs.data.guild.data;

import jaysonjson.papierfuchs.data.IHasUUID;
import jaysonjson.papierfuchs.data.guild.obj.zGuildBanner;
import jaysonjson.papierfuchs.data.guild.obj.zGuildRank;

import java.util.HashMap;
import java.util.UUID;

public class zGuild implements IHasUUID {

    private UUID owner;
    private String name = "";
    private HashMap<UUID, zGuildRank> members = new HashMap<>();
    private UUID uuid;
    private zGuildBanner banner = new zGuildBanner();

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
        return UUID.randomUUID();
    }
    
    public zGuildBanner getBanner() {
		return banner;
	}
    
    public HashMap<UUID, zGuildRank> getMembers() {
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
    
    public void setMembers(HashMap<UUID, zGuildRank> members) {
		this.members = members;
	}
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setOwner(UUID owner) {
		this.owner = owner;
	}
    
}
