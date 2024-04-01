package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.cosmetic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import org.bukkit.Material;

public class IFPlaceablePetItem extends FuchsPetItem {
    public IFPlaceablePetItem(String id, Material material, int modeldata, FuchsLanguageString fuchsLanguageString, FuchsRarity rarity) {
        super(id, material, modeldata, fuchsLanguageString, rarity);
    }

    @Override
    public boolean canBeItemFramePlaced() {
        return true;
    }
}
