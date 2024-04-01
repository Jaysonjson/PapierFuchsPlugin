package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import org.jetbrains.annotations.Nullable;

public interface IFuchsItemPlant {

    @Nullable
    default FuchsItem getFruit() {
        return null;
    }

    default boolean hasFruit() {
        return getFruit() != null;
    }

    default int getPlantGrowTime() {
        return 0;
    }

    default int getPlantModelData() {
        return -1;
    }

    default int getFinishedPlantModelData() {
        return -1;
    }

    default int maxFruitDrop() {
        return 0;
    }

    default int minFruitDrop() {
        return 0;
    }

}
