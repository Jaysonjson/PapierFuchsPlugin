package jaysonjson.papierfuchs.object.entity;

import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;
import net.minecraft.server.v1_16_R3.EntityTypes;

public abstract class FuchsEntity extends FuchsObject implements IFuchsEntity {

	EntityTypes<?> type;
	public FuchsEntity(String id, EntityTypes<?> type) {
		super(id, RegistryType.ENTITY);
		this.type = type;
	}

	@Override
	public EntityTypes getEntityType() {
		return type;
	}

	@Override
	public int getMaxHealth() {
		return 10;
	}

}
