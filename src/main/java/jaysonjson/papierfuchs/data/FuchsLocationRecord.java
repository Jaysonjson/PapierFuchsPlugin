package jaysonjson.papierfuchs.data;

import org.bukkit.Location;
import org.bukkit.World;

public record FuchsLocationRecord(double x, double y, double z) {
    public Location createLocation(World world) {
        return new Location(world, x, y, z);
    }
}
