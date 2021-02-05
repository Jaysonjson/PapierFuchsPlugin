package jaysonjson.papierfuchs.data.drop.obj;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import org.bukkit.entity.EntityType;
import java.util.HashMap;

public class zMobDrop {
    public EntityType type = EntityType.BOAT;
    public HashMap<String, Integer> itemDropsID = new HashMap<>();
    public transient HashMap<FuchsItem, Integer> itemDrops = new HashMap<>();
}
