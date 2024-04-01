package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

public interface IFuchsItemFertilizer {

    default float getFertilizerAmount() {
        return 0;
    }

    default boolean isFertilizer() {
       return getFertilizerAmount() > 0;
    }

}
