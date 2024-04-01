package jaysonjson.papierfuchs.fuchs.object.intern.language;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.io.File;

public interface IFuchsLanguage {
    void loadContent();
    File getFile();
    FuchsItem getItem();

    boolean hasContent();
}
