package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.crafting;


import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.Material;

public class GlassItem extends FuchsItem {

    public GlassItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Glass");
    }
}
