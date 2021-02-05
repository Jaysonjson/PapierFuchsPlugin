package jaysonjson.papierfuchs.events.entity;

import jaysonjson.papierfuchs.Constant;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDamage implements Listener {
    @EventHandler
    public void entityDamageEvent(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
            event.setDamage(event.getDamage() + (fuchsPlayer.getStats().getStrength() * Constant.DAMAGE_MODIFIER));
            if(event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                ItemStack itemStack = ((Player) event.getDamager()).getItemInHand();
                net.minecraft.server.v1_16_R3.ItemStack nmsCopy = Utility.createNMSCopy(itemStack);
                NBTTagCompound tag = Utility.getItemTag(nmsCopy);
                if(tag.hasKey(ItemNBT.ITEM_DURABILITY)) {
                    tag.setInt(ItemNBT.ITEM_DURABILITY, tag.getInt(ItemNBT.ITEM_DURABILITY) - 1);
                    nmsCopy.setTag(tag);
                    ((Player) event.getDamager()).setItemInHand(CraftItemStack.asBukkitCopy(nmsCopy));
                }
            }
        }
    }
}

