package jaysonjson.papierfuchs.fuchs.object;

public interface IModelData {

    default int getCustomModelData() {
        return -1;
    }

    default boolean hasCustomModelData() {
        return getCustomModelData() != -1;
    }

}
