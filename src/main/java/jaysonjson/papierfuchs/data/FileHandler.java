package jaysonjson.papierfuchs.data;


import java.io.File;
import java.lang.reflect.Field;

public class FileHandler {
	
	@Directory
    public static String ROOT = "plugins/papierfuchs/";
    @Directory
	public static String PLAYER_DIR = ROOT + "/players/";
    @Directory
    public static String AREA_DIR = ROOT + "/areas/";
    @Directory
    public static String GUILD_DIR = ROOT + "/guilds/";
    @Directory
    public static String SERVER_DIR = ROOT;
    @Directory
    @Deprecated
    public static String BACKPACK_DIR = PLAYER_DIR + "/backpacks/";
    @Directory
    public static String MOBDROPS_DIR = ROOT + "/drops/mobs/";
    @Directory
    public static String BLOCK_DROPS_DIR = ROOT + "/drops/blocks/";
    @Directory
    public static String BREWERY_DIR = ROOT + "/crafting/brewery/";
    @Directory
    public static String CRAFTING_GENERAL_DIR = ROOT + "/crafting/general/";
    @Directory
    public static String LANG_DIR = ROOT + "/language/";
    
    public static void createDirectories() throws IllegalAccessException {
        for (Field field : FileHandler.class.getFields()) {
            if(field.isAnnotationPresent(Directory.class)) {
                new File((String) field.get(FileHandler.class)).mkdirs();
            }
        }
    }
    
}
