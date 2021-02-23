package jaysonjson.papierfuchs.object.entity;

import net.minecraft.server.v1_16_R3.Entity;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.entity.Player;


public interface IFuchsEntity {

	Entity createEntity(Player player);
	EntityTypes<?> getEntityType();

	default Entity createEntity() {
		return createEntity(null);
	}

	int getMaxHealth();
}
