package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsArea;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.AreaInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class AreaListInventory extends ListInventory {


    public AreaListInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsArea area : References.data.areas.values()) {
            ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "»" + area.getUuid() + "«");
            itemMeta.setLore(lore);
            itemMeta.setDisplayName(area.getDisplayName());
            itemStack.setItemMeta(itemMeta);
            net.minecraft.world.item.ItemStack nmsCopy = FuchsUtility.createNMSCopy(itemStack);
            NBTTagCompound tag = FuchsUtility.getItemTag(nmsCopy);
            tag.setString("area", area.getUuid().toString());
            itemStacks.add(CraftItemStack.asBukkitCopy(nmsCopy));
        }
        return itemStacks;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            if (clickedItem.hasItemMeta()) {
                net.minecraft.world.item.ItemStack nmsCopy = FuchsUtility.createNMSCopy(clickedItem);
                NBTTagCompound tag = FuchsUtility.getItemTag(nmsCopy);
                if (tag.hasKey("area")) {
                    AreaInventory areaInventory = InventoryList.area.copy();
                    areaInventory.setArea(References.data.getArea((UUID.fromString(tag.getString("area")))));
                    areaInventory.setAreaListInventory(this);
                    areaInventory.create(getPlayer());
                    areaInventory.openInventory();
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }
}
