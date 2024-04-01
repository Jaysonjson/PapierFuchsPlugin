package jaysonjson.papierfuchs.fuchs.io.data.server.data;

import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.MultiStructureData;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class ByteFuchsWorld implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private final ArrayList<FWBlockChange> blockChanges = new ArrayList<>();
    private ArrayList<EntityMetadataSetter> entityMetadatas = new ArrayList<>();
    private ArrayList<BlockMetadataSetter> blockMetadatas = new ArrayList<>();
    private ArrayList<MultiStructureData> multiStructures = new ArrayList<>();
    private UUID uuid;


    public ArrayList<BlockMetadataSetter> getBlockMetadatas() {
        return blockMetadatas;
    }

    public ArrayList<EntityMetadataSetter> getEntityMetadatas() {
        return entityMetadatas;
    }

    public void setBlockMetadatas(ArrayList<BlockMetadataSetter> blockMetadatas) {
        this.blockMetadatas = blockMetadatas;
    }

    public void setEntityMetadatas(ArrayList<EntityMetadataSetter> entityMetadatas) {
        this.entityMetadatas = entityMetadatas;
    }

    public UUID getWorldUuid() {
        return uuid;
    }

    public void setWorldUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public ArrayList<FWBlockChange> getBlockChanges() {
        return blockChanges;
    }

    public ArrayList<MultiStructureData> getMultiStructures() {
        return multiStructures;
    }

    @Nullable
    public MultiStructureData getMultiStructure(UUID uuid) {
        for (MultiStructureData multiStructure : getMultiStructures()) {
            if(multiStructure.isSameStructure(uuid)) {
                return multiStructure;
            }
        }
        return null;
    }

    public String path() {
        return FileHandler.UNEDITABLE_WORLD + getWorldUuid();
    }
}
