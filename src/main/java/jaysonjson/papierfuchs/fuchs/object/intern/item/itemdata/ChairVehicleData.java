package jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata;

import com.google.common.io.BaseEncoding;
import jaysonjson.papierfuchs.fuchs.object.BlockMetaData;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.block.Block;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class ChairVehicleData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private float height = 0;
    private float x = 0;
    private float z = 0;
    private UUID armorStand;

    public ChairVehicleData() {

    }

    public ChairVehicleData(float height, UUID armorStand) {
        this.height = height;
        this.armorStand = armorStand;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setArmorStand(UUID armorStand) {
        this.armorStand = armorStand;
    }

    public UUID getArmorStand() {
        return armorStand;
    }

    public float getX() {
        return x;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public String encode() {
        return BaseEncoding.base16().encode(SerializationUtils.serialize(this));
    }

    public void encode(Block block) {
        FuchsUtility.addBlockMetadata(block, BlockMetaData.CHAIR_DATA, encode());
    }

    public static ChairVehicleData decode(String str) {
        return FuchsUtility.deserialize(BaseEncoding.base16().decode(str));
    }

    public static ChairVehicleData decode(Block block) {
        return decode(block.getMetadata(BlockMetaData.CHAIR_DATA).get(0).asString());
    }
}
