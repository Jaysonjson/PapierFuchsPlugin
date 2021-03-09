package jaysonjson.papierfuchs.registry;

import jaysonjson.papierfuchs.LogType;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.currency.FuchsCurrency;
import jaysonjson.papierfuchs.object.effect.FuchsEffect;
import jaysonjson.papierfuchs.object.entity.FuchsEntity;
import jaysonjson.papierfuchs.object.gas.FuchsGas;
import jaysonjson.papierfuchs.object.inventory.FuchsInventory;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.object.npc.FuchsNPC;
import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import jaysonjson.papierfuchs.object.skillclass.FuchsSkillclass;

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
    public static Map<String, FuchsSkillclass> skill_classes = new HashMap<>();
    public static Map<String, FuchsInventory> inventories = new HashMap<>();
    public static Map<String, FuchsCurrency> currencies = new HashMap<>();
    public static Map<String, FuchsNPC> npcs = new HashMap<>();

    @Deprecated
    private static Map<String, IFuchsRegistryObject> OBJECTS = new HashMap<>();

    public static void sort() {
        items = new TreeMap<>(items);
    }

    @Deprecated
    private static void CREATE(IFuchsPlugin fuchsPlugin) {
        effects.values().forEach(effect -> addToObjects(effect, fuchsPlugin));
        entities.values().forEach(fuchsEntity -> addToObjects(fuchsEntity, fuchsPlugin));
        gasses.values().forEach(fuchsGas -> addToObjects(fuchsGas, fuchsPlugin));
        inventories.values().forEach(fuchsInventory -> addToObjects(fuchsInventory, fuchsPlugin));
        items.values().forEach(fuchsItem -> addToObjects(fuchsItem, fuchsPlugin));
        liquids.values().forEach(fuchsLiquid -> addToObjects(fuchsLiquid, fuchsPlugin));
        npcs.values().forEach(fuchsNPC -> addToObjects(fuchsNPC, fuchsPlugin));
        rarities.values().forEach(fuchsRarity -> addToObjects(fuchsRarity, fuchsPlugin));
        skill_classes.values().forEach(fuchsSkillclass -> addToObjects(fuchsSkillclass, fuchsPlugin));
        currencies.values().forEach(fuchsCurrency -> addToObjects(fuchsCurrency, fuchsPlugin));
        //Utility.log(OBJECTS.toString());
        for (String s : OBJECTS.keySet()) {
            Utility.log(LogType.REGISTRY, s);
        }
    }

    private static void addToObjects(IFuchsRegistryObject value, IFuchsPlugin fuchsPlugin) {
        OBJECTS.put(value.registryString(fuchsPlugin), value);
    }

}
