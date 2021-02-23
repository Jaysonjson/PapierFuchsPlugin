package jaysonjson.papierfuchs.object.item.objects.other;

import jaysonjson.papierfuchs.inventories.backpack.BackPackNBTInventory;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class BackPackItemNBT extends FuchsItem {

    int inventorySize;
    FuchsItemData fuchsItemData;
    public BackPackItemNBT(String id, Material material, IItemUseType itemUseType, int inventorySize) {
        super(id, material, itemUseType);
        this.inventorySize = inventorySize;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.lore.add(inventorySize + " Slots");
        fuchsItemData.setItem(ChatColor.RESET + "NBT Rucksack");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.IS_BACKPACK, true);
        tag.setString("fakeUUID", UUID.randomUUID().toString());
        return tag;
    }


    @Override
    public void onItemUse(PlayerInteractEvent event) {
        if(event.getHand().equals(EquipmentSlot.HAND)) {
            BackPackNBTInventory inventory = new BackPackNBTInventory(event.getItem(), inventorySize);
            inventory.openInventory(event.getPlayer());
        }
    }
}
