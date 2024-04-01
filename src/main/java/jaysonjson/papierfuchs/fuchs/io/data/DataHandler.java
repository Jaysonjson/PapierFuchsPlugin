package jaysonjson.papierfuchs.fuchs.io.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.player.data.FPKeys;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.ByteFuchsServer;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.ByteFuchsWorld;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsWorld;
import jaysonjson.papierfuchs.fuchs.object.intern.jobs.FuchsJob;
import jaysonjson.papierfuchs.fuchs.object.intern.skillclass.FuchsSkillclass;
import jaysonjson.papierfuchs.fuchs.utility.FuchsAnsi;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsArea;
import jaysonjson.papierfuchs.fuchs.io.data.backpack.data.zBackPack;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.data.zCrafting;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.CraftingItemNBT;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.brewery.zCraftingBrewery;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.general.zCraftingGeneral;
import jaysonjson.papierfuchs.fuchs.io.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.fuchs.io.data.discord.data.zDiscord;
import jaysonjson.papierfuchs.fuchs.io.data.drop.data.zDrops;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.FuchsBlockDrop;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.ItemDropChance;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.zMobDrop;
import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuild;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsServer;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.io.*;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class DataHandler {

    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.TRANSIENT)
            .setDateFormat("yyyy-MM-dd HH:mm:ss.S")
            .create();


    public static FuchsPlayer loadPlayer(UUID uuid) {
        File file = new File(FileHandler.PLAYER_DIR + uuid.toString() + "/data.json");
        FuchsPlayer player;
        if(!file.exists()) {
            player = new FuchsPlayer();
            player.setUUID(uuid);
            savePlayer(player);
        } else {
            player = gson.fromJson(readData(file), FuchsPlayer.class);
        }
        player.setUUID(uuid);
        FPKeys keys = player.getKeys();
        if(player.hasSkillClassID()) {
            if(FuchsUtility.skillClassExists(keys.getSkillClass())) {
                FuchsSkillclass<?> fuchsSkillclass = FuchsUtility.getSkillClassByID(keys.getSkillClass());
                fuchsSkillclass.loadData(player);
                player.setSkillClass(fuchsSkillclass);
            } else {
                keys.setSkillClass("");
            }
        }
        if(player.hasJobID()) {
            if(FuchsUtility.jobExists(keys.getJob())) {
                FuchsJob<?> fuchsJob = FuchsUtility.getJobByID(keys.getJob());
                fuchsJob.loadData(player);
                player.setJob(fuchsJob);
            } else {
                keys.setJob("");
            }
        }
        if(FuchsUtility.languageIDExists(player.getSettings().getLanguageID())) {
            player.setLanguage(FuchsUtility.getFuchsLanguageByID(player.getSettings().getLanguageID()));
        }
        return player;
    }

    public static void savePlayer(FuchsPlayer player) {
        String json = gson.toJson(player);
        try {
            new File(FileHandler.PLAYER_DIR + player.getUUID().toString() + "/").mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(player.getPath() + "data.json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(player.hasSkillClass()) {
            player.getSkillClass().saveData(player);
        }
    }

    public static zBackPack loadBackPack(UUID uuid) {
        File file = new File(FileHandler.ROOT + "/old/" + uuid.toString() + ".json");
        zBackPack backPack;
        if(!file.exists()) {
            backPack = new zBackPack();
            backPack.setUUID(uuid);
            saveBackPack(backPack);
        } else {
            backPack = gson.fromJson(readData(file), zBackPack.class);
        }
        backPack.setUUID(uuid);
        return backPack;
    }

    public static void saveBackPack(zBackPack backPack) {
        String json = gson.toJson(backPack);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FileHandler.ROOT + "/old/" + backPack.getUUID().toString() + ".json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static FuchsWorld loadWorld(UUID uuid) {
        File file = new File(FileHandler.WORLD_DIR + uuid.toString() + ".json");
        FuchsWorld fuchsWorld;
        if(!file.exists()) {
            fuchsWorld = new FuchsWorld();
            fuchsWorld.setWorldUuid(uuid);
            saveWorld(fuchsWorld);
        } else {
            fuchsWorld = gson.fromJson(readData(file), FuchsWorld.class);
        }
        fuchsWorld.setWorldUuid(uuid);
        return fuchsWorld;
    }

    public static void saveWorld(FuchsWorld fuchsWorld) {
        save(FileHandler.WORLD_DIR + fuchsWorld.getWorldUuid().toString() + ".json", gson.toJson(fuchsWorld));
    }

    public static void saveByteServer(ByteFuchsServer server) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FileHandler.UNEDITABLE + "server");
            DeflaterOutputStream outputStreamWriter = new DeflaterOutputStream(fileOutputStream);
            outputStreamWriter.write(SerializationUtils.serialize(server));
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }    }

    public static ByteFuchsServer loadByteServer() {
        File file = new File(FileHandler.UNEDITABLE + "server");
        ByteFuchsServer byteFuchsServer = new ByteFuchsServer();
        if(!file.exists()) {
            byteFuchsServer = new ByteFuchsServer();
            saveByteServer(byteFuchsServer);
        } else {
            try {
                InflaterInputStream fis = new InflaterInputStream(new ByteArrayInputStream(FileUtils.readFileToByteArray(file)));
                ObjectInputStream o = new ObjectInputStream(fis);
                byteFuchsServer = (ByteFuchsServer) o.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return byteFuchsServer;
    }


    public static void saveByteWorld(ByteFuchsWorld fuchsWorld) {
       /* try {
            FileUtils.writeByteArrayToFile(new File(FileHandler.UNEDITABLE_WORLD + fuchsWorld.getWorldUuid()), SerializationUtils.serialize(fuchsWorld));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fuchsWorld.path());
            DeflaterOutputStream outputStreamWriter = new DeflaterOutputStream(fileOutputStream);
            outputStreamWriter.write(SerializationUtils.serialize(fuchsWorld));
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ByteFuchsWorld loadByteWorld(UUID uuid) {
        File file = new File(FileHandler.UNEDITABLE_WORLD + uuid.toString());
        ByteFuchsWorld fuchsWorld = new ByteFuchsWorld();
        if(!file.exists()) {
            fuchsWorld = new ByteFuchsWorld();
            fuchsWorld.setWorldUuid(uuid);
            saveByteWorld(fuchsWorld);
        } else {
            try {
                InflaterInputStream fis = new InflaterInputStream(new ByteArrayInputStream(FileUtils.readFileToByteArray(file)));
                ObjectInputStream o = new ObjectInputStream(fis);
                fuchsWorld = (ByteFuchsWorld) o.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        fuchsWorld.setWorldUuid(uuid);
        return fuchsWorld;
    }

    public static void deleteBackPack(UUID uuid) {
        new File(FileHandler.ROOT + "/old/" + uuid.toString() + ".json").delete();
    }

    public static boolean backPackExists(UUID uuid) {
        return new File(FileHandler.ROOT + "/old/" + uuid.toString() + ".json").exists();
    }


    public static void saveServer(FuchsServer server) {
        save(FileHandler.SERVER_DIR + "server.json", gson.toJson(server));
    }

    public static FuchsServer loadServer() {
        File file = new File(FileHandler.SERVER_DIR + "server.json");
        FuchsServer server;
        if(!file.exists()) {
            server = new FuchsServer();
            saveServer(server);
        } else {
            server = gson.fromJson(readDataISO(file), FuchsServer.class);
        }
        return server;
    }

    public static void savePapierFuchs(PapierFuchsData papierFuchsData) {
        save(FileHandler.DATA_ROOT + "papierfuchs.json", gson.toJson(papierFuchsData));
    }

    public static PapierFuchsData loadPapierFuchs() {
        File file = new File(FileHandler.DATA_ROOT + "papierfuchs.json");
        PapierFuchsData papierFuchsData;
        if(!file.exists()) {
            papierFuchsData = new PapierFuchsData();
            savePapierFuchs(papierFuchsData);
        } else {
            papierFuchsData = gson.fromJson(readDataISO(file), PapierFuchsData.class);
        }
        return papierFuchsData;
    }

    public static void save(String path, String json) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void saveDiscord(zDiscord discord) {
        save(FileHandler.SERVER_DIR + "discord.json", gson.toJson(discord));

    }

    public static zDiscord loadDiscord() {
        File file = new File(FileHandler.SERVER_DIR + "discord.json");
        zDiscord discord;
        if(!file.exists()) {
            discord = new zDiscord();
            saveDiscord(discord);
        } else {
            discord = gson.fromJson(readData(file), zDiscord.class);
        }
        return discord;
    }



    public static void saveArea(FuchsArea area) {
        save(FileHandler.AREA_DIR + area.getUuid().toString() + ".json", gson.toJson(area));
    }

    public static FuchsArea loadArea(UUID uuid) {
        File file = new File(FileHandler.AREA_DIR + uuid.toString() + ".json");
        FuchsArea area;
        if(!file.exists()) {
            area = new FuchsArea();
            area.setUuid(uuid);
            saveArea(area);
        } else {
            area = gson.fromJson(readData(file), FuchsArea.class);
        }
        return area;
    }

    public static void saveGuild(FuchsGuild guild) {
        save(FileHandler.GUILD_DIR + guild.getUUID().toString() + ".json", gson.toJson(guild));
    }

    public static FuchsGuild loadGuild(UUID uuid) {
        return loadGuild(uuid.toString());
    }

    public static FuchsGuild loadGuild(String uuid) {
        File file = new File(FileHandler.GUILD_DIR + uuid + ".json");
        FuchsGuild guild;
        if(!file.exists()) {
            guild = new FuchsGuild();
            guild.setName("Unbekannt");
            saveGuild(guild);
        } else {
            guild = gson.fromJson(readData(file), FuchsGuild.class);
        }
        return guild;
    }

    public static zDrops loadDrops() {
        File mobDrops = new File(FileHandler.MOBDROPS_DIR);
        File block_drops = new File(FileHandler.BLOCK_DROPS_DIR);
        zDrops zDrops = new zDrops();
        for (File file : mobDrops.listFiles()) {
            if(file.getName().toLowerCase().contains("json")) {
                zMobDrop mobDrop = gson.fromJson(readData(file), zMobDrop.class);
                /*for (String s : mobDrop.itemDropsID.keySet()) {
                    if(Utility.itemIDExists(s)) {
                        mobDrop.itemDrops.put(Utility.getFuchsItemByID(s), mobDrop.itemDropsID.get(s));
                        System.out.println("[Fuchs {MobDrops}] " + mobDrop.itemDrops);
                    } else {
                        System.out.println("[Fuchs {MobDrops}] Item mit der ID " + s + " existiert nicht, überspringen...");
                    }
                }*/
                for (ItemDropChance item : mobDrop.items) {
                    if(FuchsUtility.itemIDExists(item.item)) {
                        item.setFuchsItem(FuchsUtility.getFuchsItemByID(item.item));
                        mobDrop.itemDropChances.add(item);
                        //System.out.println("[PapierFuchs {MobDrops}] " + FuchsAnsi.GREEN + "Item " + item.toString() + " hinzugefügt!" + FuchsAnsi.RESET);
                        FuchsLog.log(FuchsAnsi.GREEN + "Drop " + FuchsAnsi.CYAN + item.item + FuchsAnsi.GREEN + " hinzugefügt!");
                    } else {
                        FuchsLog.log(ChatColor.RED + "Item mit der ID " + FuchsAnsi.CYAN + item.item + ChatColor.RED + " existiert nicht, überspringen..." + FuchsAnsi.RESET);
                    }
                }
                zDrops.getMobDrops().add(mobDrop);
            }
        }
        for (File file : block_drops.listFiles()) {
            if (file.getName().toLowerCase().contains("json")) {
                FuchsBlockDrop fuchsBlockDrop = gson.fromJson(readData(file), FuchsBlockDrop.class);
                for (ItemDropChance item : fuchsBlockDrop.items) {
                    if(FuchsUtility.itemIDExists(item.item)) {
                        item.setFuchsItem(FuchsUtility.getFuchsItemByID(item.item));
                        //System.out.println("[PapierFuchs {BlockDrops}] " + FuchsAnsi.GREEN + " Item " + item.toString() + " hinzugefügt!" + FuchsAnsi.RESET);
                        FuchsLog.log(FuchsAnsi.GREEN + "BlockDrop " + FuchsAnsi.CYAN + item.item + FuchsAnsi.GREEN + " hinzugefügt!");
                    } else {
                        FuchsLog.log(ChatColor.RED + "Item mit der ID " + FuchsAnsi.CYAN + item.item + ChatColor.RED + " existiert nicht, überspringen..." + FuchsAnsi.RESET);
                    }
                }
                zDrops.getBlockDrops().add(fuchsBlockDrop);
            }
        }
        return zDrops;
    }

    public static zCrafting loadCrafting() {
        File brewery = new File("");
        File general = new File("");
        zCrafting zCrafting = new zCrafting();
        if(false) {
            for (File file : brewery.listFiles()) {
                if (file.getName().toLowerCase().contains("json")) {
                    zCraftingBrewery zCraftingBrewery = gson.fromJson(readData(file), zCraftingBrewery.class);
                    for (String s : zCraftingBrewery.inputsID) {
                        if (FuchsUtility.itemIDExists(s)) {
                            zCraftingBrewery.inputs.add(FuchsUtility.getFuchsItemByID(s));

                            //System.out.println("[Fuchs {Crafting}] " + s);
                            FuchsLog.log(FuchsAnsi.GREEN + "Rezept " + FuchsAnsi.CYAN + s + FuchsAnsi.GREEN + " hinzugefügt!");
                        } else {
                            FuchsLog.log(ChatColor.AQUA + file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("plugins")) + ChatColor.RED + " Item mit der ID " + ChatColor.RESET + FuchsAnsi.CYAN + s + ChatColor.RED + " existiert nicht, überspringen..." + FuchsAnsi.RESET);
                        }
                    }
                    zCrafting.breweries.add(zCraftingBrewery);
                }
            }
            for (File file : general.listFiles()) {
                if (file.getName().toLowerCase().contains("json")) {
                    zCraftingGeneral craftingGeneral = gson.fromJson(readData(file), zCraftingGeneral.class);
                    for (zCraftingItem input : craftingGeneral.inputs) {
                        createCraftingItemStack(input, file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("plugins")));
                    }
                    createCraftingItemStack(craftingGeneral.output, file.getAbsolutePath().substring(file.getAbsolutePath().indexOf("plugins")));
                    zCrafting.generals.add(craftingGeneral);
                }
            }
        }
        return zCrafting;
    }

    private static void createCraftingItemStack(zCraftingItem craftingItem, String recipe) {
        ItemStack itemStack = null;
        if(craftingItem.material != Material.AIR) {
            itemStack = new ItemStack(craftingItem.material);
        } else if(!craftingItem.fuchsItem.equals("")) {
            if(FuchsUtility.itemIDExists(craftingItem.getItemID())) {
                itemStack = FuchsUtility.getFuchsItemByID(craftingItem.getItemID()).createItem();
            } else {
                FuchsLog.error(ChatColor.AQUA + recipe + ChatColor.RED + " Item mit der ID " + ChatColor.AQUA + craftingItem.getItemID() + ChatColor.RED + " existiert nicht, überspringen...");
            }
        } else if(!craftingItem.itemData.equals("")) {
            itemStack = FuchsUtility.generateItemStack(craftingItem.itemData);
        }
        if(itemStack != null) {
            net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
            NBTTagCompound tag = FuchsUtility.getItemTag(nmsStack);
            if (!craftingItem.itemData.equals("")) {
                for (CraftingItemNBT craftingItemNBT : craftingItem.nbt) {
                    switch (craftingItemNBT.type) {
                        case STRING:
                            tag.setString(craftingItemNBT.key, craftingItemNBT.string_value);
                        case FLOAT:
                            tag.setFloat(craftingItemNBT.key, craftingItemNBT.float_value);
                        case BOOLEAN:
                            tag.setBoolean(craftingItemNBT.key, craftingItemNBT.bool_value);
                        case INTEGER:
                            tag.setInt(craftingItemNBT.key, craftingItemNBT.int_value);
                        case DOUBLE:
                            tag.setDouble(craftingItemNBT.key, craftingItemNBT.double_value);
                    }
                }
            }
            nmsStack.setTag(tag);
            itemStack = CraftItemStack.asBukkitCopy(nmsStack);
            itemStack.setAmount(craftingItem.amount);
            craftingItem.itemStack = itemStack;
        }
    }

    public static void createMobDrop() {
        zMobDrop mobDrop = new zMobDrop();
        mobDrop.type = EntityType.ZOMBIE;
        mobDrop.items.add(new ItemDropChance("scrap", 2, 1, 1));
        String json = gson.toJson(mobDrop);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FileHandler.MOBDROPS_DIR + "test.json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void createBlockDrop() {
        FuchsBlockDrop blockDrop = new FuchsBlockDrop();
        blockDrop.material = Material.STONE;
        //blockDrop.items.add(new ItemDropChance(ItemList.TIN_INGOT.copy().getID(), 2, 1, 1));
        String json = gson.toJson(blockDrop);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FileHandler.BLOCK_DROPS_DIR + "stone.json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*public static void loadLanguages() {
        File langs = new File(FileHandler.LANG_DIR);
        for (File file : langs.listFiles()) {
            if (file.getName().toLowerCase().contains("json")) {
                LanguageData language = gson.fromJson(readDataISO(file), LanguageData.class);
                Languages.valueOf(language.language).setLanguage(language);
                Languages.NOT_SET.setLanguage(language);
            }
        }
    }

    public static void createGermanLangTest() {
        LanguageData language = new LanguageData();
        language.language = Languages.GERMAN.name();
        language.CONTENT.put("zoryha_tear", ChatColor.AQUA.toString() + "Zoryha's Träne");
        language.CONTENT.put("test", "Test");
        String json = gson.toJson(language);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FileHandler.LANG_DIR + "german.json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/

    public static String readData(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    public static String readDataISO(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()), StandardCharsets.ISO_8859_1)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

    @Nullable
    public static Object getData(String file, Class<?> clazz) {
        if(new File(file).exists()) {
            return gson.fromJson(readDataISO(new File(file)), clazz);
        }
        if (PapierFuchs.class.getResource("/" + file) != null) {
            return gson.fromJson(FuchsUtility.getContentFromResource(PapierFuchs.class, "/" + file), clazz);
        }
        return null;
    }

}
