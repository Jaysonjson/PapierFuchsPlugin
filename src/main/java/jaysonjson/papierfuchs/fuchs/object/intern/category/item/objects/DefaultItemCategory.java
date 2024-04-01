package jaysonjson.papierfuchs.fuchs.object.intern.category.item.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;

public class DefaultItemCategory extends FuchsItemCategory {

    FuchsLanguageString languageString;
    public DefaultItemCategory(String id, FuchsLanguageString languageString) {
        super(id);
        this.languageString = languageString;
    }


    @Override
    public FuchsLanguageString getDisplayName() {
        return languageString;
    }
}
