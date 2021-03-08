package jaysonjson.papierfuchs.data;

import org.bukkit.Location;
import org.bukkit.World;

@Deprecated
public record FuchsLoc(double x, double y, double z) {

    public Location createLocation(World world) {
        return new Location(world, x, y, z);
    }

    public FuchsLocation createFuchsLocation(World world) {
        return new FuchsLocation(world, x, y, z);
    }
}
