package jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata;

import com.google.common.io.BaseEncoding;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemBlockData;
import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class MultiStructureData implements Serializable {

    @Serial
    private transient static final long serialVersionUID = 0L;

    private UUID sharedId;
    private UUID heart;
    private String heartItem = "";
    private String blockId = "";
    private Collection<MultiBlockData> blocks = new ArrayList<>();
    private Collection<UUID> slaves = new ArrayList<>();

    public MultiStructureData() {

    }

    public UUID getSharedId() {
        return sharedId;
    }

    public void setSharedId(UUID sharedId) {
        this.sharedId = sharedId;
    }

    public boolean isSameStructure(UUID blockData) {
        return blockData.equals(getSharedId());
    }

    public boolean isSameStructure(MultiStructureData blockData) {
        return isSameStructure(blockData.getSharedId());
    }

    public Collection<MultiBlockData> getBlocks() {
        return blocks;
    }

    public Collection<UUID> getSlaves() {
        return slaves;
    }

    public void setBlocks(Collection<MultiBlockData> blocks) {
        this.blocks = blocks;
    }

    public void setSlaves(Collection<UUID> slaves) {
        this.slaves = slaves;
    }

    public void remove() {
        World world = null;
        for (MultiBlockData multiBlock : blocks) {
            world = multiBlock.getLocation().getWorld();
            Block block = world.getBlockAt(multiBlock.getLocation().asBukkit());
            if(block.getType().equals(multiBlock.getType())) {
                block.setType(Material.AIR);
            }
        }
        if(world != null) {
            for (UUID uuid : slaves) {
                if(world.getEntity(uuid) != null && world.getEntity(uuid) instanceof ArmorStand armorStand) {
                    world.getBlockAt(armorStand.getLocation()).setType(Material.AIR);
                    armorStand.remove();
                }
            }
            if(world.getEntity(heart) != null && world.getEntity(heart) instanceof ArmorStand armorStand) {
                world.getBlockAt(armorStand.getLocation()).setType(Material.AIR);
                armorStand.remove();
            }
            References.data.getByteWorld(world).getMultiStructures().remove(this);
        }
    }

    public boolean create(World world, Collection<MultiBlockData> multiBlockData, ArmorStand heart, FuchsItemBlockData blockData) {
        setHeart(heart.getUniqueId());
        setSharedId(UUID.randomUUID());
        setBlocks(multiBlockData);
        HashMap<Block, MultiBlockData> toAdd = new HashMap<>();
        for (MultiBlockData blockDatum : multiBlockData) {
            Location location = blockDatum.getLocation().asBukkit();
            Block block = world.getBlockAt(location);
            if(!block.hasMetadata(BlockMetaData.MULTIBLOCK)) {
                if(block.getType().equals(Material.AIR) || block.getType().equals(Material.CAVE_AIR) || block.getType().equals(blockDatum.getType())) {
                    toAdd.put(block, blockDatum);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        for (Block block : toAdd.keySet()) {
            MultiBlockData blockDatum = toAdd.get(block);
            block.setType(blockDatum.getType());
            blockDatum.onCreate(block, blockData);
        }
        for (Block block : toAdd.keySet()) {
            encode(block);
        }
        References.data.getByteWorld(world).getMultiStructures().add(this);
        return true;
    }

    @Deprecated
    public String encode() {
        return BaseEncoding.base64().encode(SerializationUtils.serialize(this));
    }

    public void encode(Block block) {
        FuchsUtility.addBlockMetadata(block, BlockMetaData.MULTIBLOCK, getSharedId());
    }

    @Deprecated
    public static MultiStructureData decode(String str) {
        return FuchsUtility.deserialize(BaseEncoding.base16().decode(str));
    }

    @Deprecated
    public static MultiStructureData decode(Block block) {
        return decode(block.getMetadata(BlockMetaData.MULTIBLOCK).get(0).asString());
    }

    public UUID getHeart() {
        return heart;
    }

    public void setHeart(UUID heart) {
        this.heart = heart;
    }

    public String getHeartItem() {
        return heartItem;
    }

    public void setHeartItem(String heartItem) {
        this.heartItem = heartItem;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }
}
