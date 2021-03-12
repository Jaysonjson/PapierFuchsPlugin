package jaysonjson.papierfuchs.object.language;

import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FileHandler;
import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.RegistryType;

import java.io.File;

public class FuchsLanguage extends FuchsObject implements IFuchsLanguage {

    public LanguageData language = new LanguageData();
    public FuchsLanguage(String id) {
        super(id, RegistryType.LANGUAGE);
    }

    @Override
    public void loadContent() {
        language = DataHandler.gson.fromJson(DataHandler.readDataISO(getFile()), LanguageData.class);
    }

    @Override
    public File getFile() {
        return new File(FileHandler.LANG_DIR + getID().replace(getFuchsPlugin().getPluginID() + ":", "") + ".json");
    }
}
