package jaysonjson.papierfuchs.fuchs.io.data.drop.obj;

import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;

import java.util.ArrayList;

public class FuchsDrop {

    private String id = "";
    private ArrayList<zCraftingItem> items = new ArrayList<>();
    private FuchsLanguageString displayName = new FuchsLanguageString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<zCraftingItem> getItems() {
        return items;
    }

    public FuchsLanguageString getDisplayName() {
        return displayName;
    }
}
