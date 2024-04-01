package jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.workbench;

import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.FuchsRecipe;
import jaysonjson.papierfuchs.fuchs.object.crafting.vanilla.FuchsRecipeShapeType;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class FuchsWorkbenchRecipeNew extends FuchsRecipe {
    /*public String shape = "AAA" +
                          "AAA" +
                          "AAA";*/
    private ArrayList<String> shape = new ArrayList<>();
    private HashMap<Character, zCraftingItem> keys = new HashMap<>();


    public int getShapeSize() {
        return shape.size();
    }

    public FuchsRecipeShapeType getType() {
        if(getShapeSize() == 1) {
            return FuchsRecipeShapeType.ONE;
        } else if(getShapeSize() == 2) {
            return FuchsRecipeShapeType.TWO;
        } else if(getShapeSize() == 3) {
            return FuchsRecipeShapeType.THREE;
        }
        return FuchsRecipeShapeType.UNKNOWN;
    }

    public boolean isValid() {
        if(getType() == FuchsRecipeShapeType.UNKNOWN) {
            FuchsLog.error("Unbekanntes Shape beim Rezept: " + getID());
            return false;
        }

        return true;
    }

    public ArrayList<String> getShape() {
        return shape;
    }

    @Nullable
    public zCraftingItem getItemFromKey(Character key) {
        return getKeys().get(key);
    }

    public HashMap<Character, zCraftingItem> getKeys() {
        return keys;
    }

    @Nullable
    public zCraftingItem getItemFromSlot(int slot) {
        switch (getType()) {
            case ONE -> {
                if (slot == 1 || slot == 4 || slot == 7) {
                    return getItemFromKey(getShape().get(0).charAt(0));
                } else if (slot == 2 || slot == 5 || slot == 8) {
                    return getItemFromKey(getShape().get(0).charAt(1));
                } else if (slot == 3 || slot == 6 || slot == 9) {
                    return getItemFromKey(getShape().get(0).charAt(2));
                }
            }

            case TWO -> {

            }

            case THREE -> {
                if(slot > 1 && slot < 4) {
                    return getItemFromKey(getShape().get(0).charAt(slot - 1));
                } else if(slot > 3 && slot < 7) {
                    return getItemFromKey(getShape().get(1).charAt(slot - 1));
                } else if (slot > 6 && slot < 10) {
                    return getItemFromKey(getShape().get(3).charAt(slot - 1));
                }
            }
        }
        return null;
    }

    public void setKeys(HashMap<Character, zCraftingItem> keys) {
        this.keys = keys;
    }

    public void setShape(ArrayList<String> shape) {
        this.shape = shape;
    }
}
