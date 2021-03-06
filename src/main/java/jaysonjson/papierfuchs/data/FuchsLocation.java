package jaysonjson.papierfuchs.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;


/**
 * Dient zum Speichern einer Bukkit-Location
 */
public class FuchsLocation {

    private double x;
    private double y;
    private double z;
    private String world_str;
    private transient World world;

    public FuchsLocation() {

    }

    public FuchsLocation(Location location) {
        this.world = location.getWorld();
        updateWorldString();
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
    }

    public FuchsLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public FuchsLocation(World world, double x, double y, double z) {
        this.world = world;
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
        return createLocation(world);
    }

    /**
     * Aktualisiert den Welt-String zum aktuellen Name der Welt
     */
    public void updateWorldString() {
        this.world_str = world.getName();
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
