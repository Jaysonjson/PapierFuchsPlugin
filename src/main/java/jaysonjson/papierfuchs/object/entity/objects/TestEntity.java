package jaysonjson.papierfuchs.object.entity.objects;

import jaysonjson.papierfuchs.object.entity.FuchsEntity;
import jaysonjson.papierfuchs.object.entity.FuchsEntityData;
import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.entity.Player;

public class TestEntity extends FuchsEntity {

    public TestEntity(String id, EntityTypes<?> type) {
        super(id, type);
    }

    @Override
    public Entity createEntity(Player player) {
        FuchsEntityData fuchsEntityData = new FuchsEntityData(this, player);
        return fuchsEntityData.getEntity();
    }
}
