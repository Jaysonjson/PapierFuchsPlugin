package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.drop;

import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.FuchsMobDrop;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DropListInventory extends ListInventory {

    public DropListInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsMobDrop value : References.data.mobDrops.values()) {
            ItemStack itemStack = new ItemStack(Material.PAPER);
            ItemMeta itemMeta = itemStack.getItemMeta();
            ArrayList<Component> lore = new ArrayList<>();
            lore.add(Component.text(ChatColor.GRAY + "Entity: " + ChatColor.AQUA + value.getEntity()));
            lore.add(Component.text(ChatColor.GRAY + "Items: " + ChatColor.AQUA + value.getItems().size()));
            lore.add(Component.text(ChatColor.GRAY + "Id: " + ChatColor.AQUA + value.getId()));
            itemMeta.lore(lore);
            itemMeta.setDisplayName(value.getDisplayName().get(getFuchsPlayer()));
            itemStack.setItemMeta(itemMeta);
            NBTTagCompound tag = FuchsUtility.getItemTag(itemStack);
            tag.setString("drop", value.getId());
            net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);
            nms.setTag(tag);
            itemStacks.add(CraftItemStack.asBukkitCopy(nms));
        }
        return itemStacks;
    }

    @Override
    public void onItemClick(InventoryClickEvent event, ItemStack clickedItem) {
        if (FuchsUtility.isTopInventory(event) && event.getInventory().equals(getInventory())) {
            event.setCancelled(true);
            if (clickedItem.hasItemMeta()) {
                NBTTagCompound tag = FuchsUtility.getItemTag(event.getCurrentItem());
                if (tag.hasKey("drop")) {
                    DropContentInventory dropContentInventory = InventoryList.dropContent.copy();
                    dropContentInventory.setDropListInventory(this);
                    dropContentInventory.setDrop(References.data.mobDrops.get(tag.getString("drop")));
                    dropContentInventory.createAndOpen(getPlayer());
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Drops");
    }
}
