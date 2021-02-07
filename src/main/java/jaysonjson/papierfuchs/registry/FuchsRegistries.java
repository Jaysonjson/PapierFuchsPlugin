package jaysonjson.papierfuchs.registry;

import jaysonjson.papierfuchs.object.gas.FuchsGas;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FuchsRegistries {

    public static Map<String, FuchsItem> items = new HashMap<>();
    public static Map<String, FuchsLiquid> liquids = new HashMap<>();
    public static Map<String, FuchsGas> gasses = new HashMap<>();

    public static void registerItems(FuchsItem... fuchsItem) {
        for (FuchsItem item : fuchsItem) {
            if(!items.containsKey(item.getID())) {
                items.put(item.getID(), item);
            } else {
                System.out.println("Item mit der ID " + item.getID() + " existiert bereits!");
            }
        }
    }

    public static void registerLiquids(FuchsLiquid... fuchsLiquid) {
        for (FuchsLiquid liquid : fuchsLiquid) {
            if(!liquids.containsKey(liquid.getID())) {
                liquids.put(liquid.getID(), liquid);
                System.out.println("Flüssigkeit mit der ID " + liquid.getID() + " registriert!");
            } else {
                System.out.println("Flüssigkeit mit der ID " + liquid.getID() + " existiert bereits!");
            }
        }
    }

    public static void registerGasses(FuchsGas... fuchsGases) {
        for (FuchsGas gas : fuchsGases) {
            if(!gasses.containsKey(gas.getID())) {
                gasses.put(gas.getID(), gas);
            } else {
                System.out.println("Gas mit der ID " + gas.getID() + " existiert bereits!");
            }
        }
    }

    public static void register(Class listClass) {
        Field[] fields = listClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                Object obj = field.get(listClass);
                if(obj instanceof FuchsItem) {
                    registerItems((FuchsItem) obj);
                }
                if(obj instanceof FuchsGas) {
                    registerGasses((FuchsGas) obj);

                }
                if(obj instanceof FuchsLiquid) {
                    registerLiquids((FuchsLiquid) obj);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sort() {
        items = new TreeMap<>(items);
    }

}
