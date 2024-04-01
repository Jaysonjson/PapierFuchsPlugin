package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import org.bukkit.Material;

public class ArrowRightItem extends FuchsItem {

    public ArrowRightItem(String id) {
        super(id, Material.FEATHER, UseTypeList.NONE.copy());
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.none.copy();
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Craftet zu:");
    }


    @Override
    public boolean showInItemList() {
        return false;
    }

    @Override
    public boolean showIdInLore() {
        return false;
    }

    @Override
    public int getCustomModelData() {
        return 76;
    }

    @Override
    public boolean isCategoryIcon() {
        return false;
    }
}
