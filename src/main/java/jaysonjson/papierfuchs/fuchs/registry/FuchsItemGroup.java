package jaysonjson.papierfuchs.fuchs.registry;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.util.ArrayList;
import java.util.TreeMap;

public class FuchsItemGroup {

    public ArrayList<FuchsRegistryObject<FuchsItem>> currencies = new ArrayList<>();
    public TreeMap<Float, FuchsRegistryObject<FuchsItem>> currenciesSorted = new TreeMap<>();

    public ArrayList<FuchsRegistryObject<FuchsItem>> food = new ArrayList<>();

    public ArrayList<FuchsRegistryObject<FuchsItem>> pets = new ArrayList<>();
    public ArrayList<FuchsRegistryObject<FuchsItem>> hats = new ArrayList<>();

    public void create() {
        float d = 0;
        for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.items.values()) {
            FuchsItem item = value.copy();
            if(item.isCurrency() || item.getCurrencyAmount() > 0) {
                currencies.add(value);
                d += 0.0001f;
                if(currenciesSorted.containsKey(item.getCurrencyAmount())) {
                    currenciesSorted.put(item.getCurrencyAmount() + d, value);
                } else {
                    currenciesSorted.put(item.getCurrencyAmount(), value);
                }
            }
            if(item.isPetItem()) {
                pets.add(value);
            }
            if(item.isHat()) {
                hats.add(value);
            }
            if(item.isFood()) {
                food.add(value);
            }
        }
    }

}
