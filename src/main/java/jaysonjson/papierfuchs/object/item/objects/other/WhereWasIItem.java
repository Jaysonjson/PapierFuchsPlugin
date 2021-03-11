package jaysonjson.papierfuchs.object.item.objects.other;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class WhereWasIItem extends FuchsItem {
    public WhereWasIItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.setItem("Wo war ich?");
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public void onItemUse(PlayerInteractEvent event) {
        Location location = event.getPlayer().getLocation();
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getPlayer().teleport(location);
                cancel();
            }
        }.runTaskTimer(PapierFuchs.INSTANCE, 300L, 0L);

        event.getPlayer().sendMessage(getDisplayName() + ": 15s");
        World world = event.getPlayer().getWorld();
        long time = world.getFullTime() + 300L;
        new BukkitRunnable() {
            @Override
            public void run() {
                long remaining = (time - world.getFullTime()) / 20;
                if(remaining % 5 == 0 || remaining < 6) {
                    event.getPlayer().sendMessage(getDisplayName() + ": " + remaining + "s");
                    world.playEffect(event.getPlayer().getLocation(), Effect.SMOKE, 1 ,1);
                    world.playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 1, 1);
                    world.playEffect(location, Effect.ENDER_SIGNAL, 1, 3);
                    if(remaining <= 0) {
                        cancel();
                    }
                }
            }
        }.runTaskTimer(PapierFuchs.INSTANCE, 20L, 20L);
    }

    @Override
    public boolean isChargeAble() {
        return true;
    }
}
