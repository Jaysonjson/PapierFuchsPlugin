package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces.gun;

import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

public interface IFuchsItemGun {

    default void shoot(PlayerInteractEvent event) {

    }

    default void shootBow(EntityShootBowEvent event) {

    }

    @Deprecated
    default Vector getVelocity() {
        return new Vector(0, 0, 0);
    }
    default double getProjectileSpeed() {
        return 0;
    }
    @Nullable
    default FuchsProjectile getProjectile() {
        return null;
    }
    default boolean isGun() {
        return false;
    }
    @Deprecated
    default void onProjectileHit(ProjectileHitEvent event) { }

    default boolean requiresProjectileInv() {
        return false;
    }
}
