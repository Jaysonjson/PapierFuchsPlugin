package jaysonjson.papierfuchs.object.entity;

import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;
import net.minecraft.server.v1_16_R3.EntityTypes;

public abstract class FuchsEntity implements IFuchsRegistryObject, IFuchsEntity {

	private String id;
	EntityTypes<?> type;
	public FuchsEntity(String id, EntityTypes<?> type) {
		this.id = id;
		this.type = type;
	}
	
	@Override
	public String getID() {
		return id;
	}

	@Override
	public EntityTypes getEntityType() {
		return type;
	}

	@Override
	public RegistryType getType() {
		return RegistryType.ENTITY;
	}

	@Override
	public int getMaxHealth() {
		return 10;
	}

	@Override
	public void updateID(IFuchsPlugin fuchsPlugin) {
		this.id = fuchsPlugin.getPluginID() + ":" + getID();
	}
}
