package jaysonjson.papierfuchs.data.crafting.obj;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class zCraftingItem {

	public String fuchsItem = "";
	public String itemData = "";
	public Material material = Material.AIR;
	public Integer amount = 0;
	public ArrayList<CraftingItemNBT> nbt = new ArrayList<>();
	
	public transient ItemStack itemStack;
	
	public ItemStack getItem() {
		return itemStack;
	}
	
	public String getItemID() {
		return fuchsItem;
	}
}
