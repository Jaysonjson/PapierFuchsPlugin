package jaysonjson.papierfuchs.object.entity;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

public class FuchsEntityData {

    FuchsEntity fuchsEntity;
    Player player;
    CraftWorld world;
    Entity entity;
    FuchsEntityCreature fuchsEntityCreature;
    public FuchsEntityData(FuchsEntity fuchsEntity, Player player) {
        this.fuchsEntity = fuchsEntity;
        this.player = player;
        if(player != null) {
            world = ((CraftWorld) player.getWorld());
        }
    }

    public FuchsEntityData(FuchsEntity fuchsEntity, Player player, CraftWorld world) {
        this.fuchsEntity = fuchsEntity;
        this.player = player;
        this.world = world;
    }

    public Entity getEntity() {
        entity = getCreature();
        return entity;
    }

    public void create() {
        FuchsEntityCreature fuchsEntityCreature = new FuchsEntityCreature(fuchsEntity.getEntityType(), world.getHandle());
    }

    public FuchsEntityCreature getCreature() {
        if(fuchsEntityCreature == null) {
            create();
        }
        return fuchsEntityCreature;
    }
}
