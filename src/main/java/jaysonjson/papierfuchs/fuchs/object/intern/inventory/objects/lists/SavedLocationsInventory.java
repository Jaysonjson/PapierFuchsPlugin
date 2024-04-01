package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.kyori.adventure.text.Component;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SavedLocationsInventory extends ListInventory {

    public SavedLocationsInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (String fuchsLocation : getFuchsPlayer().getSavedLocations().keySet()) {
            FuchsLocation fuchsLocation1 = getFuchsPlayer().getSavedLocations().get(fuchsLocation);
            ItemStack itemStack = FuchsUtility.createInventoryStack(Material.PAPER, 1, fuchsLocation);
            net.minecraft.world.item.ItemStack nms = FuchsUtility.createNMSCopy(itemStack);
            NBTTagCompound tag = FuchsUtility.getItemTag(nms);
            tag.setString("location", fuchsLocation);
            nms.setTag(tag);
            itemStack = CraftItemStack.asBukkitCopy(nms);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(Component.text("Welt: " + fuchsLocation1.getWorld().getName()));
            lore.add(Component.text("X: " + fuchsLocation1.getX()));
            lore.add(Component.text("Y: " + fuchsLocation1.getY()));
            lore.add(Component.text("Z: " + fuchsLocation1.getZ()));
            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);
            itemStacks.add(itemStack);
        }
        return itemStacks;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if(FuchsUtility.isTopInventory(event)) {
            event.setCancelled(true);
            if (clickedItem.hasItemMeta()) {
                Player player = (Player) event.getWhoClicked();
                net.minecraft.world.item.ItemStack nmsCopy = FuchsUtility.createNMSCopy(clickedItem);
                NBTTagCompound tag = FuchsUtility.getItemTag(nmsCopy);
                if (tag.hasKey("location")) {
                    player.teleport(getFuchsPlayer().getSavedLocations().get(tag.getString("location")).asBukkit());
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

}
