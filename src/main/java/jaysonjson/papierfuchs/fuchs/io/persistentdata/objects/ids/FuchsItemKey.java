package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;

import java.io.Serial;
import java.io.Serializable;

public class FuchsItemKey implements Serializable {

    @Serial
    private transient static final long serialVersionUID = 0L;

    private String key = "";
    private FuchsLanguageString displayName = new FuchsLanguageString();

    public FuchsItemKey() {

    }

    public FuchsItemKey(String key, FuchsLanguageString languageString) {
        this.key = key;
        this.displayName = languageString;
    }

    public FuchsLanguageString getDisplayName() {
        return displayName;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "FuchsItemKey{" +
                "key='" + key + '\'' +
                ", displayName=" + displayName +
                '}';
    }
}
