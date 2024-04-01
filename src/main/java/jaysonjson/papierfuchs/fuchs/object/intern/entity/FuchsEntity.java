package jaysonjson.papierfuchs.fuchs.object.intern.entity;

import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.object.IModelData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

public abstract class FuchsEntity<T extends Entity> extends FuchsObject implements IFuchsEntity<T>, Cloneable, IModelData, IFuchsDisplayName {

	private FuchsRegistryObject<? extends FuchsItem> spawnEgg = null;
	public FuchsEntity(String id) {
		super(id, RegistryType.ENTITY);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public boolean delayed() {
		return true;
	}

	@Override
	@Nullable
	public FuchsRegistryObject<? extends FuchsItem> getSpawnEgg() {
		return spawnEgg;
	}

	public void setSpawnEgg(FuchsRegistryObject<? extends FuchsItem> spawnEgg) {
		this.spawnEgg = spawnEgg;
	}

	@Override
	public boolean isDisplayNameChangeable() {
		return false;
	}
}
