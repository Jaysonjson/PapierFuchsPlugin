package jaysonjson.papierfuchs.object.item.objects.generic.resource;

import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class IngotItem extends FuchsItem {

	String displayName;
	OreItem ore;
	public IngotItem(String id, Material material, IItemUseType itemUseType, String displayName, OreItem ore) {
		super(id, material, itemUseType);
		this.displayName = displayName;
		this.ore = ore;
	}

	public IngotItem(String id, Material material, IItemUseType itemUseType) {
		super(id, material, itemUseType);
	}

	@Override
	public ItemStack createItem(Player player, ItemStack stack) {
		FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
		fuchsItemData.setItem(displayName);
		return fuchsItemData.item;
	}

	@Override
	public NBTTagCompound getTag(NBTTagCompound tag) {
		tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
		return tag;
	}

	@Nullable
	public OreItem getOre() {
		return ore;
	}

	public boolean hasOre() {
		return getOre() != null;
	}

	@Override
	public boolean isIngot() {
		return true;
	}
}
