package jaysonjson.papierfuchs.fuchs.object.intern.block;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemBlockData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemPlaceType;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;

public interface IFuchsBlock {

    default double getDefaultHealth() {
        return 10d;
    }

    default boolean hasHitBox() {
        return true;
    }

    default boolean isSmall() {
        return true;
    }

    default FuchsItemBlockData.EffectiveTool getEffectiveTool() {
        return FuchsItemBlockData.EffectiveTool.NONE;
    }

    default Collection<FuchsRegistryObject<? extends FuchsItem>> getDrops() {
        return new ArrayList<>();
    }

    default boolean dropSelf() {
        return getDrops().size() < 1;
    }

    @Nullable
    default FuchsRegistryObject<? extends FuchsItem> asItem() {
        return null;
    }

    default int getDefaultLightLevel() {
        return 0;
    }

    default Collection<Vector> getLightPlacements(Location location) {
        return new ArrayList<>();
    }

    @Deprecated
    default ItemPlaceType getPlaceType() {
        return ItemPlaceType.HEAD;
    }

    default boolean shouldPlaceRotate() {
        return true;
    }

    default Collection<Vector> getBarrierLocations(Location location) {
        return new ArrayList<>();
    }

    default Collection<FuchsBlockArmorStand> getArmorStands() {
        ArrayList<FuchsBlockArmorStand> armorStands = new ArrayList<>();
        armorStands.add(new FuchsBlockArmorStand(new Vector(0, 0, 0), getItemModelData()));
        return armorStands;
    }

    default int getItemModelData() {
        return -1;
    }

    default Material getMaterial() {
        return Material.FEATHER;
    }

}
