package jaysonjson.papierfuchs.object.effect;

import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;

public abstract class FuchsEffect extends FuchsObject implements IFuchsEffect {


	public FuchsEffect(String id) {
		super(id, RegistryType.EFFECT);
	}

	@Override
	public void onEnemyHit() {

	}
}
