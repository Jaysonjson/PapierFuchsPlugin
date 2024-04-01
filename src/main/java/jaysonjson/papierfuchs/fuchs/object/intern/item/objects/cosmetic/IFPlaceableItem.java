package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.cosmetic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.Material;

public class IFPlaceableItem extends FuchsItem {
    int modeldata;
    FuchsLanguageString fuchsLanguageString;
    public IFPlaceableItem(String id, Material material, IItemUseType itemUseType, int modeldata, FuchsLanguageString fuchsLanguageString) {
        super(id, material, itemUseType);
        this.modeldata = modeldata;
        this.fuchsLanguageString = fuchsLanguageString;
    }

    public FuchsLanguageString getFuchsLanguageString() {
        return fuchsLanguageString;
    }

    @Override
    public int getCustomModelData() {
        return modeldata;
    }

    @Override
    public boolean canBeItemFramePlaced() {
        return true;
    }
}
