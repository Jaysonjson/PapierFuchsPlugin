package jaysonjson.papierfuchs.fuchs.io.data.area.data;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsSize;
import jaysonjson.papierfuchs.fuchs.object.IProperty;
import jaysonjson.papierfuchs.fuchs.object.IPropertyEmpty;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class FuchsArea implements IPropertyEmpty {

    private UUID uuid;
    private String displayName;
    private FuchsLocation location = new FuchsLocation();
    private FuchsSize size = new FuchsSize();
    private UUID owner;
    //private FuchsWorld world = FuchsWorld.OVERWORLD;
    private int priority = 0;
    private ArrayList<String> properties = new ArrayList<>();

    public Location createLocation() {
        return location.asBukkit();
    }

    public Location createTeleportLocation() {
        return location.asBukkit().add(0, 2, 0);
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
    
    //public FuchsWorld getWorld() {
		//return world;
	//}

    public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

    public void setLocation(FuchsLocation location) {
		this.location = location;
	}
    
    public void setOwner(UUID owner) {
		this.owner = owner;
	}

    public void setPriority(int priority) {
		this.priority = priority;
	}
    
    public void setSize(FuchsSize size) {
		this.size = size;
	}

   // public void setWorld(FuchsWorld world) {
		//this.world = world;
	//}
    
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


    public String getArrayName() {
        return getPriority() + "_" + getUuid().toString();
    }

    public FuchsAreaHelper helper() {
        return new FuchsAreaHelper(this);
    }

    @Override
    public ArrayList<String> getProperties() {
        return properties;
    }
}
