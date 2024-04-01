package jaysonjson.papierfuchs.fuchs.io.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


/**
 * Dient zum Speichern einer Bukkit-Location
 */
public class FuchsLocation implements Serializable {

    @Serial
    private transient static final long serialVersionUID = 0L;

    private double x = 0;
    private double y = 0;
    private double z = 0;
    private float yaw = 0;
    private float pitch = 0;
    private UUID worldUid;
    private transient World world;

    public FuchsLocation() {

    }

    public FuchsLocation(Location location) {
        this.world = location.getWorld();
        if(world == null) {
            updateWorldFromString();
        }
        updateWorldString();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
    }

    @Deprecated
    public FuchsLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        updateWorldFromString();
    }

    @Deprecated
    public FuchsLocation(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        updateWorldFromString();
    }

    public FuchsLocation(World world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        if(world == null) {
            updateWorldFromString();
        }
        updateWorldString();
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public FuchsLocation(World world, Location location) {
        this.world = world;
        if(world == null) {
            updateWorldFromString();
        }
        updateWorldString();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
    }

    public FuchsLocation(World world, double x, double y, double z) {
        this.world = world;
        if(world == null) {
            updateWorldFromString();
        }
        updateWorldString();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public World getWorld() {
        if(world == null) {
            updateWorldFromString();
        }
        return world;
    }

    public void setWorld(World world) {
        this.worldUid = world.getUID();
        this.world = world;
    }

    public Location createLocation(World world) {
        return new Location(world, getX(), getY(), getZ());
    }

    /**
     * Kreiert eine Bukkit-Location von dieser
     * @return Bukkit-Location
     */
    public Location createLocation() {
        updateWorldFromString();
        return createLocation(world);
    }

    /**
     * Einfach nur ein rename
     * @return
     */
    public Location asBukkit() {
        return createLocation();
    }

    /**
     * Aktualisiert den Welt-String zum aktuellen Name der Welt
     */
    public void updateWorldString() {
        if(world != null) {
            this.worldUid = world.getUID();
        } else {
            this.worldUid = null;
        }
    }

    /**
     * Aktualisiert die Welt zum aktuellen Name der Welt
     */
    public void updateWorldFromString() {
        this.world = Bukkit.getWorld(worldUid);
    }

    /**
     * Vergleicht zwei FuchsLocations
     * @param location Die location, welche mit dieser verglichen werden soll
     */
    public boolean compare(FuchsLocation location) {
        return worldUid.equals(location.worldUid) && x == location.getX() && y == location.getY() && z == location.getZ();
    }

    public boolean compare(Location location) {
        return worldUid.equals(location.getWorld().getUID()) && x == location.getX() && y == location.getY() && z == location.getZ();
    }

    @Override
    public String toString() {
        return "FuchsLocation{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                ", worldUid='" + worldUid + '\'' +
                '}';
    }

    public boolean validate() {
        return world != null;
    }
}
