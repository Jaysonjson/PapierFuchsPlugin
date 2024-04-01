package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FuchsItemKey;

import java.util.HashMap;

public interface IFuchsItemStats {

    default HashMap<FuchsItemKey, Integer> getStatRequirements() {
        return new HashMap<>();
    }

    default HashMap<FuchsItemKey, Integer> getStatAdditions() {
        return new HashMap<>();
    }

    default HashMap<FuchsItemKey, Integer> getStatSubtractions() {
        return new HashMap<>();
    }

}
