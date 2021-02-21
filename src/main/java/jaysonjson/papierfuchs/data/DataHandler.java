package jaysonjson.papierfuchs.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.data.area.data.zArea;
import jaysonjson.papierfuchs.data.backpack.data.zBackPack;
import jaysonjson.papierfuchs.data.crafting.data.zCrafting;
import jaysonjson.papierfuchs.data.crafting.obj.CraftingItemNBT;
import jaysonjson.papierfuchs.data.crafting.obj.brewery.objs.zCraftingBreweryLiquidInput;
import jaysonjson.papierfuchs.data.crafting.obj.brewery.objs.zCraftingBreweryLiquidOutput;
import jaysonjson.papierfuchs.data.crafting.obj.brewery.zCraftingBrewery;
import jaysonjson.papierfuchs.data.crafting.obj.general.zCraftingGeneral;
import jaysonjson.papierfuchs.data.crafting.obj.zCraftingItem;
import jaysonjson.papierfuchs.data.discord.data.zDiscord;
import jaysonjson.papierfuchs.data.drop.data.zDrops;
import jaysonjson.papierfuchs.data.drop.obj.FuchsBlockDrop;
import jaysonjson.papierfuchs.data.drop.obj.ItemDropChance;
import jaysonjson.papierfuchs.data.drop.obj.zMobDrop;
import jaysonjson.papierfuchs.data.guild.data.zGuild;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.object.item.ItemList;
import jaysonjson.papierfuchs.object.liquid.LiquidList;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

public class DataHandler {

    private static final Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
    private static final Gson gson = new Gson();


    public static FuchsPlayer loadPlayer(UUID uuid) {
        File file = new File(FileHandler.PLAYER_DIR + uuid.toString() + ".json");
        FuchsPlayer player;
        if(!file.exists()) {
            player = new FuchsPlayer();
            player.setUUID(uuid);
            savePlayer(player);
        } else {
            player = gson.fromJson(readData(file), FuchsPlayer.class);
        }
        player.setUUID(uuid);
        return player;
    }

    public static void savePlayer(FuchsPlayer player) {
        String json = gsonBuilder.toJson(player);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileHandler.PLAYER_DIR + player.getUUID().toString() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zBackPack loadBackPack(UUID uuid) {
        File file = new File(FileHandler.BACKPACK_DIR + uuid.toString() + ".json");
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
        String json = gsonBuilder.toJson(backPack);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileHandler.BACKPACK_DIR + backPack.getUUID().toString() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteBackPack(UUID uuid) {
        new File(FileHandler.BACKPACK_DIR + uuid.toString() + ".json").delete();
    }

    public static boolean backPackExists(UUID uuid) {
        return new File(FileHandler.BACKPACK_DIR + uuid.toString() + ".json").exists();
    }


    public static void saveServer(FuchsServer server) {
        String json = gsonBuilder.toJson(server);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileHandler.SERVER_DIR + "server.json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static FuchsServer loadServer() {
        File file = new File(FileHandler.SERVER_DIR + "server.json");
        FuchsServer server;
        if(!file.exists()) {
            server = new FuchsServer();
            saveServer(server);
        } else {
            server = gson.fromJson(readData(file), FuchsServer.class);
        }
        return server;
    }

    public static void saveDiscord(zDiscord discord) {
        String json = gsonBuilder.toJson(discord);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileHandler.SERVER_DIR + "discord.json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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



    public static void saveArea(zArea area) {
        String json = gsonBuilder.toJson(area);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileHandler.AREA_DIR + area.getUuid().toString() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zArea loadArea(UUID uuid) {
        File file = new File(FileHandler.AREA_DIR + uuid.toString() + ".json");
        zArea area;
        if(!file.exists()) {
            area = new zArea();
            area.setUuid(uuid);
            saveArea(area);
        } else {
            area = gson.fromJson(readData(file), zArea.class);
        }
        return area;
    }

    public static void saveGuild(zGuild guild) {
        String json = gsonBuilder.toJson(guild);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileHandler.GUILD_DIR + guild.getUUID().toString() + ".json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static zGuild loadGuild(UUID uuid) {
        return loadGuild(uuid.toString());
    }

    public static zGuild loadGuild(String uuid) {
        File file = new File(FileHandler.GUILD_DIR + uuid.toString() + ".json");
        zGuild guild;
        if(!file.exists()) {
            guild = new zGuild();
            guild.setName("Unbekannt");
            saveGuild(guild);
        } else {
            guild = gson.fromJson(readData(file), zGuild.class);
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
                    if(Utility.itemIDExists(item.item)) {
                        item.setFuchsItem(Utility.getFuchsItemByID(item.item));
                        mobDrop.itemDropChances.add(item);
                        System.out.println("[PapierFuchs {MobDrops}] " + item.toString());
                    } else {
                        System.out.println("[PapierFuchs {MobDrops}] Item mit der ID " + item.item + " existiert nicht, überspringen...");
                    }
                }
                zDrops.getMobDrops().add(mobDrop);
            }
        }
        for (File file : block_drops.listFiles()) {
            if (file.getName().toLowerCase().contains("json")) {
                FuchsBlockDrop fuchsBlockDrop = gson.fromJson(readData(file), FuchsBlockDrop.class);
                for (ItemDropChance item : fuchsBlockDrop.items) {
                    if(Utility.itemIDExists(item.item)) {
                        item.setFuchsItem(Utility.getFuchsItemByID(item.item));
                        System.out.println("[PapierFuchs {BlockDrops}] " + item.toString());
                    } else {
                        System.out.println("[PapierFuchs {BlockDrops}] Item mit der ID " + item.item + " existiert nicht, überspringen...");
                    }
                }
                zDrops.getBlockDrops().add(fuchsBlockDrop);
            }
        }
        return zDrops;
    }

    public static zCrafting loadCrafting() {
        File brewery = new File(FileHandler.BREWERY_DIR);
        File general = new File(FileHandler.CRAFTING_GENERAL_DIR);
        zCrafting zCrafting = new zCrafting();
        for (File file : brewery.listFiles()) {
            if(file.getName().toLowerCase().contains("json")) {
                zCraftingBrewery zCraftingBrewery = gson.fromJson(readData(file),zCraftingBrewery.class);
                for (String s : zCraftingBrewery.inputsID) {
                    if(Utility.itemIDExists(s)) {
                        zCraftingBrewery.inputs.add(Utility.getFuchsItemByID(s));

                        System.out.println("[Fuchs {Crafting}] " + s);
                    } else {
                        System.out.println("[Fuchs {Crafting}] Item mit der ID " + s + " existiert nicht, überspringen...");
                    }
                }
                zCrafting.breweries.add(zCraftingBrewery);
            }
        }

        for (File file : general.listFiles()) {
            if(file.getName().toLowerCase().contains("json")) {
                zCraftingGeneral craftingGeneral = gson.fromJson(readData(file), zCraftingGeneral.class);
                for (zCraftingItem input : craftingGeneral.inputs) {
                    createCraftingItemStack(input);
                }
                createCraftingItemStack(craftingGeneral.output);
                zCrafting.generals.add(craftingGeneral);
            }
        }
        return zCrafting;
    }

    private static void createCraftingItemStack(zCraftingItem craftingItem) {
        ItemStack itemStack = null;
        if(craftingItem.material != Material.AIR) {
            itemStack = new ItemStack(craftingItem.material);
        } else if(!craftingItem.fuchsItem.equals("")) {
            if(Utility.itemIDExists(craftingItem.getItemID())) {
                itemStack = Utility.getFuchsItemByID(craftingItem.getItemID()).createItem();
            } else {
                System.out.println("[Fuchs {Crafting}] Item mit der ID " + craftingItem.getItemID() + " existiert nicht, überspringen...");
            }
        } else if(!craftingItem.itemData.equals("")) {
            itemStack = Utility.generateItemStack(craftingItem.itemData);
        }
        if(itemStack != null) {
            net.minecraft.server.v1_16_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
            NBTTagCompound tag = Utility.getItemTag(nmsStack);
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
        String json = gsonBuilder.toJson(mobDrop);
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
        blockDrop.items.add(new ItemDropChance(ItemList.TIN_INGOT.getID(), 2, 1, 1));
        String json = gsonBuilder.toJson(blockDrop);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FileHandler.BLOCK_DROPS_DIR + "stone.json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void createBreweryCrafting() {

        zCraftingBrewery craftingBrewery = new zCraftingBrewery();
        craftingBrewery.inputsID.add(ItemList.HOP.getID());
        craftingBrewery.inputsID.add(ItemList.MALT.getID());
        zCraftingBreweryLiquidInput liquidInput = new zCraftingBreweryLiquidInput();
        liquidInput.liquidAmount = 1.5;
        liquidInput.liquidInputID = LiquidList.WATER.getID();
        zCraftingBreweryLiquidOutput liquidOutput = new zCraftingBreweryLiquidOutput();
        liquidOutput.liquidAmount = 2;
        liquidOutput.liquidOutputID = LiquidList.BEER.getID();
        craftingBrewery.liquidInput = liquidInput;
        craftingBrewery.liquidOutput = liquidOutput;
        String json = gsonBuilder.toJson(craftingBrewery);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileHandler.BREWERY_DIR + "test.json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void createGeneralCraftingTest() {
        zCraftingGeneral general = new zCraftingGeneral();
        zCraftingItem input0 = new zCraftingItem();
        input0.fuchsItem = ItemList.HOP.getID();
        input0.nbt.add(new CraftingItemNBT());
        zCraftingItem input1 = new zCraftingItem();
        input1.material = Material.GOLDEN_APPLE;
        general.inputs.add(input0);
        general.inputs.add(input1);
        zCraftingItem ouput = new zCraftingItem();
        ouput.fuchsItem = ItemList.ALCOHOL_TEST.getID();
        ouput.nbt.add(new CraftingItemNBT());
        ouput.nbt.add(new CraftingItemNBT());
        general.output = ouput;
        String json = gsonBuilder.toJson(general);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileHandler.CRAFTING_GENERAL_DIR + "test.json"));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String readData(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }

}
