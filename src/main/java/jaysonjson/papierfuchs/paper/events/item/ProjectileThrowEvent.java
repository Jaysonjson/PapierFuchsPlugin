package jaysonjson.papierfuchs.paper.events.item;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemProjectileData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class ProjectileThrowEvent implements Listener {

    @EventHandler
    public void onThrow(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        if(projectile instanceof ThrowableProjectile throwableProjectile) {
            ItemStack itemStack = throwableProjectile.getItem();
            FuchsMCItem fuchsMCItem = new FuchsMCItem(itemStack);
            //System.out.println(itemStack);
            FuchsItemProjectileData projectileData = fuchsMCItem.projectileData().get();
            //System.out.println(projectileData.isProjectile());
            //System.out.println(projectileData.getProjectileId());
            if(projectileData.isProjectile()) {
                if(FuchsUtility.projectileExists(projectileData.getProjectileId())) {
                    FuchsProjectile fuchsProjectile = FuchsUtility.getProjectileById(projectileData.getProjectileId());
                    fuchsProjectile.onThrow(event, itemStack);
                }
            }
        }
    }
}
