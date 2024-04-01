package jaysonjson.papierfuchs.fuchs.io.data;


import java.io.File;
import java.lang.reflect.Field;

public class FileHandler {

    public static String PROTOCOL = "prot_v03";

	@Directory public static String ROOT = "plugins/papierfuchs/" + PROTOCOL + "/";
    @Directory public static String CUSTOM_ROOT = ROOT + "custom/";
	@Directory public static String LOOT_RECIPES = CUSTOM_ROOT + "loot_recipes/";
	@Directory public static String DATA_ROOT = ROOT + "data/";
	@Directory public static String UNEDITABLE = DATA_ROOT + "uneditable/";
    @Directory public static String PLAYER_DIR = DATA_ROOT + "/players/";
    @Directory public static String AREA_DIR = DATA_ROOT + "/areas/";
    @Directory public static String GUILD_DIR = DATA_ROOT + "/guilds/";
    @Directory public static String SERVER_DIR = DATA_ROOT;
    @Directory public static String NPC_DIR = DATA_ROOT + "/npcs/";
    @Directory public static String WORLD_DIR = DATA_ROOT + "/worlds/";
    @Directory public static String UNEDITABLE_WORLD = UNEDITABLE + "worlds/";
    @Directory public static String CUSTOM_OBJECTS_DIR = CUSTOM_ROOT + "/objects/";
    @Directory public static String CUSTOM_OBJECTS_ITEMS_DIR = CUSTOM_OBJECTS_DIR + "items/";
    @Directory public static String CUSTOM_OBJECTS_CURRENCY_DIR = CUSTOM_OBJECTS_DIR + "currencies/";
    @Directory public static String RECIPE_SMITHING = LOOT_RECIPES + "/recipes/smithing/";
    @Directory public static String RECIPE_WORKBENCH = LOOT_RECIPES + "/recipes/workbench/";
    @Directory public static String MOBDROPS_DIR = LOOT_RECIPES + "/drops/mobs/";
    @Directory public static String BLOCK_DROPS_DIR = LOOT_RECIPES + "/drops/blocks/";
    @Directory public static String LANG_DIR = CUSTOM_ROOT + "/language/";
    
    public static void createDirectories()  {
        createDirectories(FileHandler.class);
    }

    public static void createDirectories(Class<?> clazz)  {
        try {
            for (Field field : clazz.getFields()) {
                if(field.isAnnotationPresent(Directory.class)) {
                    new File((String) field.get(clazz)).mkdirs();
                }
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
}
