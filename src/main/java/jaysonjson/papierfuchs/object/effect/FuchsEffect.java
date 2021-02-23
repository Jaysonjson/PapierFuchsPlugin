package jaysonjson.papierfuchs.object.effect;

import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsEffect implements IFuchsRegistryObject, IFuchsEffect {

	private final String id;
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
}
