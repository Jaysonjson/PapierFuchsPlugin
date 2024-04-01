package jaysonjson.papierfuchs.fuchs.io.data.player.data;

import jaysonjson.papierfuchs.fuchs.object.IProperty;
import jaysonjson.papierfuchs.fuchs.object.IPropertyEmpty;
import jaysonjson.papierfuchs.fuchs.object.intern.language.LanguageList;

import java.util.ArrayList;

public class FuchsPlayerSettings implements IPropertyEmpty {

    private String language = LanguageList.GERMAN.copy().getID();
    private ArrayList<String> properties = new ArrayList<>();

    public boolean isHideScoreboard() {
        return getProperty(FPSProperty.hideScoreboard);
    }

    public boolean isHideItemLore() {
        return getProperty(FPSProperty.hideItemLore);
    }

    public void setHideScoreboard(boolean hideScoreboard) {
        setProperty(FPSProperty.hideScoreboard, hideScoreboard);
    }

    public void setHideItemLore(boolean hideItemLore) {
        setProperty(FPSProperty.hideItemLore, hideItemLore);
    }

    public String getLanguageID() {
        return language;
    }

    public void setLanguageID(String language) {
        this.language = language;
    }

    @Override
    public ArrayList<String> getProperties() {
        return properties;
    }
}
