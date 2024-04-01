package jaysonjson.papierfuchs.paper.events.entity.player;

import com.google.common.io.BaseEncoding;
import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemToolData;
import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.ChairVehicleData;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.paper.events.entity.EntityDamage;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = event.getPlayer().getWorld();
        ItemStack item = player.getInventory().getItemInMainHand();
        Block block = event.getClickedBlock();
        if(event.getAction() == Action.LEFT_CLICK_AIR) {
            FuchsItemToolData toolData = new FuchsMCItem(player, item).toolData().get();
            if (toolData.isExtraRanged()) {
                RayTraceResult rayTraceResult = world.rayTraceEntities(player.getEyeLocation().add(player.getLocation().getDirection()), player.getLocation().getDirection(), toolData.getExtraRange());
                if (rayTraceResult != null && rayTraceResult.getHitEntity() != null && !rayTraceResult.getHitEntity().getUniqueId().equals(player.getUniqueId())) {
                    if(rayTraceResult.getHitEntity() instanceof LivingEntity livingEntity) {
                        livingEntity.damage(toolData.getAttackDamage());
                        new EntityDamage().entityDamageEvent(new EntityDamageByEntityEvent(player, livingEntity, EntityDamageEvent.DamageCause.ENTITY_ATTACK, toolData.getAttackDamage()));
                    }
                }
            }
        }
        if(block != null && !player.isSneaking()) {
            if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if (block.hasMetadata(BlockMetaData.CHAIR_DATA)) {
                    ChairVehicleData chairVehicleData = ChairVehicleData.decode(block);
                    if (world.getEntity(chairVehicleData.getArmorStand()) != null) {
                        if (world.getEntity(chairVehicleData.getArmorStand()) instanceof ArmorStand armorStand) {
                            if (armorStand.getPassengers().size() < 1) {
                                armorStand.addPassenger(player);
                            }
                        }
                    } else {
                        block.removeMetadata(BlockMetaData.CHAIR_DATA, PapierFuchs.INSTANCE);
                    }
                }
            }
        }
    }
}
