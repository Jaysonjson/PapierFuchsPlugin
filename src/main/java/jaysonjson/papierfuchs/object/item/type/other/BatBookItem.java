package jaysonjson.papierfuchs.object.item.type.other;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BatBookItem extends FuchsItem {

    public BatBookItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player);
        oItem.addToLore("Killt Fledermäuse in einen bestimmten Radius");
        oItem.setItem(ChatColor.GRAY + "Fledermaus Buch");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public void onItemUse(PlayerInteractEvent event) {
        int bats_killed = 0;
        for (Entity nearbyEntity : event.getPlayer().getNearbyEntities(15, 15, 15)) {
            if(nearbyEntity.getType() == EntityType.BAT) {
                nearbyEntity.remove();
                bats_killed++;
            }
        }
        event.getPlayer().sendMessage("Es wurden " + bats_killed + " Fledermäuse gekillt!");
    }
}
