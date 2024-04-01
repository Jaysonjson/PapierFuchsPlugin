package jaysonjson.papierfuchs.fuchs.object.extern.custom.item;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.extern.custom.CustomObjectData;
import org.bukkit.Material;

import java.util.HashMap;

public class CustomItemData extends CustomObjectData {

    public Material type = Material.AIR;
    public int modelData = -1;
    public FuchsLanguageString display_name = new FuchsLanguageString();
    public String rarity = "papierfuchs:common";
    public String use_type = "papierfuchs:other";

    public HashMap<String, Integer> ints = new HashMap<>();
    public HashMap<String, String> strings = new HashMap<>();
    public HashMap<String, Boolean> bools = new HashMap<>();
    public HashMap<String, Float> floats = new HashMap<>();
    public HashMap<String, Double> doubles = new HashMap<>();

}
