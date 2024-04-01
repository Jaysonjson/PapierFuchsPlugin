package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class FuchsSoundDiscItem extends FuchsItem {

    public FuchsSoundDiscItem() {
        super("sound_disc", Material.FEATHER, UseTypeList.NONE.copy());
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c(ChatColor.AQUA + "Sound Disc");
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.sound.copy() };
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.none.copy();
    }
}
