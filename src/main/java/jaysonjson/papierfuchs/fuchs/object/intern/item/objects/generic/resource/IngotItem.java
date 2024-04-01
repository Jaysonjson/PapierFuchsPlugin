package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.resource;

import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import org.bukkit.Material;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
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

	@Nullable
	public OreItem getOre() {
		try {
			return (OreItem) ore.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean hasOre() {
		return getOre() != null;
	}

	@Override
	public boolean isIngot() {
		return true;
	}
}
