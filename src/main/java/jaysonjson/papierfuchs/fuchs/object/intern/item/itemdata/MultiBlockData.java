package jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemBlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Light;

import java.io.Serial;
import java.io.Serializable;

public class MultiBlockData implements Serializable {

    @Serial
    private transient static final long serialVersionUID = 0L;

    private Material type;
    private FuchsLocation location;

    public MultiBlockData() {

    }

    public MultiBlockData(Block block) {
        type = block.getType();
        location = new FuchsLocation(block.getLocation());
    }

    public MultiBlockData(Material type, Location location) {
        this.type = type;
        this.location = new FuchsLocation(location);
    }

    public void setType(Material type) {
        this.type = type;
    }

    public Material getType() {
        return type;
    }

    public void setLocation(FuchsLocation location) {
        this.location = location;
    }

    public FuchsLocation getLocation() {
        return location;
    }

    public void onCreate(Block block, FuchsItemBlockData blockData) {
        if(blockData != null) {
            if (block.getBlockData() instanceof Light light) {
                light.setLevel(blockData.getLightLevel());
                block.setBlockData(light);
            }
        }
    }
}
