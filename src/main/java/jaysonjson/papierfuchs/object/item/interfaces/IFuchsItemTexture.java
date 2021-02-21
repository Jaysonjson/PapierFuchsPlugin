package jaysonjson.papierfuchs.object.item.interfaces;

public interface IFuchsItemTexture {
    int getCustomModelData();
    default boolean hasCustomModelData() {
        return getCustomModelData() > -1;
    }
}
