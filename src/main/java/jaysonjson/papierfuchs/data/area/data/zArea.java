package jaysonjson.papierfuchs.data.area.data;

import jaysonjson.papierfuchs.data.FuchsLocation;
import jaysonjson.papierfuchs.data.FuchsSize;
import jaysonjson.papierfuchs.data.area.obj.FuchsWorld;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class zArea {

    private UUID uuid;
    private String displayName;
    private FuchsLocation location = new FuchsLocation();
    private FuchsSize size = new FuchsSize();
    private UUID owner;
    private FuchsWorld world = FuchsWorld.OVERWORLD;
    private HashMap<String, Boolean> properties = new HashMap<>();
    private int priority = 0;

    public Location createLocation(World world) {
        return new Location(world, location.getX(), location.getY(), location.getZ());
    }

    public Location createTeleportLocation(World world) {
        return new Location(world, location.getX(), location.getY() + 2, location.getZ());
    }

    public boolean canOverlap(Player player) {
        if(player != null) {
            if (player.getUniqueId().equals(owner)) {
                return isAllowOwnerOverlap();
            }
        }
        return isAllowOverlap();
    }

    public boolean canOverlap() {
        return canOverlap(null);
    }
    
    public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
    
    public UUID getUuid() {
		return uuid;
	}
    
    public String getDisplayName() {
		return displayName;
	}
    
    public FuchsLocation getLocation() {
		return location;
	}
   
    public UUID getOwner() {
		return owner;
	}

    public int getPriority() {
		return priority;
	}
    
    public FuchsSize getSize() {
		return size;
	}
    
    public FuchsWorld getWorld() {
		return world;
	}
    
    public void setAllowOverlap(boolean allowOverlap) {
        properties.put(FuchsAreaProperty.ALLOW_OVERLAP, allowOverlap);
	}
    
    public void setAllowOwnerOverlap(boolean allowOwnerOverlap) {
        properties.put(FuchsAreaProperty.ALLOW_OWNER_OVERLAP, allowOwnerOverlap);
    }
    
    public void setBreakBlocks(boolean breakBlocks) {
        properties.put(FuchsAreaProperty.BREAK_BLOCKS, breakBlocks);
	}
    
    public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
    
    public void setDropItems(boolean dropItems) {
        properties.put(FuchsAreaProperty.DROP_ITEMS, dropItems);
	}
    
    public void setLocation(FuchsLocation location) {
		this.location = location;
	}
    
    public void setOwner(UUID owner) {
		this.owner = owner;
	}
    
    public void setPlaceBlocks(boolean placeBlocks) {
        properties.put(FuchsAreaProperty.PLACE_BLOCKS, placeBlocks);
	}
    
    public void setPriority(int priority) {
		this.priority = priority;
	}
    
    public void setSize(FuchsSize size) {
		this.size = size;
	}
    
    public void setSpawnMobs(boolean spawnMobs) {
        properties.put(FuchsAreaProperty.SPAWN_MOBS, spawnMobs);
	}
    
    public void setWorld(FuchsWorld world) {
		this.world = world;
	}
    
    public boolean isAllowOverlap() {
		return getProperty(FuchsAreaProperty.ALLOW_OVERLAP);
	}
    
    public boolean isAllowOwnerOverlap() {
		return getProperty(FuchsAreaProperty.ALLOW_OWNER_OVERLAP);
	}
    
    public boolean isBreakBlocks() {
		return getProperty(FuchsAreaProperty.BREAK_BLOCKS);
	}
    
    public boolean isDropItems() {
		return getProperty(FuchsAreaProperty.DROP_ITEMS);
	}
    
    public boolean isPlaceBlocks() {
		return getProperty(FuchsAreaProperty.PLACE_BLOCKS);
	}
    
    public boolean isSpawnMobs() {
		return getProperty(FuchsAreaProperty.SPAWN_MOBS);
	}

	public boolean getProperty(String property) {
        if(properties.containsKey(property)) {
            return properties.get(property);
        }
        return false;
    }

    public String getArrayName() {
        return getPriority() + "_" + getUuid().toString();
    }
}
