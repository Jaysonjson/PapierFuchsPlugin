package jaysonjson.papierfuchs.paper.events.entity;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemProjectileData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ProjectileHit implements Listener {

    @EventHandler
    public void onEvent(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if(projectile instanceof ThrowableProjectile throwableProjectile) {
            ItemStack itemStack = throwableProjectile.getItem();
            FuchsMCItem fuchsMCItem = new FuchsMCItem(itemStack);
            FuchsItemProjectileData projectileData = fuchsMCItem.projectileData().get();
            if(projectileData.isProjectile()) {
                if(FuchsUtility.projectileExists(projectileData.getProjectileId())) {
                    FuchsProjectile fuchsProjectile = FuchsUtility.getProjectileById(projectileData.getProjectileId());
                    fuchsProjectile.onHit(event, itemStack);
                    if(fuchsProjectile.destroyOnImpact()) {
                        projectile.remove();
                    } else {
                        if(projectileData.hasShooter()) {
                            Player player = Bukkit.getPlayer(projectileData.getShooter());
                            if(player.getInventory().firstEmpty() != -1) {
                                player.getInventory().addItem(FuchsUtility.damageFuchsItem(player, fuchsMCItem));
                            } else {
                                projectile.getWorld().dropItemNaturally(projectile.getLocation(), FuchsUtility.damageFuchsItem(player, fuchsMCItem));
                            }
                        } else {
                            projectile.getWorld().dropItemNaturally(projectile.getLocation(), fuchsMCItem.getItemStack());
                        }
                    }
                    if(event.getHitEntity() != null) {
                        Entity hitEntity = event.getHitEntity();
                        if(hitEntity instanceof LivingEntity livingEntity) {
                            //livingEntity.damage(fuchsProjectile.getDamage());
                            Entity player = projectile;
                            if(projectileData.hasShooter()) {
                                player = Bukkit.getPlayer(projectileData.getShooter());
                            }
                            new EntityDamage().entityDamageEvent(new EntityDamageByEntityEvent(player, livingEntity, EntityDamageEvent.DamageCause.ENTITY_ATTACK, fuchsProjectile.getDamage()));
                        }
                    }
                }
            }
            if(FuchsUtility.isFuchsItem(itemStack)) {
                FuchsItem fuchsItem = FuchsUtility.getFuchsItemFromNMS(throwableProjectile.getItem());
                fuchsItem.onProjectileHit(event);
            }
        }
    }

}
