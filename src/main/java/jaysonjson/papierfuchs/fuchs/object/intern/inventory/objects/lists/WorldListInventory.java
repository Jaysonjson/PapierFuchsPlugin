package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class WorldListInventory extends ListInventory {

    public WorldListInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (World world : PapierFuchs.INSTANCE.getServer().getWorlds()) {
            Material material = Material.GRASS_BLOCK;
            if(world.getEnvironment().equals(World.Environment.NETHER)) {
                material = Material.NETHERRACK;
            } else if(world.getEnvironment().equals(World.Environment.THE_END)) {
                material = Material.END_STONE;
            }
            ItemStack itemStack = FuchsUtility.createInventoryStack(material, 1, world.getName());
            net.minecraft.world.item.ItemStack nms = FuchsUtility.createNMSCopy(itemStack);
            NBTTagCompound tag = FuchsUtility.getItemTag(nms);
            tag.setString("world", world.getName());
            nms.setTag(tag);
            itemStack = CraftItemStack.asBukkitCopy(nms);
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
                if (tag.hasKey("world")) {
                    World world = PapierFuchs.INSTANCE.getServer().getWorld(tag.getString("world"));
                    player.teleport(world.getSpawnLocation());
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Worlds");
    }
}
