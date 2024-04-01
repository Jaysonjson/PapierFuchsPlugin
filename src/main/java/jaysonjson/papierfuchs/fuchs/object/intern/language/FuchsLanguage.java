package jaysonjson.papierfuchs.fuchs.object.intern.language;

import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.bukkit.ChatColor;

import java.io.File;
import java.util.Map;

public class FuchsLanguage extends FuchsObject implements IFuchsLanguage {

    public LanguageData language = new LanguageData();
    FuchsItem item;
    public FuchsLanguage(String id, FuchsItem item) {
        super(id, RegistryType.LANGUAGE);
        this.item = item;
    }

    @Override
    public void loadContent() {
        language.CONTENT.clear();
        String[] strs = getID().split(":");
        //language = (LanguageData) DataHandler.getData("language/" + strs[0] + "/" + strs[1] + ".json", LanguageData.class);
        String path = "language/" + strs[0] + "/" + strs[1] + ".json";
        for (FuchsRegistry registry : FuchsRegistries.registries) {
            if (registry.fuchsPlugin.getClass().getResource("/" + path) != null) {
                LanguageData internalData = DataHandler.gson.fromJson(FuchsUtility.getContentFromResource(registry.fuchsPlugin.getClass(), "/" + path), LanguageData.class);
                for (Map.Entry<String, String> entry : internalData.CONTENT.entrySet()) {
                    //FuchsLog.log(ChatColor.YELLOW + "Sprache (INTERN; RESOURCES) " + FuchsAnsi.CYAN + getID() + ChatColor.YELLOW + ", Entry,  Schlüssel {" + FuchsAnsi.CYAN + entry.getKey() + ChatColor.YELLOW + "} " + ", Wert {" + entry.getValue() + ChatColor.YELLOW + "}");
                    language.CONTENT.put(entry.getKey(), entry.getValue());
                }
                FuchsLog.log(ChatColor.RESET + "Sprache " + ChatColor.AQUA + getID() + ChatColor.RESET + " von Mod " + ChatColor.AQUA + registry.fuchsPlugin.getRegistryID() + ChatColor.RESET + " geladen!");
            }
        }
        //OVERRIDE RESOURCES
        if(getFile().exists()) {
            LanguageData externalData = DataHandler.gson.fromJson(DataHandler.readDataISO(getFile()), LanguageData.class);
            for (Map.Entry<String, String> entry : externalData.CONTENT.entrySet()) {
                //FuchsLog.log(ChatColor.YELLOW + "Sprache (EXTERN; PLUGINS) " + FuchsAnsi.CYAN + getID() + ChatColor.YELLOW + ", Entry,  Schlüssel {" + FuchsAnsi.CYAN + entry.getKey() + ChatColor.YELLOW + "} " + ", Wert {" + entry.getValue() + ChatColor.YELLOW + "}");
                language.CONTENT.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public File getFile() {
        if(getID().contains(":")) {
            String[] strs = getID().split(":");
            if(strs.length > 0) {
                return new File(FileHandler.LANG_DIR + "/" + strs[0] + "/" + strs[1] + ".json");
            }
        }
        return new File(FileHandler.LANG_DIR + getID().replace(getFuchsPlugin().getRegistryID() + ":", "") + ".json");
    }

    @Override
    public FuchsItem getItem() {
        return item;
    }

    @Override
    public boolean hasContent() {
        if(getFile().exists()) {
            return true;
        }
        if(getID().contains(":")) {
            String[] strs = getID().split(":");
            if(strs.length > 0) {
                return getClass().getResource("/language/" + strs[0] + "/" + strs[1] + ".json") != null;
            }
        }
        return false;
    }
}
