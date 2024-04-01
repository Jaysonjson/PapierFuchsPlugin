package jaysonjson.papierfuchs.fuchs.io.data.crafting.obj;

import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class zCraftingItem {

	public String fuchsItem = "";
	public String itemData = "";
	public Material material = Material.AIR;
	public Integer amount = 1;
	public ArrayList<CraftingItemNBT> nbt = new ArrayList<>();
	public int slot = 0;
	public double chance = 0.0;
	
	public transient ItemStack itemStack;
	
	public ItemStack getItem(String recipe) {
		if(itemStack == null) {
			generate(recipe);
		}
		return itemStack;
	}

	public ItemStack getItem() {
		return getItem("UNKNOWN");
	}
	
	public String getItemID() {
		return fuchsItem;
	}

	@Override
	public String toString() {
		return "zCraftingItem{" +
				"fuchsItem='" + fuchsItem + '\'' +
				", itemData='" + itemData + '\'' +
				", material=" + material +
				", amount=" + amount +
				", nbt=" + nbt +
				", itemStack=" + itemStack +
				'}';
	}

	public void generate(String recipe) {
		ItemStack newStack = null;
		if(material != Material.AIR) {
			newStack = new ItemStack(material);
		} else if(!fuchsItem.equals("")) {
			if(FuchsUtility.itemIDExists(getItemID())) {
				newStack = FuchsUtility.getFuchsItemByID(getItemID()).createItem();
			} else {
				FuchsLog.error(ChatColor.AQUA + recipe + ChatColor.RED + " Item mit der ID " + ChatColor.AQUA + getItemID() + ChatColor.RED + " existiert nicht, überspringen...");
			}
		} else if(!itemData.equals("")) {
			newStack = FuchsUtility.generateItemStack(itemData);
		}
		if(newStack != null) {
			net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(newStack);
			NBTTagCompound tag = FuchsUtility.getItemTag(nmsStack);
			if (!itemData.equals("")) {
				for (CraftingItemNBT craftingItemNBT : nbt) {
					switch (craftingItemNBT.type) {
						case STRING:
							tag.setString(craftingItemNBT.key, craftingItemNBT.string_value);
						case FLOAT:
							tag.setFloat(craftingItemNBT.key, craftingItemNBT.float_value);
						case BOOLEAN:
							tag.setBoolean(craftingItemNBT.key, craftingItemNBT.bool_value);
						case INTEGER:
							tag.setInt(craftingItemNBT.key, craftingItemNBT.int_value);
						case DOUBLE:
							tag.setDouble(craftingItemNBT.key, craftingItemNBT.double_value);
					}
				}
			}
			nmsStack.setTag(tag);
			newStack = CraftItemStack.asBukkitCopy(nmsStack);
			newStack.setAmount(amount);
			this.itemStack = newStack;
		}
	}

	public double getChance() {
		return chance;
	}
}
