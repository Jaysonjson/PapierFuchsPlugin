package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import org.bukkit.Material;
import org.bukkit.World;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class FuchsItemBlockData implements Serializable {

    public static final byte MAX_LIGHT = 15;
    public static final byte MIN_LIGHT = 0;

    public enum EffectiveTool {
        AXE, PICKAXE, SWORD, SHOVEL, HOE, NONE;

        public static FuchsItemBlockData.EffectiveTool fromMaterial(Material material) {
            String materialName = material.name().toLowerCase();
            if(materialName.contains("pickaxe")) {
                return PICKAXE;
            } else if(materialName.contains("axe")) {
                return AXE;
            } else if(materialName.contains("sword")) {
                return SWORD;
            } else if(materialName.contains("shovel")) {
                return SHOVEL;
            } else if(materialName.contains("hoe")) {
                return HOE;
            }
            return NONE;
        }
    }

    @Serial
    private static final long serialVersionUID = 0L;

    private int lightLevel = 0;
    private Collection<FuchsLocation> lightLocations = new ArrayList<>();
    private EffectiveTool effectiveTool = EffectiveTool.NONE;
    private Collection<FuchsLocation> barrierLocations = new ArrayList<>();

    public FuchsItemBlockData() {

    }

    public FuchsItemBlockData(FuchsItem fuchsItem) {
        if(fuchsItem.asBlock() != null) {
            lightLevel = fuchsItem.asBlock().copy().getDefaultLightLevel();
            effectiveTool = fuchsItem.asBlock().copy().getEffectiveTool();
        }
    }

    public int getLightLevel() {
        validate();
        return lightLevel;
    }

    public void setLightLevel(int lightLevel) {
        this.lightLevel = lightLevel;
        validate();
    }

    public void checkLightLevel() {
        if(lightLevel < MIN_LIGHT) {
            lightLevel = MIN_LIGHT;
        }
        if(lightLevel > MAX_LIGHT) {
            lightLevel = MAX_LIGHT;
        }
    }

    public void validate() {
        checkLightLevel();
    }

    public Collection<FuchsLocation> getLightLocations() {
        return lightLocations;
    }

    public void getLightLocations(Collection<FuchsLocation> locations) {
        this.lightLocations = locations;
    }

    public boolean hasLights() {
        return getLightLocations().size() > 0;
    }

    public void removeLights() {
        if(hasLights()) {
            for (FuchsLocation location : getLightLocations()) {
                World world = location.getWorld();
                if(world.getBlockAt(location.asBukkit()).getType().equals(Material.LIGHT)) {
                    world.getBlockAt(location.asBukkit()).setType(Material.AIR);
                }
            }
            getLightLocations().clear();
        }
    }

    public void setEffectiveTool(EffectiveTool effectiveTool) {
        this.effectiveTool = effectiveTool;
    }

    public EffectiveTool getEffectiveTool() {
        return effectiveTool;
    }

    public Collection<FuchsLocation> getBarrierLocations() {
        return barrierLocations;
    }

    public void setBarrierLocations(Collection<FuchsLocation> barrierLocations) {
        this.barrierLocations = barrierLocations;
    }

    public boolean hasBarrier() {
        return getBarrierLocations().size() > 0;
    }

    public void removeBarrier() {
        if(hasBarrier()) {
            for (FuchsLocation barrierLocation : barrierLocations) {
                World world = barrierLocation.getWorld();
                if(world.getBlockAt(barrierLocation.asBukkit()).getType().equals(Material.BARRIER)) {
                    world.getBlockAt(barrierLocation.asBukkit()).setType(Material.AIR);
                }
            }
            barrierLocations.clear();
        }
    }

    @Override
    public String toString() {
        return "FuchsItemBlockData{" +
                "lightLevel=" + lightLevel +
                ", effectiveTool=" + effectiveTool +
                '}';
    }
}
