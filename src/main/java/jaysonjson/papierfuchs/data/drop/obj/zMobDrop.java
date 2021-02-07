package jaysonjson.papierfuchs.data.drop.obj;

import org.bukkit.entity.EntityType;

import java.util.ArrayList;

public class zMobDrop {
    public EntityType type = EntityType.BOAT;
    public ArrayList<ItemDropChance> items = new ArrayList<>();

    public transient ArrayList<ItemDropChance> itemDropChances = new ArrayList<>();
    //public HashMap<String, Integer> itemDropsID = new HashMap<>();
    //public transient HashMap<FuchsItem, Integer> itemDrops = new HashMap<>();

    @Override
    public String toString() {
        return "zMobDrop{" +
                "type=" + type +
                ", items=" + items +
                ", itemDropChances=" + itemDropChances +
                '}';
    }
}
