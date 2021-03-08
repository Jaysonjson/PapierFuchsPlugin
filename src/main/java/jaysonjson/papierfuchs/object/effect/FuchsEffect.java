package jaysonjson.papierfuchs.object.effect;

import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsEffect implements IFuchsRegistryObject, IFuchsEffect {

	private String id;
	public FuchsEffect(String id) {
		this.id = id;
	}
	
	@Override
	public RegistryType getType() {
		return RegistryType.EFFECT;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public void onEnemyHit() {

	}

	@Override
	public void updateID(IFuchsPlugin fuchsPlugin) {
		this.id = fuchsPlugin.getPluginID() + ":" + getID();
	}
}
