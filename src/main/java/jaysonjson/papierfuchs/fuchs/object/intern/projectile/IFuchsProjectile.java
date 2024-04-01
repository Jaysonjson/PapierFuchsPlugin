package jaysonjson.papierfuchs.fuchs.object.intern.projectile;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public interface IFuchsProjectile {

    ItemStack asItem(Player player, ItemStack itemStack);
    default ItemStack asItem() {
        return asItem(null, null);
    }
    default void onHit(ProjectileHitEvent event, ItemStack itemStack) {

    }

    default void onShootBow(EntityShootBowEvent event) {

    }

    default void onThrow(ProjectileLaunchEvent event, ItemStack itemStack) {

    }


    default Projectiles getProjectileType() {
        return Projectiles.SNOWBALL;
    }

    default double getExtraVelocity() {
        return 0d;
    }

    default boolean destroyOnImpact() {
        return true;
    }

    default double getDamage() {
        return 0;
    }
}
