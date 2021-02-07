package jaysonjson.papierfuchs.object.item.interfaces.nbt.objects;

import jaysonjson.papierfuchs.object.item.interfaces.nbt.INBTTag;
import jaysonjson.papierfuchs.object.item.interfaces.nbt.NBTType;

public class FuchsNBT implements INBTTag {

	public String key;
	public NBTType type;
	public boolean update;
	public Object defaultValue;
	
	public FuchsNBT(String key, Object defaultValue, boolean update, NBTType type) {
		this.key = key;
		this.defaultValue = defaultValue;
		this.update = update;
		this.type = type;
	}
	
	@Override
	public NBTType getType() {
		return type;
	}

	@Override
	public boolean canUpdate() {
		return update;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Object getDefaultValue() {
		return defaultValue;
	}

}
