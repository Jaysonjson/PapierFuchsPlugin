package jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.lists;

import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.generic.ListInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.skillclass.FuchsSkillclass;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SkillclassInventory extends ListInventory {

    public SkillclassInventory(String id) {
        super(id);
    }

    @Override
    public ArrayList<ItemStack> getStacks() {
        ArrayList<ItemStack> itemStacks = new ArrayList<>();
        for (FuchsRegistryObject<FuchsSkillclass<?>> value : FuchsRegistries.skill_classes.values()) {
            net.minecraft.world.item.ItemStack nms = FuchsUtility.createNMSCopy(value.copy().createClassStack(getFuchsPlayer()));
            NBTTagCompound tag = FuchsUtility.getItemTag(nms);
            tag.setString("skillclass", value.getID());
            nms.setTag(tag);
            itemStacks.add(CraftItemStack.asBukkitCopy(nms));
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
                if (tag.hasKey("skillclass")) {
                    getFuchsPlayer().getKeys().setSkillClass(tag.getString("skillclass"));
                    References.data.savePlayer(getFuchsPlayer());
                    References.data.loadPlayer(getFuchsPlayer());
                    FuchsUtility.updateInventory((Player) event.getWhoClicked());
                }
            }
        }
        super.onItemClick(event, clickedItem);
    }
}
