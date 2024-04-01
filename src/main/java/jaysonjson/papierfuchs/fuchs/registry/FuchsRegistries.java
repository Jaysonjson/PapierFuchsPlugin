package jaysonjson.papierfuchs.fuchs.registry;

import jaysonjson.papierfuchs.fuchs.io.database.FuchsTable;
import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.jobs.FuchsJob;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.object.intern.sound.FuchsSound;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.FuchsGas;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.FuchsInventory;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.language.FuchsLanguage;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPC;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.skillclass.FuchsSkillclass;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.FuchsUseType;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.References;
import org.bukkit.ChatColor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class FuchsRegistries {

    public static FuchsItemGroup itemGroup = new FuchsItemGroup();

    public static Map<String, FuchsRegistryObject<FuchsItem>> items = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsBlock>> blocks = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsLiquid>> liquids = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsGas>> gasses = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsEffect>> effects = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsRarity>> rarities = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsEntity<?>>> entities = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsSkillclass<?>>> skill_classes = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsInventory>> inventories = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsCurrency>> currencies = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsNPC<?>>> npcs = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsLanguage>> languages = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsSound>> sounds = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsProjectile>> projectiles = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsUseType>> use_types = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsObject>> others = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsJob<?>>> jobs = new TreeMap<>();
    public static Map<String, FuchsRegistryObject<FuchsItemCategory>> itemCategories = new TreeMap<>();

    public static ArrayList<FuchsRegistry> registries = new ArrayList<>();

    private static ArrayList<String> missingServerObjects = new ArrayList<>();

    @Deprecated
    public static Map<String, FuchsRegistryObject<? extends IFuchsObject>> OBJECTS = new TreeMap<>();

    @Deprecated
    public static void sort() {
        items = new TreeMap<>(items);
    }

    @Deprecated
    public static void CREATE(IFuchsPlugin fuchsPlugin) {
        long millis = System.currentTimeMillis();
        for (FuchsRegistry registry : registries) {
            References.registeredItemsDataBase.createTable(registry);
        }
        References.registeredItemsDataBase.createDataBase();
        missingServerObjects = References.registeredItemsDataBase.dataBase.loopKeys(References.registeredItemsDataBase.all, References.registeredItemsDataBase.key);
        //missingServerObjects.addAll(References.data.getByteServer().getRegisteredObjects());
        OBJECTS.clear();
        effects.values().forEach(FuchsRegistries::addToObjects);
        entities.values().forEach(FuchsRegistries::addToObjects);
        gasses.values().forEach(FuchsRegistries::addToObjects);
        inventories.values().forEach(FuchsRegistries::addToObjects);
        items.values().forEach(FuchsRegistries::addToObjects);
        liquids.values().forEach(FuchsRegistries::addToObjects);
        npcs.values().forEach(FuchsRegistries::addToObjects);
        rarities.values().forEach(FuchsRegistries::addToObjects);
        skill_classes.values().forEach(FuchsRegistries::addToObjects);
        currencies.values().forEach(FuchsRegistries::addToObjects);
        others.values().forEach(FuchsRegistries::addToObjects);
        languages.values().forEach(FuchsRegistries::addToObjects);
        sounds.values().forEach(FuchsRegistries::addToObjects);
        projectiles.values().forEach(FuchsRegistries::addToObjects);
        use_types.values().forEach(FuchsRegistries::addToObjects);
        jobs.values().forEach(FuchsRegistries::addToObjects);
        blocks.values().forEach(FuchsRegistries::addToObjects);
        itemCategories.values().forEach(FuchsRegistries::addToObjects);
        for (String missingServerObject : missingServerObjects) {
            FuchsLog.warn("Fehlendes FuchsObject in Registry: " + ChatColor.AQUA + missingServerObject + ChatColor.RESET);
            for (FuchsTable table : References.registeredItemsDataBase.tables) {
                References.registeredItemsDataBase.dataBase.removeData(table,missingServerObject);
            }
        }
        if(missingServerObjects.size() > 0) {
            FuchsLog.warn("Fehlende FuchsObjects gelöscht...");
        }
        FuchsLog.log("Create-Object dauerte " + (System.currentTimeMillis() - millis) + "ms");
        //Utility.log(OBJECTS.toString());
        /*for (String s : OBJECTS.keySet()) {
            FuchsLog.log(LogType.REGISTRY, s);
        }*/
    }

    private static void addToObjects(FuchsRegistryObject<? extends IFuchsObject> value) {
        missingServerObjects.remove(value.copy().createObjectId());
        if (References.registeredItemsDataBase.exists(value.copy())) {
            FuchsLog.log("Neues FuchsObject in Registry: " + ChatColor.AQUA + value.copy().registryStringType() + ChatColor.RESET);
            References.registeredItemsDataBase.add(value.getFuchsPlugin().getRegistry(), value.copy());
        }
        //References.registeredItemsDataBase.add(value.getFuchsPlugin().getRegistry(), value.copy());
        OBJECTS.put(value.copy().registryStringType(), value);
    }

}
