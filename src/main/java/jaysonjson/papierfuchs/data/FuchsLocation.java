package jaysonjson.papierfuchs.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;


/**
 * Dient zum Speichern einer Bukkit-Location
 */
public class FuchsLocation {

    private double x = 0;
    private double y = 0;
    private double z = 0;
    private String world_str = "world";
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
    }

    @Deprecated
    public FuchsLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world_str = world.getName();
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
     * Aktualisiert den Welt-String zum aktuellen Name der Welt
     */
    public void updateWorldString() {
        if(world != null) {
            this.world_str = world.getName();
        } else {
            this.world_str = "world";
        }
    }

    /**
     * Aktualisiert die Welt zum aktuellen Name der Welt
     */
    public void updateWorldFromString() {
        this.world = Bukkit.getWorld(world_str);
    }

    /**
     * Vergleicht zwei FuchsLocations
     * @param location Die location, welche mit dieser verglichen werden soll
     */
    public boolean compare(FuchsLocation location) {
        return x == location.getX() && y == location.getY() && z == location.getZ();
    }

    @Override
    public String toString() {
        return "FuchsLocation{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
