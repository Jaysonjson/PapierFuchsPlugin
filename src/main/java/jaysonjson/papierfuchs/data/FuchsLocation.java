package jaysonjson.papierfuchs.data;

import org.bukkit.Location;
import org.bukkit.World;

public class FuchsLocation {
    public double x;
    public double y;
    public double z;

    public FuchsLocation() {

    }

    public FuchsLocation(double x, double y, double z) {
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

    public Location createLocation(World world) {
        return new Location(world, getX(), getY(), getZ());
    }

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
