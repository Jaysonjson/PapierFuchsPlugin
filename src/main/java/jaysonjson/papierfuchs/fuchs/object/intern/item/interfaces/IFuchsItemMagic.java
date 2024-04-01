package jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FuchsItemKey;

import java.util.HashMap;

public interface IFuchsItemMagic {

    default HashMap<FuchsItemKey, Float> getMagic() {
        return new HashMap<>();
    }

}
