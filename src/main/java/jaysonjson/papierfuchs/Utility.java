package jaysonjson.papierfuchs;

import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.FileHandler;
import jaysonjson.papierfuchs.data.area.data.zArea;
import jaysonjson.papierfuchs.data.area.obj.zLocation;
import jaysonjson.papierfuchs.data.backpack.data.zBackPack;
import jaysonjson.papierfuchs.data.guild.data.zGuild;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.gas.FuchsGas;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsMCItem;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.object.liquid.LiquidList;
import jaysonjson.papierfuchs.registry.FuchsRegistries;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
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

    public static zArea getNearestArea(Player player) {
        return getNearestArea(player.getWorld().getEnvironment(), player.getLocation());
    }

    @Deprecated
    public static zLocation getNearestAreaDistanceDEP(Location location) {
        ArrayList<Double> distancesX = new ArrayList<>();
        ArrayList<Double> distancesZ = new ArrayList<>();
        for (zArea areas : PapierFuchs.INSTANCE.areas) {
            distancesX.add(location.getX() - areas.getLocation().x);
            distancesZ.add(location.getZ() - areas.getLocation().z);
        }
        Collections.sort(distancesX);
        Collections.reverse(distancesX);
        Collections.sort(distancesZ);
        Collections.reverse(distancesZ);
        return new zLocation(distancesX.get(0), 0, distancesZ.get(0));
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

    public static double countMoney(Player player) {
        return countMoney(player.getInventory());
    }

    public static double countMoney(Inventory inventory) {
        return countMoney(inventory.getContents());
    }

    public static double countMoney(ItemStack[] contents) {
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

    public static double countMoneyBackpack(Inventory inventory) {
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
                        ItemStack[] contents = generateInventoryContent(DataHandler.loadBackPack(UUID.fromString(tag.getString(ItemNBT.ITEM_UUID))).inventoryContent);
                        amount += countMoney(contents);
                    }
                }
            }
        }
        return amount;
    }

    public static double countZoryhaShard(Inventory inventory) {
        return countZoryhaShard(inventory.getContents());
    }

    public static double countZoryhaShard(ItemStack[] contents) {
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

    public static double countZoryhaShardBackpack(Inventory inventory) {
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
                        ItemStack[] contents = generateInventoryContent(DataHandler.loadBackPack(UUID.fromString(tag.getString(ItemNBT.ITEM_UUID))).inventoryContent);
                        amount += countZoryhaShard(contents);
                    }
                }
            }
        }
        return amount;
    }

    public static double countZoryhaShard(Player player) {
        return countZoryhaShard(player.getInventory());
    }

    public static double convertHacksilverToZoryhaShard(double hacksilverAmount) {
        return hacksilverAmount / Constant.HACKSILVER_TO_ZORYHASHARD_VALUE;
    }

    public static double convertZoryhaShardToHacksilver(double zoryhaShardAmount) {
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
        if(fuchsPlayer.getPlayerSpecial().alcohol > 0.5) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (fuchsPlayer.getPlayerSpecial().alcohol * 740), 0));
        }
        if(fuchsPlayer.getPlayerSpecial().alcohol > 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (fuchsPlayer.getPlayerSpecial().alcohol * 100),0));
        }
        if(fuchsPlayer.getPlayerSpecial().alcohol > 2) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (fuchsPlayer.getPlayerSpecial().alcohol * 60),0));
        }
        if(fuchsPlayer.getPlayerSpecial().alcohol > 3) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (fuchsPlayer.getPlayerSpecial().alcohol * 700),2));
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

    public static ItemStack[] removeMoney(ItemStack[] contents, double amount) {
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
    public static ItemStack[] removeMoneyBackpack(Inventory inventory, double amount) {
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

}
