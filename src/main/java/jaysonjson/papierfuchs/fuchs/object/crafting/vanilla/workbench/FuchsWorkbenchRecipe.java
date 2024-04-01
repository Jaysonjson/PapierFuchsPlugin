package jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.workbench;

import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.FuchsRecipe;

import java.util.ArrayList;

public class FuchsWorkbenchRecipe extends FuchsRecipe {
    public ArrayList<zCraftingItem> matrix = new ArrayList<>();
    public zCraftingItem result = new zCraftingItem();
    public boolean shapeless = false;
}
