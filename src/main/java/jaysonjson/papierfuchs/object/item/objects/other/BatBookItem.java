package jaysonjson.papierfuchs.object.item.objects.other;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
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
        FuchsItemData fuchsItemData = new FuchsItemData(this, player);
        fuchsItemData.addToLore("Killt Fledermäuse in einen bestimmten Radius");
        fuchsItemData.setItem(ChatColor.GRAY + "Fledermaus Buch");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        tag.setBoolean(ItemNBT.CAN_ENCHANT_MINECRAFT, false);
        return tag;
    }

    @Override
    public void onItemUse(PlayerInteractEvent event) {
        int bats_killed = 0;
        Player player = event.getPlayer();
        for (Entity nearbyEntity : player.getNearbyEntities(25, 15, 25)) {
            if(nearbyEntity.getType() == EntityType.BAT) {
                nearbyEntity.remove();
                bats_killed++;
            }
        }
        player.getWorld().playEffect(player.getLocation(), Effect.ENDER_SIGNAL, 3, 1);
        if(bats_killed == 0) {
            player.sendMessage("Es wurden keine Fledermäuse gekillt!");
        } else if(bats_killed == 1) {
            player.sendMessage("Es wurde eine Fledermaus gekillt!");
            player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1f, 1f);
        } else if(bats_killed > 1) {
            player.sendMessage("Es wurden " + bats_killed + " Fledermäuse gekillt!");
            player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1f, 1f);
        }
    }
}
