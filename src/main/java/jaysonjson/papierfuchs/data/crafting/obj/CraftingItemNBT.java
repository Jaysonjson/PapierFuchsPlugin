package jaysonjson.papierfuchs.data.crafting.obj;

import jaysonjson.papierfuchs.object.item.interfaces.nbt.NBTType;

public class CraftingItemNBT {

	public String key = "";
	
	//Only 1 Value has to be entered
	public Integer int_value = 0;
	public Boolean bool_value = false;
	public Float float_value = 0f;
	public Double double_value = 0d;
	public String string_value = "";

	public NBTType type = NBTType.STRING;
}
