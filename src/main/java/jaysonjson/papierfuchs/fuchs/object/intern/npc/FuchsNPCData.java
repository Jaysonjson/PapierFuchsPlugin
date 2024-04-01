package jaysonjson.papierfuchs.fuchs.object.intern.npc;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import net.minecraft.server.level.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.UUID;

public class FuchsNPCData implements Serializable {
    private String name = "";
    private transient String skinName = "";
    private String texture = "";
    private String signature = "";
    private UUID uuid = UUID.randomUUID();
    private FuchsLocation location = new FuchsLocation();
    private transient EntityPlayer entity;
    private boolean corpse = false;
    private UUID corpse_owner;
    private String corpse_inventory;

    public String getName() {
        return name;
    }

    public String getSkinName() {
        return skinName;
    }

    public String getSignature() {
        return signature;
    }

    public UUID getUuid() {
        return uuid;
    }

    public FuchsLocation getLocation() {
        return location;
    }

    public String getTexture() {
        return texture;
    }

    @Nullable
    public EntityPlayer getEntity() {
        return entity;
    }

    public void setEntity(EntityPlayer entity) {
        this.entity = entity;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setLocation(FuchsLocation location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setSkinName(String skinName) {
        this.skinName = skinName;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public boolean isCorpse() {
        return corpse;
    }

    public void setCorpse(boolean corpse) {
        this.corpse = corpse;
    }

    @Nullable
    public UUID getCorpseOwner() {
        return corpse_owner;
    }

    @Nullable
    public Player getCorpseOwnerAsPlayer() {
        return Bukkit.getOfflinePlayer(getCorpseOwner()).getPlayer();
    }

    public void setCorpseOwner(UUID corpse_owner) {
        this.corpse_owner = corpse_owner;
    }

    public String getCorpseInventory() {
        return corpse_inventory;
    }

    public void setCorpseInventory(String corpse_inventory) {
        this.corpse_inventory = corpse_inventory;
    }
}
