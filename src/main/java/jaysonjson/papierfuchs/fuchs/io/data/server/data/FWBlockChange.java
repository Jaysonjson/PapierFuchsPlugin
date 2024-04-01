package jaysonjson.papierfuchs.fuchs.io.data.server.data;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class FWBlockChange implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private FuchsLocation location;
    private Material material;
    @Nullable
    private UUID player;
    private LocalDateTime time;
    private ChangeType type;
    private ChangeCause cause;

    public FWBlockChange() {

    }

    public FWBlockChange(Player player, Block block, ChangeType type, ChangeCause cause) {
        this.player = player.getUniqueId();
        this.location = new FuchsLocation(block.getLocation());
        this.material = block.getType();
        this.time = LocalDateTime.now();
        this.type = type;
        this.cause = cause;
    }

    @Nullable
    public UUID getPlayer() {
        return player;
    }

    public void setPlayer(@Nullable UUID player) {
        this.player = player;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public FuchsLocation getLocation() {
        return location;
    }

    public void setLocation(FuchsLocation location) {
        this.location = location;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ChangeType getType() {
        return type;
    }

    public void setType(ChangeType type) {
        this.type = type;
    }

    public ChangeCause getCause() {
        return cause;
    }

    public void setCause(ChangeCause cause) {
        this.cause = cause;
    }

    public enum ChangeType {
        ADD, REMOVE
    }

    public enum ChangeCause {
        PLACE, BREAK, EXPLOSION
    }
}
