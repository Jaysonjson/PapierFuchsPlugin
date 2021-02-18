package jaysonjson.papierfuchs.object.item.interfaces.gun;

import org.bukkit.util.Vector;

public interface IFuchsItemGun {

    Vector getVelocity();
    IFuchsItemAmmo getAmmo();
    IFuchsGunMagazine getMagazine();
    default boolean isGun() {
        return false;
    }

}
