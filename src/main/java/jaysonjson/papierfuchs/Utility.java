package jaysonjson.papierfuchs;

import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FileHandler;
import jaysonjson.papierfuchs.data.FuchsLocation;
import jaysonjson.papierfuchs.data.area.data.zArea;
import jaysonjson.papierfuchs.data.area.obj.zLocation;
import jaysonjson.papierfuchs.data.backpack.data.zBackPack;
import jaysonjson.papierfuchs.data.guild.data.zGuild;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.data.server.data.BlockMetadataSetter;
import jaysonjson.papierfuchs.data.server.data.EntityMetadataSetter;
import jaysonjson.papierfuchs.data.server.data.FuchsServer;
import jaysonjson.papierfuchs.object.gas.FuchsGas;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemList;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.object.liquid.LiquidList;
import jaysonjson.papierfuchs.registry.FuchsRegistries;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Utility {

    public static Utility INSTANCE;

    public Utility() {
        INSTANCE = this;
    }

    public static void refreshHearts(Player player) {
        FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
        refreshHearts(player, fuchsPlayer);
    }

    public static void refreshHearts(Player player, FuchsPlayer fuchsPlayer) {
        if(fuchsPlayer.getHealth().health > 2048) {
            fuchsPlayer.getHealth().health = 2048;
        }
        if(fuchsPlayer.getHealth().health <= 0) {
            fuchsPlayer.getHealth().health = 2;
        }
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(fuchsPlayer.getHealth().health);
        player.setHealth(fuchsPlayer.getHealth().health);
    }

    public static void decreasePlayerHearts(Player player, FuchsPlayer fuchsPlayer, int amount) {
        fuchsPlayer.getHealth().health = fuchsPlayer.getHealth().health - amount;
        refreshHearts(player, fuchsPlayer);
    }

    public static void increasePlayerHearts(Player player, FuchsPlayer fuchsPlayer, int amount) {
        decreasePlayerHearts(player, fuchsPlayer, amount / -1);
    }

    public static boolean isInArea(Location location, Location locationP0, Location locationP1) {
        double maxX = (Math.max(locationP0.getX(), locationP1.getX()));
        double minX = (Math.min(locationP0.getX(), locationP1.getX()));

        double maxY = (Math.max(locationP0.getY(), locationP1.getY()));
        double minY = (Math.min(locationP0.getY(), locationP1.getY()));

        double maxZ = (Math.max(locationP0.getZ(), locationP1.getZ()));
        double minZ = (Math.min(locationP0.getZ(), locationP1.getZ()));

        if(location.getX() <= maxX && location.getX() >= minX) {
            if(location.getY() <= maxY && location.getY() >= minY) {
                return location.getZ() <= maxZ && location.getZ() >= minZ;
            }
        }
        return false;
    }

    public static boolean isInArea(zArea area, Player player) {
        Location p1 = area.createLocation(player.getWorld()).add(area.getSize(), area.getSize(), area.getSize());
        Location p2 = area.createLocation(player.getWorld()).subtract(area.getSize(), area.getSize(), area.getSize());
        return isInArea(player.getLocation(), p1, p2);
    }

    public static zArea getNearestArea(World.Environment environment, Location location) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, zArea> distances = new HashMap<>();
        if(PapierFuchs.INSTANCE.areas.size() < 1) {
            return new zArea();
        }
        for (zArea areas : PapierFuchs.INSTANCE.areas) {
            // distances.put((location.getX() - areas.location.x), areas);
            // distancesD.add((location.getX() - areas.location.x));
            if(areas.getWorld().getEnvironment().equals(environment)) {
                double distance = location.distance(areas.createLocation(location.getWorld()));
                distances.put(distance, areas);
                distancesD.add(distance);
            }
        }
        Collections.sort(distancesD);
        //Collections.reverse(distancesD);
        return distances.get(distancesD.get(0));
    }

    public static ArrayList<zArea> getNearestAreas(int radius, World.Environment environment, Location location) {
        ArrayList<zArea> areaList = new ArrayList<>();
        for (zArea areas : PapierFuchs.INSTANCE.areas) {
            if(areas.getWorld().getEnvironment().equals(environment)) {
                double distance = location.distance(areas.createLocation(location.getWorld()));
                if(distance <= radius) {
                    areaList.add(areas);
                }
            }
        }
        return areaList;
    }

    public static zArea getNearestAreaOutsidePlayer(Player player) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, zArea> distances = new HashMap<>();
        if(PapierFuchs.INSTANCE.areas.size() < 1) {
            return new zArea();
        }
        for (zArea areas : PapierFuchs.INSTANCE.areas) {
            if(!isInArea(areas, player)) {
                if (areas.getWorld().getEnvironment().equals(player.getWorld().getEnvironment())) {
                    double distance = player.getLocation().distance(areas.createLocation(player.getWorld()));
                    distances.put(distance, areas);
                    distancesD.add(distance);
                }
            }
        }
        Collections.sort(distancesD);
        if(distancesD.size() > 0) {
            return distances.get(distancesD.get(0));
        } else {
            return new zArea();
        }
    }

    public static zArea getNearestAreaPlayerExceptCurrent(Player player, zArea area) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, zArea> distances = new HashMap<>();
        if(PapierFuchs.INSTANCE.areas.size() < 1) {
            return new zArea();
        }
        for (zArea areas : PapierFuchs.INSTANCE.areas) {
            if(area != areas) {
                if (areas.getWorld().getEnvironment().equals(player.getWorld().getEnvironment())) {
                    double distance = player.getLocation().distance(areas.createLocation(player.getWorld()));
                    distances.put(distance, areas);
                    distancesD.add(distance);
                }
            }
        }
        Collections.sort(distancesD);
        if(distancesD.size() > 0) {
            return distances.get(distancesD.get(0));
        } else {
            return new zArea();
        }
    }

    public static zArea getNearestArea(Player player) {
        return getNearestArea(player.getWorld().getEnvironment(), player.getLocation());
    }


    public static zLocation getNearestAreaDistance(World.Environment environment, Location location) {
        zArea area = getNearestArea(environment, location);
        //return new zLocation(area.location.x - location.getX() - area.size, 0, area.location.z - location.getZ() - area.size);
        return new zLocation(area.getLocation().x - location.getX(), 0, area.getLocation().z - location.getZ());
    }

    public static zLocation getNearestAreaDistanceOutsidePlayer(Player player) {
        zArea area = getNearestAreaOutsidePlayer(player);
        return new zLocation(area.getLocation().x - player.getLocation().getX(), 0, area.getLocation().z - player.getLocation().getZ());
    }
    public static zLocation getNearestAreaDistanceOutsidePlayerExceptCurrent(Player player, zArea current) {
        zArea area = getNearestAreaPlayerExceptCurrent(player, current);
        return new zLocation(area.getLocation().x - player.getLocation().getX(), 0, area.getLocation().z - player.getLocation().getZ());
    }

    public static boolean areaOverlap(Location locationP1, Location locationP2, Location locationBP1, Location locationBP2)
    {
        boolean XCol = isAreaBetween(locationP1.getX(), locationP2.getX(), locationBP1.getX()) || isAreaBetween(locationP1.getX(), locationP2.getX(), locationBP2.getX());
        boolean YCol = isAreaBetween(locationP1.getY(), locationP2.getY(), locationBP1.getY()) || isAreaBetween(locationP1.getY(), locationP2.getY(), locationBP2.getY());
        boolean ZCol = isAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP1.getZ()) || isAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP2.getZ());

        return XCol && YCol && ZCol;
    }

    public static boolean areaOverlap(World world, zArea area1, zArea area2) {
        Location p1 = area1.createLocation(world);
        p1.add(area1.getSize(), area1.getSize(), area1.getSize());
        Location p2 = area1.createLocation(world);
        p2.subtract(area1.getSize(), area1.getSize(), area1.getSize());
        Location pb1 = area2.createLocation(world);
        pb1.add(area2.getSize(), area2.getSize(), area2.getSize());
        Location pb2 = area2.createLocation(world);
        pb2.subtract(area2.getSize(), area2.getSize(), area2.getSize());
        return areaOverlap(p1, p2, pb1, pb2);
    }

    public static boolean isAreaBetween(double point1, double point2, double target) {
        if(point1 < point2) {
            return target >= point1 && target <= point2;
        } else {
            return target >= point2 && target <= point1;
        }
    }
    //Einfach boolean isSpawn --> Sinnlos
    @Deprecated
    public static boolean isInSpawnArea(Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        if(area.getDisplayName().toLowerCase().equals("spawn")) {
            Location locationP0 = area.createLocation(world).add(area.getSize(), area.getSize(), area.getSize());
            Location locationP1 = area.createLocation(world).subtract(area.getSize(), area.getSize(), area.getSize());
            return Utility.isInArea(location, locationP0, locationP1);
        }
        return false;
    }

    public static boolean canBreakBlock(Player player, Location location, World world) {
        zArea area = getNearestArea(player.getWorld().getEnvironment(), location);
        Location locationP0 = area.createLocation(world).add(area.getSize(), area.getSize(), area.getSize());
        Location locationP1 = area.createLocation(world).subtract(area.getSize(), area.getSize(), area.getSize());
        if(Utility.isInArea(location, locationP0, locationP1)) {
            return area.isBreakBlocks() || player.isOp();
        }
        return true;
    }

    public static boolean canPlaceBlock(Player player, Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        Location locationP0 = area.createLocation(world).add(area.getSize(), area.getSize(), area.getSize());
        Location locationP1 = area.createLocation(world).subtract(area.getSize(), area.getSize(), area.getSize());
        if(Utility.isInArea(location, locationP0, locationP1)) {
            return area.isPlaceBlocks() || player.isOp();
        }
        return true;
    }

    public static boolean canEntitySpawn(Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        Location locationP0 = area.createLocation(world).add(area.getSize(), area.getSize(), area.getSize());
        Location locationP1 = area.createLocation(world).subtract(area.getSize(), area.getSize(), area.getSize());
        if(Utility.isInArea(location, locationP0, locationP1)) {
            return area.isSpawnMobs();
        }
        return true;
    }

    public static boolean canDropItem(Player player, Location location, World world) {
        zArea area = getNearestArea(world.getEnvironment(), location);
        Location locationP0 = area.createLocation(world).add(area.getSize(), area.getSize(), area.getSize());
        Location locationP1 = area.createLocation(world).subtract(area.getSize(), area.getSize(), area.getSize());
        if(Utility.isInArea(location, locationP0, locationP1)) {
            return area.isDropItems() || player.isOp();
        }
        return true;
    }

    public static void spawnCustomItem(Player player, FuchsItem item, World world, Location location) {
        world.dropItemNaturally(location, item.createItem(player));
    }

    @Deprecated
    private static double countMoney(Player player) {
        return countMoney(player.getInventory());
    }

    @Deprecated
    private static double countMoney(Inventory inventory) {
        return countMoney(inventory.getContents());
    }

    @Deprecated
    private static double countMoney(ItemStack[] contents) {
        double amount = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(ItemNBT.HACKSILVER_AMOUNT)) {
                        amount += (tag.getDouble(ItemNBT.HACKSILVER_AMOUNT) * content.getAmount());
                    }
                }
            }
        }
        return amount;
    }

    @Deprecated
    private static double countMoneyBackpack(Inventory inventory) {
        double amount = 0;
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(ItemNBT.HACKSILVER_AMOUNT)) {
                        amount += (tag.getDouble(ItemNBT.HACKSILVER_AMOUNT) * content.getAmount());
                    }
                    if(tag.hasKey(ItemNBT.IS_BACKPACK)) {
                        //ItemStack[] contents = generateInventoryContent(DataHandler.loadBackPack(UUID.fromString(tag.getString(ItemNBT.ITEM_UUID))).inventoryContent);
                        ItemStack[] contents = generateInventoryContent(tag.getString(ItemNBT.INVENTORY_CONTENT));
                        amount += countMoney(contents);
                    }
                }
            }
        }
        return amount;
    }

    @Deprecated
    private static double countZoryhaShard(Inventory inventory) {
        return countZoryhaShard(inventory.getContents());
    }

    @Deprecated
    private static double countZoryhaShard(ItemStack[] contents) {
        double amount = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(ItemNBT.ZORYHASHARD_AMOUNT)) {
                        amount += (tag.getDouble(ItemNBT.ZORYHASHARD_AMOUNT) * content.getAmount());
                    }
                }
            }
        }
        return amount;
    }

    @Deprecated
    private static double countZoryhaShardBackpack(Inventory inventory) {
        double amount = 0;
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(ItemNBT.ZORYHASHARD_AMOUNT)) {
                        amount += (tag.getDouble(ItemNBT.ZORYHASHARD_AMOUNT) * content.getAmount());
                    }
                    if(tag.hasKey(ItemNBT.IS_BACKPACK)) {
                        //ItemStack[] contents = generateInventoryContent(DataHandler.loadBackPack(UUID.fromString(tag.getString(ItemNBT.ITEM_UUID))).inventoryContent);
                        ItemStack[] contents = generateInventoryContent(tag.getString(ItemNBT.INVENTORY_CONTENT));
                        amount += countZoryhaShard(contents);
                    }
                }
            }
        }
        return amount;
    }


    public static double countCurrency(Inventory inventory, String type, boolean backPack) {
        return countCurrency(inventory.getContents(), type, backPack);
    }

    public static double countCurrency(Player player, String type, boolean backPack) {
        return countCurrency(player.getInventory(), type, backPack);
    }

    public static double countCurrency(ItemStack[] contents, String type, boolean backPack) {
        double amount = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if(content.hasItemMeta()) {
                    NBTTagCompound tag = getItemTag(content);
                    if(tag.hasKey(ItemNBT.CURRENCY_TYPE) && tag.hasKey(ItemNBT.CURRENCY_AMOUNT)) {
                        if(tag.getString(ItemNBT.CURRENCY_TYPE).equalsIgnoreCase(type)) {
                            amount += countItemCurrency(content);
                        }
                    }

                    if(backPack) {
                        ItemStack[] backpackContents = generateInventoryContent(tag.getString(ItemNBT.INVENTORY_CONTENT));
                        amount += countCurrency(backpackContents, type, true);
                    }
                }
            }
        }
        return amount;
    }

    @Deprecated
    private static double countZoryhaShard(Player player) {
        return countZoryhaShard(player.getInventory());
    }

    @Deprecated
    private static double convertHacksilverToZoryhaShard(double hacksilverAmount) {
        return hacksilverAmount / Constant.HACKSILVER_TO_ZORYHASHARD_VALUE;
    }

    @Deprecated
    private static double convertZoryhaShardToHacksilver(double zoryhaShardAmount) {
        return zoryhaShardAmount * Constant.HACKSILVER_TO_ZORYHASHARD_VALUE;
    }

    public static void reloadAreas() {
        PapierFuchs.INSTANCE.areas.clear();
        HashMap<String, zArea> areaHash = new HashMap<>();
        ArrayList<String> sortedHash = new ArrayList<>();
        for (File file : new File(FileHandler.AREA_DIR).listFiles()) {
            ///^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/
            try {
                zArea area = DataHandler.loadArea(UUID.fromString(file.getName().replaceAll(".json", "")));
                areaHash.put(area.getPriority() + "_" + area.getDisplayName(), area);
                sortedHash.add(area.getPriority() + "_" + area.getDisplayName());
                DataHandler.saveArea(area);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(sortedHash);
        Collections.reverse(sortedHash);
        for (String hash : sortedHash) {
            PapierFuchs.INSTANCE.areas.add(areaHash.get(hash));
        }
    }

    public static boolean guildExists(UUID uuid) {
        return new File(FileHandler.GUILD_DIR + uuid.toString() + ".json").exists();
    }

    public static boolean guildExists(String name) {
        for (File file : new File(FileHandler.GUILD_DIR).listFiles()) {
            zGuild guild = DataHandler.loadGuild(file.getName().replaceAll(".json", ""));
            if(guild.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areaExistsUUID(UUID uuid) {
        return new File(FileHandler.AREA_DIR + uuid.toString() + ".json").exists();
    }

    public static boolean areaExists(String name) {
        for (zArea area : PapierFuchs.INSTANCE.areas) {
            if(area.getDisplayName().toLowerCase().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static FuchsItem getFuchsItemByID(String id) {
        if(itemIDExists(id)) {
            return FuchsRegistries.items.get(id);
        }
        return null;
    }

    public static boolean itemIDExists(String id) {
        return FuchsRegistries.items.containsKey(id);
    }

    public static void deleteArea(String name) {
        if(areaExists(name)) {
            new File(FileHandler.AREA_DIR + name.toLowerCase() + ".json").delete();
        }
    }

    public static String formatInteger(Integer integer) {
        String string = String.valueOf(integer);
        if(integer > 1000) string = String.format("%.2fk", integer / 1000.0);
        if(integer > 1000000) string = String.format("%.2fM", integer / 1000000.0);
        if(integer > 1000000000) string = String.format("%.2fG", integer / 1000000000.0);
        return string;
    }

    public static String formatTime(Integer integer) {
        String string = integer + "s";
        if(integer > 60) string = String.format("%.1fm", (integer / 60.0) % 60);
        return string;
    }

    public static void updateSkillBook(BookMeta bookMeta) {

    }

    public static NBTTagCompound getItemTag(net.minecraft.server.v1_16_R3.ItemStack itemStack) {
        return itemStack.hasTag() ? itemStack.getTag() : new NBTTagCompound();
    }

    public static NBTTagCompound getItemTag(ItemStack itemStack) {
        return getItemTag(createNMSCopy(itemStack));
    }

    public static net.minecraft.server.v1_16_R3.ItemStack createNMSCopy(ItemStack itemStack) {
        return CraftItemStack.asNMSCopy(itemStack);
    }


    public static boolean isAbilityItemAll(Player player, ItemStack itemStack) {
        for (FuchsItem fuchsItem : FuchsRegistries.items.values()) {
            if(fuchsItem.getMaterial().equals(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    public static void makeDrunk(Player player, FuchsPlayer fuchsPlayer) {
        if(fuchsPlayer.getPlayerSpecial().getAlcohol() > 0.5) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (fuchsPlayer.getPlayerSpecial().getAlcohol() * 740), 0));
        }
        if(fuchsPlayer.getPlayerSpecial().getAlcohol() > 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (fuchsPlayer.getPlayerSpecial().getAlcohol() * 100),0));
        }
        if(fuchsPlayer.getPlayerSpecial().getAlcohol() > 2) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (fuchsPlayer.getPlayerSpecial().getAlcohol() * 60),0));
        }
        if(fuchsPlayer.getPlayerSpecial().getAlcohol() > 3) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (fuchsPlayer.getPlayerSpecial().getAlcohol() * 700),2));
        }

        if(fuchsPlayer.getPlayerSpecial().getAlcohol() >= fuchsPlayer.getPlayerSpecial().getMaxAlcohol()) {
            player.setHealth(0);
        }
    }


    public static boolean isFuchsItem(ItemStack itemStack) {
        for (FuchsItem fuchsItem : FuchsRegistries.items.values()) {
            if(fuchsItem.getMaterial().equals(itemStack.getType())) {
                if(Utility.getItemTag(Utility.createNMSCopy(itemStack)).hasKey(ItemNBT.ITEM_ID)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAbstractVanillaItem(ItemStack itemStack) {
        for (FuchsItem fuchsItem : FuchsRegistries.items.values()) {
            if(fuchsItem.isVanillaOverride()) {
                if(fuchsItem.defaultVanillaOverride().equals(itemStack.getType())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public static FuchsItem getAbstractVanillaOverride(ItemStack itemStack) {
        for (FuchsItem fuchsItem : FuchsRegistries.items.values()) {
            if(fuchsItem.isVanillaOverride()) {
                if(fuchsItem.defaultVanillaOverride().equals(itemStack.getType())) {
                    return fuchsItem;
                }
            }
        }
        return null;
    }

    @Nullable
    public static FuchsItem getFuchsItemFromNMS(ItemStack itemStack) {
        net.minecraft.server.v1_16_R3.ItemStack nmsCopy = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = nmsCopy.hasTag() ? nmsCopy.getTag() : new NBTTagCompound();
        if(tag.hasKey(ItemNBT.ITEM_ID)) {
            return getFuchsItemByID(tag.getString(ItemNBT.ITEM_ID));
        }
        return null;
    }

    public static String createInventoryContent(ItemStack[] itemStacks) {
        if(itemStacks.length > 0) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
                dataOutput.writeInt(itemStacks.length);
                for (ItemStack itemStack : itemStacks) {
                    dataOutput.writeObject(itemStack);
                }
                dataOutput.close();
                return Base64Coder.encodeLines(outputStream.toByteArray());
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
        return "";
    }
    
    public static String createItemStackString(ItemStack itemStack) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(itemStack);
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return "";
    }

    public static ItemStack generateItemStack(String itemData) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(itemData));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack itemStack = (ItemStack) dataInput.readObject();
            dataInput.close();
            return itemStack;
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return new ItemStack(Material.AIR);
    }

    public static ItemStack[] generateInventoryContent(String inventoryContent) {
        if (inventoryContent.length() > 0) {
            try {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(inventoryContent));
                BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
                ItemStack[] items = new ItemStack[dataInput.readInt()];
                for (int i = 0; i < items.length; i++) {
                    items[i] = (ItemStack) dataInput.readObject();
                }
                dataInput.close();
                return items;
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            return new ItemStack[0];
        }
        return new ItemStack[0];
    }

    public static ItemStack createInventoryStack(Material material, int amount, String displayName) {
        return createInventoryStack(material, amount, displayName, new ArrayList<String>());
    }

    public static ItemStack createInventoryStack(Material material, int amount, String displayName, ArrayList<String> lore) {
        return createInventoryStack(new ItemStack(material), amount, displayName, lore);
    }

    public static ItemStack createInventoryStack(ItemStack itemstack, int amount, String displayName) {
        return createInventoryStack(itemstack, amount, displayName, new ArrayList<String>());
    }

    public static ItemStack createInventoryStack(ItemStack itemstack, int amount, String displayName, ArrayList<String> lore) {
        itemstack.setAmount(amount);
        ItemMeta itemstackMeta = itemstack.getItemMeta();
        itemstackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        itemstackMeta.setDisplayName(displayName);
        itemstackMeta.setLore(lore);
        itemstack.setItemMeta(itemstackMeta);
        return itemstack;
    }

    public static ItemStack createInventoryWoolColor(boolean bool, String displayName, int amount) {
        return bool ? createInventoryStack(Material.GREEN_WOOL, amount, displayName) : createInventoryStack(Material.RED_WOOL, amount, displayName);
    }

    public static boolean isTopInventory(InventoryClickEvent event) {
        return event.getRawSlot() < event.getView().getTopInventory().getSize();
    }

    public static void createInventoryBorder(Inventory inventory, int rowSize, ItemStack itemStack) {
        for (int i = 0; i < rowSize; i++) {
            int j = i * 9;
            for (int slot = j; slot < j + 9; slot++) {
                if (i == 0 || i == 5 || j == slot || slot == j + 8) {
                    inventory.setItem(slot, itemStack);
                }
            }
        }
    }

    public static void createInventoryBorder(Inventory inventory, int rowSize, Material material) {
        createInventoryBorder(inventory, rowSize, new ItemStack(material));
    }

    public static void createInventoryBorder(Inventory inventory, Material material) {
        createInventoryBorder(inventory, (inventory.getSize() + 1) / 9, material);
    }

    public static void createInventoryBorder(Inventory inventory, ItemStack itemStack) {
        createInventoryBorder(inventory, (inventory.getSize() + 1) / 9, itemStack);
    }

    public static int addAmount(ItemStack original, ItemStack addition) {
        int amount = 0;
        NBTTagCompound tag1 = getItemTag(original);
        if(tag1.hasKey(ItemNBT.ITEM_AMOUNT)) {
            amount = tag1.getInt(ItemNBT.ITEM_AMOUNT);
            if(addition != null) {
                NBTTagCompound tag2 = getItemTag(addition);
                if (tag2.hasKey(ItemNBT.ITEM_AMOUNT)) {
                    amount += tag2.getInt(ItemNBT.ITEM_AMOUNT);
                }
            }
        }
        return amount;
    }

    public static double countItemCurrency(ItemStack itemStack) {
        NBTTagCompound tag = getItemTag(itemStack);
        if(tag.hasKey(ItemNBT.CURRENCY_AMOUNT)) {
            return tag.getDouble(ItemNBT.CURRENCY_AMOUNT) * itemStack.getAmount();
        }
        return 0;
    }


    private static ItemStack[] removeCurrency(ItemStack[] contents, String currency_type, double amount) {
        double fake_amount = amount;
        for (ItemStack content : contents) {
            if(content != null) {
                if(content.hasItemMeta()) {
                    NBTTagCompound tag = getItemTag(content);
                    if(tag.hasKey(ItemNBT.CURRENCY_TYPE) && tag.hasKey(ItemNBT.CURRENCY_AMOUNT)) {
                        if(countItemCurrency(content) >= amount) {

                        }
                    }
                }
            }
        }
        return contents;
    }

    @Deprecated
    private static ItemStack[] removeMoney(ItemStack[] contents, double amount) {
        double fakeAmount = amount;
        int i = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(ItemNBT.HACKSILVER_AMOUNT)) {
                        fakeAmount -= tag.getDouble(ItemNBT.HACKSILVER_AMOUNT);
                        double decrease = fakeAmount - tag.getDouble(ItemNBT.HACKSILVER_AMOUNT);
                        if(decrease > 0) {
                            content.setAmount(content.getAmount() - 1);
                        }

                        if(decrease < 0) {
                            if(tag.getDouble(ItemNBT.HACKSILVER_AMOUNT) > decrease) {
                                FuchsMCItem fuchsItem = new FuchsMCItem(getFuchsItemFromNMS(content), content);
                                fuchsItem.changeDoubleTag(ItemNBT.HACKSILVER_AMOUNT, tag.getDouble(ItemNBT.HACKSILVER_AMOUNT) + decrease);
                                contents[i] = fuchsItem.getItemStack();
                            } else {
                                content.setAmount(content.getAmount() - 1);
                            }
                        }
                    }
                }
            }
        }
        return contents;
    }
    @Deprecated
    private static ItemStack[] removeMoneyBackpack(Inventory inventory, double amount) {
        ItemStack[] contents = inventory.getContents();
        double fakeAmount = amount;
        int i = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if (content.hasItemMeta()) {
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = createNMSCopy(content);
                    NBTTagCompound tag = getItemTag(nmsItem);
                    if (tag.hasKey(ItemNBT.HACKSILVER_AMOUNT)) {
                        fakeAmount -= tag.getDouble(ItemNBT.HACKSILVER_AMOUNT);
                        double decrease = fakeAmount - tag.getDouble(ItemNBT.HACKSILVER_AMOUNT);
                        if(decrease > 0) {
                            content.setAmount(content.getAmount() - 1);
                            contents[i] = content;
                        }

                        if(decrease < 0) {
                            if(tag.getDouble(ItemNBT.HACKSILVER_AMOUNT) > decrease) {
                                FuchsMCItem fuchsItem = new FuchsMCItem(getFuchsItemFromNMS(content), content);
                                fuchsItem.changeDoubleTag(ItemNBT.HACKSILVER_AMOUNT, tag.getDouble(ItemNBT.HACKSILVER_AMOUNT) + decrease);
                                contents[i] = fuchsItem.getItemStack();
                            } else {
                                content.setAmount(content.getAmount() - 1);
                                contents[i] = content;
                            }
                        }
                    }
                    if(tag.hasKey(ItemNBT.IS_BACKPACK)) {
                        ItemStack[] contentsF = generateInventoryContent(DataHandler.loadBackPack(UUID.fromString(tag.getString(ItemNBT.ITEM_UUID))).inventoryContent);
                        ItemStack[] BGcontent = removeMoney(contentsF, amount);
                        zBackPack backPack = DataHandler.loadBackPack(UUID.fromString(tag.getString(ItemNBT.ITEM_UUID)));
                        backPack.setItemStacks(BGcontent);
                        DataHandler.saveBackPack(backPack);
                    }
                }
            }
            i++;
        }
        return contents;
    }

    @Nullable
    public static FuchsLiquid getLiquidByID(String id) {
        if(liquidExists(id)) {
            return FuchsRegistries.liquids.get(id);
        }
        return LiquidList.NONE;
    }

    public static boolean liquidExists(String id) {
        return FuchsRegistries.liquids.containsKey(id);
    }

    @Nullable
    public static FuchsGas getGasByID(String id) {
        if(gasExists(id)) {
            return FuchsRegistries.gasses.get(id);
        }
        return null;
    }

    public static boolean gasExists(String id) {
        return FuchsRegistries.gasses.containsKey(id);
    }

    public static byte[] toByteArray(List<String> list) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        for (String element : list) {
            out.writeUTF(element);
        }
        out.close();
        return baos.toByteArray();
    }


    public static void updateInventory(Player player) {
        boolean updated = false;
        Inventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);
            if(itemStack != null) {
                if (!itemStack.isSimilar(new ItemStack(Material.AIR))) {
                    if(isAbstractVanillaItem(itemStack)) {
                        inventory.setItem(i, getAbstractVanillaOverride(itemStack).createItem(player, itemStack));
                        updated = true;
                    } else if(isFuchsItem(itemStack)) {
                        inventory.setItem(i, getFuchsItemFromNMS(itemStack).createItem(player, itemStack));
                        updated = true;
                    }
                }
            }
        }
        if(updated) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("Dein Inventar wurde aktualisiert!").color(ChatColor.DARK_PURPLE).create());
        }
    }

    public static boolean arrayContainsFuchsLocation(ArrayList<FuchsLocation> array, FuchsLocation location) {
        for (FuchsLocation arrayLoc : array) {
            if(arrayLoc.compare(location)) {
                return true;
            }
        }
        return false;
    }

    public static void arrayRemoveFuchsLocation(ArrayList<FuchsLocation> array, FuchsLocation location) {
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).compare(location)) {
                array.remove(i);
            }
        }
    }

    public static void openOpenedChests(World world) {
        FuchsServer fuchsServer = DataHandler.loadServer();
        for (FuchsLocation fuchsLocation : fuchsServer.OPEN_CHESTS) {
            if(world.getBlockAt(fuchsLocation.createLocation(world)).getState() instanceof Chest) {
                Chest chest = (Chest) world.getBlockAt(fuchsLocation.createLocation(world)).getState();
                chest.open();
                System.out.println("Kiste bei " + fuchsLocation + " geöffnet!");
            } else {
                //Utility.arrayRemoveFuchsLocation(zServer.OPEN_CHESTS, fuchsLocation);
            }
        }
    }

    public static void setEntityMetadatas(World world) {
        FuchsServer fuchsServer = DataHandler.loadServer();
        for (EntityMetadataSetter metadata : fuchsServer.ENTITY_METADATA) {
            Entity entity = world.getEntity(metadata.uuid);
            if(entity != null) {
                entity.setMetadata(metadata.key, new FixedMetadataValue(PapierFuchs.INSTANCE, metadata.value));
                System.out.println("Metadata für Entity " + entity.getName() + " gesetzt!");
            }
        }
    }

    public static void setBlockMetadatas(World world) {
        FuchsServer fuchsServer = DataHandler.loadServer();
        for (BlockMetadataSetter metadata : fuchsServer.BLOCK_METADATA) {
            Block block = world.getBlockAt(metadata.location.createLocation(world));
            if(block.getType() == metadata.type) {
                block.setMetadata(metadata.key, new FixedMetadataValue(PapierFuchs.INSTANCE, metadata.value));
                System.out.println("Metadata für Block " + block.getTranslationKey() + " " + metadata.location + "gesetzt!");
            }
        }
    }

    public static int countBounty(FuchsPlayer fuchsPlayer) {
        int bounty = 0;
        for (Integer value : fuchsPlayer.getPlayerSpecial().getBounties().values()) {
            bounty += value;
        }
        return bounty;
    }

    public static boolean inventoryHasItem(Inventory inventory, ItemStack itemStack) {
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                List<String> lore = new ArrayList<>();
                ItemStack n_c = new ItemStack(content);
                ItemStack n_s = new ItemStack(itemStack);
                n_c.setLore(lore);
                n_s.setLore(lore);
                if (n_c.isSimilar(n_s) && itemStack.getAmount() <= countItemInInventory(inventory, itemStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int countItemInInventory(Inventory inventory, ItemStack itemStack) {
        int amount = 0;
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                List<String> lore = new ArrayList<>();
                ItemStack n_c = new ItemStack(content);
                ItemStack n_s = new ItemStack(itemStack);
                n_c.setLore(lore);
                n_s.setLore(lore);
                if (n_c.isSimilar(n_s)) {
                    amount += content.getAmount();
                }
            }
        }
        return amount;
    }

    public static void removeItemsFromInventory(Inventory inventory, ItemStack itemStack, int amount) {
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                List<String> lore = new ArrayList<>();
                ItemStack n_c = new ItemStack(content);
                ItemStack n_s = new ItemStack(itemStack);
                n_c.setLore(lore);
                n_s.setLore(lore);
                if (n_c.isSimilar(n_s)) {
                    if(content.getAmount() >= amount) {
                        content.setAmount(content.getAmount() - amount);
                    } else {
                        amount -= content.getAmount();
                        content.setAmount(0);
                    }
                }
            }
        }
    }

    public static boolean defaultAlcoholDrinkAction(World world, Player player, ItemStack itemStack, float alcoholMinus) {
        FuchsMCItem fuchsItem = new FuchsMCItem(Utility.getFuchsItemFromNMS(itemStack), itemStack);
        if(fuchsItem.getTagFromOriginal().hasKey(ItemNBT.LIQUID_AMOUNT)) {
            if(fuchsItem.getDoubleFromTag(ItemNBT.LIQUID_AMOUNT) > 0) {
                FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
                fuchsPlayer.getPlayerSpecial().increaseAlcohol(new Random().nextDouble() / alcoholMinus);
                player.sendMessage("Alkohol: " + fuchsPlayer.getPlayerSpecial().getAlcohol());
                fuchsItem.changeDoubleTag(ItemNBT.LIQUID_AMOUNT, fuchsItem.getDoubleFromTag(ItemNBT.LIQUID_AMOUNT) - 10);

                if(fuchsItem.getDoubleFromTag(ItemNBT.LIQUID_AMOUNT) > 0) {
                    itemStack = fuchsItem.getItemStack();
                } else {
                    itemStack = ItemList.GLASS.createItem(player);
                }

                player.getInventory().setItemInMainHand(itemStack);
                Utility.makeDrunk(player, fuchsPlayer);
                if(fuchsPlayer.getPlayerSpecial().getAlcohol() > 1.25) {
                    player.getWorld().playEffect(player.getLocation().add(player.getLocation().getDirection().multiply(1.2)), Effect.VILLAGER_PLANT_GROW, 55, 1);
                }
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
                DataHandler.savePlayer(fuchsPlayer);
                player.updateInventory();
                return true;
            }
        }
        return false;
    }

    public static ItemStack damageFuchsItem(Player player, FuchsItem fuchsItem, ItemStack itemStack) {
        FuchsMCItem fuchsMCItem = new FuchsMCItem(fuchsItem, itemStack);
        if(fuchsMCItem.getTagFromOriginal().hasKey(ItemNBT.ITEM_DURABILITY)) {
            fuchsMCItem.changeIntTag(ItemNBT.ITEM_DURABILITY, fuchsMCItem.getIntFromTag(ItemNBT.ITEM_DURABILITY) - 1);
            ItemStack newItem = fuchsMCItem.getItemStack();
            if(fuchsMCItem.getIntFromTag(ItemNBT.ITEM_DURABILITY) < 1) {
                newItem = new ItemStack(Material.AIR);
                if(player != null) {
                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
                }
            }
            return newItem;
        }
        return itemStack;
    }

    public static ItemStack damageFuchsItem(FuchsItem fuchsItem, ItemStack itemStack) {
        return damageFuchsItem(null, fuchsItem, itemStack);
    }
}
