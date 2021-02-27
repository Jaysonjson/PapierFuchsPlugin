package jaysonjson.papierfuchs.registry;

import jaysonjson.papierfuchs.FuchsAnsi;
import jaysonjson.papierfuchs.object.effect.FuchsEffect;
import jaysonjson.papierfuchs.object.entity.FuchsEntity;
import jaysonjson.papierfuchs.object.gas.FuchsGas;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.object.rarity.FuchsRarity;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FuchsRegistries {

    public static Map<String, FuchsItem> items = new HashMap<>();
    public static Map<String, FuchsLiquid> liquids = new HashMap<>();
    public static Map<String, FuchsGas> gasses = new HashMap<>();
    public static Map<String, FuchsEffect> effects = new HashMap<>();
    public static Map<String, FuchsRarity> rarities = new HashMap<>();
    public static Map<String, FuchsEntity> entities = new HashMap<>();

    public static void registerItems(FuchsItem... fuchsItem) {
        for (FuchsItem item : fuchsItem) {
            if(!items.containsKey(item.getID())) {
                items.put(item.getID(), item);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Item mit der ID " + item.getID() + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Item mit der ID " + item.getID() + " existiert bereits!\033[0m");
            }
        }
    }

    public static void registerLiquids(FuchsLiquid... fuchsLiquid) {
        for (FuchsLiquid liquid : fuchsLiquid) {
            if(!liquids.containsKey(liquid.getID())) {
                liquids.put(liquid.getID(), liquid);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Flüssigkeit mit der ID " + liquid.getID() + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Flüssigkeit mit der ID " + liquid.getID() + " existiert bereits!\033[0m");
            }
        }
    }

    public static void registerGasses(FuchsGas... fuchsGases) {
        for (FuchsGas gas : fuchsGases) {
            if(!gasses.containsKey(gas.getID())) {
                gasses.put(gas.getID(), gas);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Gas mit der ID " + gas.getID() + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Gas mit der ID " + gas.getID() + " existiert bereits!\033[0m");
            }
        }
    }

    public static void registerEffects(FuchsEffect... fuchsEffects) {
    	for (FuchsEffect effect : fuchsEffects) {
    		if(!effects.containsKey(effect.getID())) {
    		    effects.put(effect.getID(), effect);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Effekt mit der ID " + effect.getID() + " registriert!\033[0m");
    		} else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Effekt mit der ID " + effect.getID() + " existiert bereits!\033[0m");
    		}
    	}
    }

    public static void registerRarities(FuchsRarity... fuchsRarities) {
        for (FuchsRarity rarity : fuchsRarities) {
            if(!rarities.containsKey(rarity.getID())) {
                rarities.put(rarity.getID(), rarity);
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Rarität mit der ID " + rarity.getID() + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Rarität mit der ID " + rarity.getID() + " existiert bereits!\033[0m");
            }
        }
    }
    
    public static void registerEntities(FuchsEntity... fuchsEntities) {
    	for (FuchsEntity fuchsEntity : fuchsEntities) {
    		if(!entities.containsKey(fuchsEntity.getID())) {
    			entities.put(fuchsEntity.getID(), fuchsEntity);
    			System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.GREEN + "Entity mit der ID " + fuchsEntity.getID() + " registriert!\033[0m");
            } else {
                System.out.println("[PapierFuchs {Registry}] " + FuchsAnsi.RED + "Entity mit der ID " + fuchsEntity.getID() + " existiert bereits!\033[0m");
            }
    	}
    }
    
    public static void register(Class<?> listClass) {
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
                if(obj instanceof FuchsRarity) {
                    registerRarities((FuchsRarity) obj);
                }
                if(obj instanceof FuchsEffect) {
                    registerEffects((FuchsEffect) obj);
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
