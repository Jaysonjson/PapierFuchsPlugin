package jaysonjson.papierfuchs.fuchs.object.intern.entity;

import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;

public class FuchsEntityData {

    FuchsEntity<?> fuchsEntity;
    Player player;
    CraftWorld world;
    Entity entity;
    FuchsEntityCreature fuchsEntityCreature;
    public FuchsEntityData(FuchsEntity<?> fuchsEntity, Player player) {
        this.fuchsEntity = fuchsEntity;
        this.player = player;
        if(player != null) {
            world = ((CraftWorld) player.getWorld());
        }
    }

    public FuchsEntityData(FuchsEntity<?> fuchsEntity, Player player, CraftWorld world) {
        this.fuchsEntity = fuchsEntity;
        this.player = player;
        this.world = world;
    }

    public Entity getEntity() {
        entity = getCreature();
        return entity;
    }

    public void create() {
       // fuchsEntityCreature = new FuchsEntityCreature(fuchsEntity.getEntityType(), world.getHandle());
    }

    public FuchsEntityCreature getCreature() {
        if(fuchsEntityCreature == null) {
            create();
        }
        return fuchsEntityCreature;
    }
}
