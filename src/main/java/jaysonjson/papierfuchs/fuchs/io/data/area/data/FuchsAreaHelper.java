package jaysonjson.papierfuchs.fuchs.io.data.area.data;

import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public record FuchsAreaHelper(FuchsArea area) {

    public boolean isInArea(Player player) {
        Location p1 = area.createLocation().add(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
        Location p2 = area.createLocation().subtract(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
        return FuchsUtility.isInArea(player.getLocation(), p1, p2);
    }

    public boolean canPlaceBlocks(Player player) {
        return canProperty(area.isPlaceBlocks(), player);
    }

    public boolean canBreakBlocks(Player player) {
        return canProperty(area.isBreakBlocks(), player);
    }

    public boolean canProperty(boolean property, Player player) {
        if (!player.isOp()) {
            if (isInArea(player)) {
                return property;
            }
        } else {
            return true;
        }
        return false;
    }
}
