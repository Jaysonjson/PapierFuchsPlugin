package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import javax.annotation.Nullable;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class FuchsItemProjectileData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private String projectileId = "";
    private String containedProjectile = "";

    @Nullable
    private UUID shooter = null;

    public FuchsItemProjectileData() {

    }

    public FuchsItemProjectileData(FuchsItem fuchsItem) {
        if(fuchsItem.getProjectile() != null) {
            projectileId = fuchsItem.getProjectile().getID();
            containedProjectile = fuchsItem.getProjectile().getID();
        }
    }

    public String getProjectileId() {
        return projectileId;
    }

    public void setProjectileId(String projectileId) {
        this.projectileId = projectileId;
    }

    public boolean isProjectile() {
        return !getProjectileId().equals("");
    }

    public boolean hasProjectile() {
        return !containedProjectile.equals("");
    }

    public void setContainedProjectile(String containedProjectile) {
        this.containedProjectile = containedProjectile;
    }

    public String getContainedProjectile() {
        return containedProjectile;
    }

    @Nullable
    public UUID getShooter() {
        return shooter;
    }

    public void setShooter(@Nullable UUID shooter) {
        this.shooter = shooter;
    }

    public boolean hasShooter() {
        return getShooter() != null;
    }

    public void resetShooter() {
        setShooter(null);
    }

    @Override
    public String toString() {
        return "FuchsItemProjectileData{" +
                "projectileId='" + projectileId + '\'' +
                ", containedProjectile='" + containedProjectile + '\'' +
                ", shooter=" + shooter +
                '}';
    }
}
