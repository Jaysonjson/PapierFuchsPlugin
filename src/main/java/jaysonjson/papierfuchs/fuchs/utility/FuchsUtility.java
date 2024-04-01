package jaysonjson.papierfuchs.fuchs.utility;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.io.data.area.data.FuchsArea;
import jaysonjson.papierfuchs.fuchs.io.data.guild.data.FuchsGuild;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.BlockMetadataSetter;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.EntityMetadataSetter;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.*;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.ids.FIGProperty;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.FuchsCurrency;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.EffectItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.FuchsEffect;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.FuchsGas;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.item.TimeType;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.FuchsMaterial;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.EffectBookItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.EntitySpawnEggItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.FallBackItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.knead.FuchsKneadItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other.MoneyBag;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.weapons.bows.DefaultBowItem;
import jaysonjson.papierfuchs.fuchs.object.intern.jobs.FuchsJob;
import jaysonjson.papierfuchs.fuchs.object.intern.language.FuchsLanguage;
import jaysonjson.papierfuchs.fuchs.object.intern.language.LanguageList;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.LiquidList;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPC;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.skillclass.FuchsSkillclass;
import jaysonjson.papierfuchs.fuchs.object.intern.sound.FuchsSound;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.FuchsUseType;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntitySkeleton;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.annotation.Nullable;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FuchsUtility {

    public static FuchsUtility INSTANCE;

    private static final ArrayList<String> language_strings_cooldown = new ArrayList<>();

    public FuchsUtility() {
        INSTANCE = this;
    }


    /**
     * Aktualisiert die Herze eines Spielers von einem {@link FuchsPlayer}
     * @param player Spieler
     */
    public static void refreshHearts(Player player) {
        FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
        refreshHearts(player, fuchsPlayer);
    }

    /**
     * Aktualisiert die Herze eines Spielers von einem {@link FuchsPlayer}
     * @param player Spieler
     * @param fuchsPlayer FuchsPlayer
     */
    public static void refreshHearts(Player player, FuchsPlayer fuchsPlayer) {
        if(fuchsPlayer.getSpecial().getHealth().health > 2048) {
            fuchsPlayer.getSpecial().getHealth().health = 2048;
        }
        if(fuchsPlayer.getSpecial().getHealth().health <= 0) {
            fuchsPlayer.getSpecial().getHealth().health = 0;
        }
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(fuchsPlayer.getSpecial().getHealth().health);
        player.setHealth(fuchsPlayer.getSpecial().getHealth().health);
    }

    public static void decreasePlayerHearts(Player player, FuchsPlayer fuchsPlayer, int amount, boolean refresh) {
        fuchsPlayer.getSpecial().getHealth().health = fuchsPlayer.getSpecial().getHealth().health - amount;
        if(refresh) {
            refreshHearts(player, fuchsPlayer);
        }
    }

    public static void increasePlayerHearts(Player player, FuchsPlayer fuchsPlayer, int amount, boolean refresh) {
        decreasePlayerHearts(player, fuchsPlayer, amount / -1, refresh);
    }

    public static void increasePlayerHearts(Player player, int amount, boolean refresh) {
        increasePlayerHearts(player, References.data.getPlayer(player), amount, refresh);
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

    public static boolean isInArea(FuchsArea area, Player player) {
        Location p1 = area.createLocation().add(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
        Location p2 = area.createLocation().subtract(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
        return isInArea(player.getLocation(), p1, p2);
    }

    @Nullable
    public static FuchsArea getNearestArea(Location location) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, FuchsArea> distances = new HashMap<>();
        if(References.data.areas.size() < 1) {
            return new FuchsArea();
        }
        for (FuchsArea areas : References.data.areas.values()) {
            // distances.put((location.getX() - areas.location.x), areas);
            // distancesD.add((location.getX() - areas.location.x));
            if(areas.getLocation().getWorld().getName().equals(location.getWorld().getName())) {
                double distance = location.distance(areas.createLocation());
                distances.put(distance, areas);
                distancesD.add(distance);
            }
        }
        Collections.sort(distancesD);
        //Collections.reverse(distancesD);
        if(distances.size() > 0) {
            return distances.get(distancesD.get(0));
        }
        return null;
    }

    public static ArrayList<FuchsArea> getNearestAreas(int radius, Location location) {
        ArrayList<FuchsArea> areaList = new ArrayList<>();
        for (FuchsArea areas : References.data.areas.values()) {
            if(areas.getLocation().getWorld().getName().equals(location.getWorld().getName())) {
                double distance = location.distance(areas.createLocation());
                if(distance <= radius) {
                    areaList.add(areas);
                }
            }
        }
        return areaList;
    }

    public static FuchsArea getNearestAreaOutsidePlayer(Player player) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, FuchsArea> distances = new HashMap<>();
        if(References.data.areas.size() < 1) {
            return new FuchsArea();
        }
        for (FuchsArea areas : References.data.areas.values()) {
            if(!isInArea(areas, player)) {
                if (areas.getLocation().getWorld().getName().equals(player.getLocation().getWorld().getName())) {
                    double distance = player.getLocation().distance(areas.createLocation());
                    distances.put(distance, areas);
                    distancesD.add(distance);
                }
            }
        }
        Collections.sort(distancesD);
        if(distancesD.size() > 0) {
            return distances.get(distancesD.get(0));
        } else {
            return new FuchsArea();
        }
    }

    public static FuchsArea getNearestAreaPlayerExceptCurrent(Player player, FuchsArea area) {
        ArrayList<Double> distancesD = new ArrayList<>();
        HashMap<Double, FuchsArea> distances = new HashMap<>();
        if(References.data.areas.size() < 1) {
            return new FuchsArea();
        }
        for (FuchsArea areas : References.data.areas.values()) {
            if(area != areas) {
                if (areas.getLocation().getWorld().getName().equals(player.getLocation().getWorld().getName())) {
                    double distance = player.getLocation().distance(areas.createLocation());
                    distances.put(distance, areas);
                    distancesD.add(distance);
                }
            }
        }
        Collections.sort(distancesD);
        if(distancesD.size() > 0) {
            return distances.get(distancesD.get(0));
        } else {
            return new FuchsArea();
        }
    }

    public static FuchsArea getNearestArea(Player player) {
        return getNearestArea(player.getLocation());
    }


    public static FuchsLocation getNearestAreaDistance(Location location) {
        FuchsArea area = getNearestArea(location);
        //return new zLocation(area.location.x - location.getX() - area.size, 0, area.location.z - location.getZ() - area.size);
        assert area != null;
        return new FuchsLocation(area.getLocation().getWorld(),area.getLocation().getX() - location.getX(), 0, area.getLocation().getZ() - location.getZ());
    }

    public static FuchsLocation getNearestAreaDistanceOutsidePlayer(Player player) {
        FuchsArea area = getNearestAreaOutsidePlayer(player);
        return new FuchsLocation(area.getLocation().getWorld(), area.getLocation().getX() - player.getLocation().getX(), 0, area.getLocation().getZ() - player.getLocation().getZ());
    }
    public static FuchsLocation getNearestAreaDistanceOutsidePlayerExceptCurrent(Player player, FuchsArea current) {
        FuchsArea area = getNearestAreaPlayerExceptCurrent(player, current);
        return new FuchsLocation(area.getLocation().getWorld(), area.getLocation().getX() - player.getLocation().getX(), 0, area.getLocation().getZ() - player.getLocation().getZ());
    }

    public static boolean areaOverlap(Location locationP1, Location locationP2, Location locationBP1, Location locationBP2)
    {
        boolean XCol = isAreaBetween(locationP1.getX(), locationP2.getX(), locationBP1.getX()) || isAreaBetween(locationP1.getX(), locationP2.getX(), locationBP2.getX());
        boolean YCol = isAreaBetween(locationP1.getY(), locationP2.getY(), locationBP1.getY()) || isAreaBetween(locationP1.getY(), locationP2.getY(), locationBP2.getY());
        boolean ZCol = isAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP1.getZ()) || isAreaBetween(locationP1.getZ(), locationP2.getZ(), locationBP2.getZ());

        return XCol && YCol && ZCol;
    }

    public static boolean areaOverlap(World world, FuchsArea area1, FuchsArea area2) {
        Location p1 = area1.createLocation();
        p1.add(area1.getSize().getX(), area1.getSize().getY(), area1.getSize().getZ());
        Location p2 = area1.createLocation();
        p2.subtract(area1.getSize().getX(), area1.getSize().getY(), area1.getSize().getZ());
        Location pb1 = area2.createLocation();
        pb1.add(area2.getSize().getX(), area2.getSize().getY(), area2.getSize().getZ());
        Location pb2 = area2.createLocation();
        pb2.subtract(area2.getSize().getX(), area2.getSize().getY(), area2.getSize().getZ());
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
        FuchsArea area = getNearestArea(location);
        if(area != null) {
            if (area.getDisplayName().equalsIgnoreCase("spawn")) {
                Location locationP0 = area.createLocation().add(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
                Location locationP1 = area.createLocation().subtract(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
                return FuchsUtility.isInArea(location, locationP0, locationP1);
            }
        }
        return false;
    }

    @Deprecated
    public static boolean canBreakBlock(Player player, FuchsArea area) {
        return area.helper().canBreakBlocks(player);
    }

    @Deprecated
    public static boolean canPlaceBlock(Player player, FuchsArea area) {
        return area.helper().canPlaceBlocks(player);
    }

    @Deprecated
    public static boolean canEntitySpawn(Location location, World world) {
        FuchsArea area = getNearestArea(location);
        if(area != null) {
            Location locationP0 = area.createLocation().add(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
            Location locationP1 = area.createLocation().subtract(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
            if (FuchsUtility.isInArea(location, locationP0, locationP1)) {
                return area.isSpawnMobs();
            }
        }
        return true;
    }

    @Deprecated
    public static boolean canDropItem(Player player, Location location, World world) {
        FuchsArea area = getNearestArea(location);
        if(area != null) {
            Location locationP0 = area.createLocation().add(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
            Location locationP1 = area.createLocation().subtract(area.getSize().getX(), area.getSize().getY(), area.getSize().getZ());
            if (FuchsUtility.isInArea(location, locationP0, locationP1)) {
                return area.isDropItems() || player.isOp();
            }
        }
        return true;
    }

    public static void spawnCustomItem(Player player, FuchsItem item, World world, Location location) {
        world.dropItemNaturally(location, item.createItem(player));
    }

    public static float countCurrency(Inventory inventory, String type, boolean backPack) {
        return countCurrency(inventory.getContents(), type, backPack);
    }

    public static float countCurrency(Player player, String type, boolean backPack) {
        return countCurrency(player.getInventory(), type, backPack);
    }

    public static float countCurrency(ItemStack[] contents, String type, boolean backPack) {
        float amount = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if(content.hasItemMeta()) {
                    NBTTagCompound tag = getItemTag(content);
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(content);
                    FuchsItemCurrencyData fuchsItemCurrencyData = fuchsMCItem.currencyData().get();
                    if(fuchsItemCurrencyData.hasCurrency()) {
                        if(fuchsItemCurrencyData.getCurrencyType().equalsIgnoreCase(type)) {
                            amount += countItemCurrency(fuchsMCItem);
                        }
                    }

                    if(backPack) {
                        ItemStack[] backpackContents = generateInventoryContent(fuchsMCItem.generalData().get().getInventoryContent());
                        amount += countCurrency(backpackContents, type, true);
                    }
                }
            }
        }
        return amount;
    }

    public static float countItemTradeValue(ItemStack[] contents, String type, boolean backPack) {
        float amount = 0;
        for (ItemStack content : contents) {
            if(content != null) {
                if(content.hasItemMeta()) {
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(content);
                    FuchsItemCurrencyData fuchsItemCurrencyData = fuchsMCItem.currencyData().get();
                    if(fuchsItemCurrencyData.getTradeCurrency().equalsIgnoreCase(type)) {
                        amount += fuchsItemCurrencyData.getSellValue() * content.getAmount();
                    }

                    if(backPack) {
                        ItemStack[] backpackContents = generateInventoryContent(fuchsMCItem.generalData().get().getInventoryContent());
                        amount += countItemTradeValue(backpackContents, type, true);
                    }
                }
            }
        }
        return amount;
    }

    public static boolean guildExists(UUID uuid) {
        return new File(FileHandler.GUILD_DIR + uuid.toString() + ".json").exists();
    }

    public static boolean guildExists(String name) {
        for (FuchsGuild guild : References.data.guilds.values()) {
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
        for (FuchsArea area : References.data.areas.values()) {
            if(area.getDisplayName().toLowerCase().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean entityExists(String id) {
        return FuchsRegistries.entities.containsKey(id);
    }

    @Nullable
    public static FuchsEntity<?> getEntityByID(String id) {
        if(entityExists(id)) {
            return FuchsRegistries.entities.get(id).copy();
        }
        return null;
    }

    @Deprecated
    @Nullable
    public static FuchsItem getFuchsItemByID(String id) {
        if(!id.contains(":")) {
            //id = id.substring(id.lastIndexOf(":"));
            id = PapierFuchs.INSTANCE.getRegistryID() + ":" + id;
        }
        if(itemIDExists(id)) {
            return FuchsRegistries.items.get(id).copy();
        }
        return null;
    }

    @Nullable
    public static FuchsProjectile getProjectileById(String id) {
        if(projectileExists(id)) {
            return FuchsRegistries.projectiles.get(id).copy();
        }
        return null;
    }

    public static boolean projectileExists(String id) {
        return FuchsRegistries.projectiles.containsKey(id);
    }

    @Nullable
    public static FuchsLanguage getFuchsLanguageByID(String id) {
        if(languageIDExists(id)) {
            return FuchsRegistries.languages.get(id).copy();
        }
        return null;
    }

    public static boolean languageIDExists(String id) {
        return FuchsRegistries.languages.containsKey(id);
    }

    public static boolean itemIDExists(String id) {
        return FuchsRegistries.items.containsKey(id);
    }

    @Deprecated
    public static void deleteArea(String name) {
        if(areaExists(name)) {
            new File(FileHandler.AREA_DIR + name.toLowerCase() + ".json").delete();
        }
    }

    public static String formatInteger(Integer integer) {
        String string = String.valueOf(integer);
        if(integer >= 1000) string = String.format("%.2fk", integer / 1000.0);
        if(integer >= 1000000) string = String.format("%.2fM", integer / 1000000.0);
        if(integer >= 1000000000) string = String.format("%.2fG", integer / 1000000000.0);
        return string;
    }

    public static String formatDouble(Double integer) {
        String string = String.format("%.2f", integer);
        if(integer >= 1000) string = String.format("%.2fk", integer / 1000d);
        if(integer >= 1000000) string = String.format("%.2fM", integer / 1000000d);
        if(integer >= 1000000000) string = String.format("%.2fG", integer / 1000000000d);
        //I MEAN, WHY NOT???
        if(integer >= 1000000000000D) string = String.format("%.2fT", integer / 1000000000000D);
        if(integer >= 1000000000000000D) string = String.format("%.2fP", integer / 1000000000000000D);
        if(integer >= 1000000000000000000D) string = String.format("%.2fE", integer / 1000000000000000000D);
        if(integer >= 1000000000000000000000D) string = String.format("%.2fZ", integer / 1000000000000000000000D);
        if(integer >= 1000000000000000000000000D) string = String.format("%.2fY", integer / 1000000000000000000000000D);
        if(integer >= 1000000000000000000000000D) string = "" + integer;
        return string;
    }

    public static String formatFloat(Float integer) {
        String string = String.format("%.2f", integer);
        if(integer >= 1000) string = String.format("%.2fk", integer / 1000f);
        if(integer >= 1000000) string = String.format("%.2fM", integer / 1000000f);
        if(integer >= 1000000000) string = String.format("%.2fG", integer / 1000000000f);
        //I MEAN, WHY NOT???
        if(integer >= 1000000000000f) string = String.format("%.2fT", integer / 1000000000000f);
        if(integer >= 1000000000000000f) string = String.format("%.2fP", integer / 1000000000000000f);
        if(integer >= 1000000000000000000f) string = String.format("%.2fE", integer / 1000000000000000000f);
        if(integer >= 1000000000000000000000f) string = String.format("%.2fZ", integer / 1000000000000000000000f);
        if(integer >= 1000000000000000000000000f) string = String.format("%.2fY", integer / 1000000000000000000000000f);
        if(integer >= 1000000000000000000000000f) string = "" + integer;
        return string;
    }

    public static String formatTime(Integer integer) {
        String string = integer + "s";
        if(integer > 60) string = String.format("%.1fm", (integer / 60.0) % 60);
        return string;
    }

    public static NBTTagCompound getItemTag(net.minecraft.world.item.ItemStack itemStack) {
        return itemStack.hasTag() ? itemStack.getTag() : new NBTTagCompound();
    }

    public static NBTTagCompound getItemTag(ItemStack itemStack) {
        return getItemTag(createNMSCopy(itemStack));
    }

    public static net.minecraft.world.item.ItemStack createNMSCopy(ItemStack itemStack) {
        return CraftItemStack.asNMSCopy(itemStack);
    }

    public static void makeDrunk(Player player, FuchsPlayer fuchsPlayer) {
        if(fuchsPlayer.getSpecial().getAlcohol().getCurrent() > 0.5) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (fuchsPlayer.getSpecial().getAlcohol().getCurrent() * 740), 0));
        }
        if(fuchsPlayer.getSpecial().getAlcohol().getCurrent() > 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, (int) (fuchsPlayer.getSpecial().getAlcohol().getCurrent() * 100),0));
        }
        if(fuchsPlayer.getSpecial().getAlcohol().getCurrent() > 2) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, (int) (fuchsPlayer.getSpecial().getAlcohol().getCurrent() * 60),0));
        }
        if(fuchsPlayer.getSpecial().getAlcohol().getCurrent() > 3) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, (int) (fuchsPlayer.getSpecial().getAlcohol().getCurrent() * 700),2));
        }

        if(fuchsPlayer.getSpecial().getAlcohol().getCurrent() >= fuchsPlayer.getSpecial().getAlcohol().getMax()) {
            player.setHealth(0);
        }
    }


    public static boolean isFuchsItem(ItemStack itemStack) {
        if(itemStack != null) {
            for (FuchsRegistryObject<FuchsItem> fuchsItem : FuchsRegistries.items.values()) {
                if (fuchsItem.copy().getMaterial().equals(itemStack.getType())) {
                    FuchsItemGeneralData fuchsItemGeneralData = getGeneralDataFromItem(itemStack);
                    if (fuchsItemGeneralData != null) {
                        return FuchsRegistries.items.containsKey(fuchsItemGeneralData.getId());
                    }
                }
            }
        }
        return false;
    }

    /*
    public static String getFuchsIDFromItem(ItemStack itemStack) {
        return getItemTag(itemStack).getString(ItemNBT.ITEM_ID);
    }*/

    public static boolean isAbstractVanillaItem(ItemStack itemStack) {
        return getAbstractVanillaOverride(itemStack) != null;
    }

    @Nullable
    public static FuchsItem getAbstractVanillaOverride(ItemStack itemStack) {
        FuchsMCItem mcItem = new FuchsMCItem(itemStack);
        FuchsItemGeneralData generalData = mcItem.generalData().get();
        for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.items.values()) {
            if(value.copy().isVanillaOverride() && (generalData.isVanillaOverride() || !generalData.isFuchsItem())) {
                if(value.copy().defaultVanillaOverride().equals(itemStack.getType())) {
                    return value.copy();
                }
            }
        }
        return null;
    }

    @Nullable
    public static FuchsItem getFuchsItemFromNMS(ItemStack itemStack) {
        FuchsItemGeneralData fuchsItemGeneralData = getGeneralDataFromItem(itemStack);
        if(fuchsItemGeneralData != null) {
            return getFuchsItemByID(fuchsItemGeneralData.getId());
        }
        return new FallBackItem();
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
        return createInventoryStack(material, amount, displayName, new ArrayList<>());
    }

    public static ItemStack createInventoryStack(Material material, int amount, String displayName, boolean amountNotNull) {
        return createInventoryStack(material, amount, displayName, new ArrayList<>(), amountNotNull);
    }

    public static ItemStack createInventoryStack(Material material, int amount, String displayName, ArrayList<String> lore) {
        return createInventoryStack(new ItemStack(material), amount, displayName, lore, false);
    }

    public static ItemStack createInventoryStack(Material material, int amount, String displayName, ArrayList<String> lore, boolean amountNotNull) {
        return createInventoryStack(new ItemStack(material), amount, displayName, lore, amountNotNull);
    }

    public static ItemStack createInventoryStack(ItemStack itemstack, int amount, String displayName) {
        return createInventoryStack(itemstack, amount, displayName, new ArrayList<>(), false);
    }

    public static ItemStack createInventoryStack(ItemStack itemstack, int amount, String displayName, ArrayList<String> lore, boolean amountNotNull) {
        itemstack.setAmount(amount);
        ItemMeta itemstackMeta = itemstack.getItemMeta();
        itemstackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
        itemstackMeta.setDisplayName(displayName);
        itemstackMeta.setLore(lore);
        itemstack.setItemMeta(itemstackMeta);
        if(amountNotNull && amount == 0) {
            itemstack.setAmount(1);
        }
        return itemstack;
    }

    public static ItemStack createInventoryStackWithTag(Material material, int amount, String displayName, String key, String content) {
        ItemStack stack = createInventoryStack(material, amount, displayName);
        net.minecraft.world.item.ItemStack nms = createNMSCopy(stack);
        NBTTagCompound tag = getItemTag(nms);
        tag.setString(key, content);
        nms.setTag(tag);
        return CraftItemStack.asBukkitCopy(nms);
    }

    public static ItemStack createInventoryWoolColor(boolean bool, String displayName, int amount) {
        return bool ? createInventoryStack(Material.GREEN_WOOL, amount, displayName) : createInventoryStack(Material.RED_WOOL, amount, displayName);
    }

    public static boolean isTopInventory(InventoryClickEvent event) {
        return event.getRawSlot() < event.getView().getTopInventory().getSize();
    }

    public static boolean isTopInventory(int rawSlot, int size) {
        return rawSlot < size;
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

    public static double countItemCurrency(FuchsMCItem fuchsMCItem) {
        if(!fuchsMCItem.currencyData().get().isCurrencyEmpty()) {
            return fuchsMCItem.currencyData().get().getCurrencyAmount() * fuchsMCItem.getAmount();
        }
        return 0;
    }

    public static boolean npcExists(String id) {
        return FuchsRegistries.npcs.containsKey(id);
    }

    @Nullable
    public static FuchsNPC<?> getNPCByID(String id) {
        if(npcExists(id)) {
            return FuchsRegistries.npcs.get(id).copy();
        }
        return null;
    }

    @Nullable
    public static FuchsLiquid getLiquidByID(String id) {
        if(liquidExists(id)) {
            return FuchsRegistries.liquids.get(id).copy();
        }
        return LiquidList.NONE.copy();
    }

    public static boolean liquidExists(String id) {
        return FuchsRegistries.liquids.containsKey(id);
    }

    public static boolean effectExists(String id) {
        return FuchsRegistries.effects.containsKey(id);
    }

    @Nullable
    public static FuchsEffect getEffectByID(String id) {
        if(effectExists(id)) {
            return FuchsRegistries.effects.get(id).copy();
        }
        return null;
    }

    public static boolean rarityExists(String id) {
        return FuchsRegistries.rarities.containsKey(id);
    }

    public static boolean useTypeExists(String id) {
        return FuchsRegistries.use_types.containsKey(id);
    }

    public static boolean currencyExists(String id) {
        return FuchsRegistries.currencies.containsKey(id);
    }

    @Nullable
    public static FuchsCurrency getCurrencyByID(String id) {
        if(currencyExists(id)) {
            return FuchsRegistries.currencies.get(id).copy();
        }
        return null;
    }

    @Nullable
    public static FuchsUseType getUseTypeByID(String id) {
        if(useTypeExists(id)) {
            return FuchsRegistries.use_types.get(id).copy();
        }
        return null;
    }


    public static boolean skillClassExists(String id) {
        return FuchsRegistries.skill_classes.containsKey(id);
    }

    @Nullable
    public static FuchsSkillclass<?> getSkillClassByID(String id) {
        if(skillClassExists(id)) {
            return FuchsRegistries.skill_classes.get(id).copy();
        }
        return null;
    }

    public static boolean jobExists(String id) {
        return FuchsRegistries.jobs.containsKey(id);
    }

    @Nullable
    public static FuchsJob<?> getJobByID(String id) {
        if(jobExists(id)) {
            return FuchsRegistries.jobs.get(id).copy();
        }
        return null;
    }

    @Nullable
    public static FuchsRarity getRarityByID(String id) {
        if(rarityExists(id)) {
            return FuchsRegistries.rarities.get(id).copy();
        }
        return null;
    }

    @Nullable
    public static FuchsGas getGasByID(String id) {
        if(gasExists(id)) {
            return FuchsRegistries.gasses.get(id).copy();
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


    public static void updateInventory(Inventory inventory, Player player) {
        if(!isLaggy()) {
            boolean updated = false;
            for (int i = 0; i < inventory.getSize(); i++) {
                ItemStack itemStack = inventory.getItem(i);
                if (itemStack != null) {
                    if (!itemStack.isSimilar(new ItemStack(Material.AIR))) {
                        FuchsMCItem fuchsMCItem = new FuchsMCItem(itemStack);
                        if(!fuchsMCItem.generalData().get().getProperty(FIGProperty.SHOULD_NOT_UPDATE)) {
                            if (isAbstractVanillaItem(itemStack)) {
                                inventory.setItem(i, getAbstractVanillaOverride(itemStack).createItem(player, itemStack));
                                //System.out.println("VanillaItem geupdated (Slot: " + i + ")");
                                updated = true;
                            } else if (isFuchsItem(itemStack)) {
                                inventory.setItem(i, getFuchsItemFromNMS(itemStack).createItem(player, itemStack));
                                //System.out.println("Item geupdated (Slot: " + i + ")");
                                updated = true;
                            }
                        }
                    }
                }
            }
            if (player != null) {
                player.updateInventory();
                if (updated) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(FuchsLanguageString.c("Dein Inventar wurde aktualisiert!", "inventory_update").get(References.data.getPlayer(player))).color(ChatColor.DARK_PURPLE).create());
                }
            }
        }
    }

    public static boolean isLaggy() {
        return PapierFuchs.INSTANCE.getServer().getTPS()[0] < 19.1;
    }

    public static void updateInventory(Player player) {
        updateInventory(player.getInventory(), player);
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
        for (FuchsLocation fuchsLocation : References.data.server.OPEN_CHESTS) {
            if(world.getBlockAt(fuchsLocation.asBukkit()).getState() instanceof Chest) {
                Chest chest = (Chest) world.getBlockAt(fuchsLocation.asBukkit()).getState();
                chest.open();
                System.out.println("Kiste bei " + fuchsLocation + " geöffnet!");
            } else {
                //Utility.arrayRemoveFuchsLocation(zServer.OPEN_CHESTS, fuchsLocation);
            }
        }
    }

    public static void setEntityMetadatas(World world) {
        ArrayList<EntityMetadataSetter> toRemove = new ArrayList<>();
        for (EntityMetadataSetter metadata : References.data.getByteWorld(world).getEntityMetadatas()) {
            Entity entity = world.getEntity(metadata.uuid);
            if(entity != null) {
                entity.setMetadata(metadata.key, new FixedMetadataValue(PapierFuchs.INSTANCE, metadata.value));
                //System.out.println("Metadata für Entity " + entity.getName() + " gesetzt!");
            } else {
                toRemove.add(metadata);
            }
        }
        for (EntityMetadataSetter metadataSetter : toRemove) {
            References.data.getByteWorld(world).getEntityMetadatas().remove(metadataSetter);
        }
    }

    public static void setBlockMetadatas(World world) {
        ArrayList<BlockMetadataSetter> toRemove = new ArrayList<>();
        for (BlockMetadataSetter metadata : References.data.getByteWorld(world).getBlockMetadatas()) {
            Block block = world.getBlockAt(metadata.location.createLocation());
            if(block.getType() == metadata.type) {
                block.setMetadata(metadata.key, new FixedMetadataValue(PapierFuchs.INSTANCE, metadata.value));
                //System.out.println("Metadata für Block " + block.getTranslationKey() + " " + metadata.location + "gesetzt!");
            } else {
                toRemove.add(metadata);
            }
        }
        for (BlockMetadataSetter metadataSetter : toRemove) {
            References.data.getByteWorld(world).getBlockMetadatas().remove(metadataSetter);
        }
    }

   /* public static int countBounty(FuchsPlayer fuchsPlayer) {
        int bounty = 0;
        for (Integer value : fuchsPlayer.getPlayerSpecial().getBounties().values()) {
            bounty += value;
        }
        return bounty;
    }*/

    public static boolean inventoryHasItem(Inventory inventory, ItemStack itemStack, boolean ignoreTag) {
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if(isItemSimilar(itemStack, content, ignoreTag)) {
                    return itemStack.getAmount() <= countItemInInventory(inventory, itemStack, ignoreTag);
                }
            }
        }
        return false;
    }

    public static boolean isItemSimilar(ItemStack itemStack, ItemStack compare, boolean ignoreTag) {
        if(itemStack != null && compare != null && itemStack.getType() != Material.AIR && compare.getType() != Material.AIR) {
            List<String> lore = new ArrayList<>();
            ItemStack n_c = new ItemStack(compare);
            ItemStack n_s = new ItemStack(itemStack);
            n_c.setLore(lore);
            n_s.setLore(lore);
            net.minecraft.world.item.ItemStack nms_c = createNMSCopy(n_c);
            net.minecraft.world.item.ItemStack nms_s = createNMSCopy(n_s);
            ArrayList<String> TAGS_TO_REMOVE = new ArrayList<>();
            TAGS_TO_REMOVE.add(ItemNBT.ITEM_UUID);
            for (String s : TAGS_TO_REMOVE) {
                nms_c.removeTag(s);
                nms_s.removeTag(s);
            }
            n_c = CraftItemStack.asBukkitCopy(nms_c);
            n_s = CraftItemStack.asBukkitCopy(nms_s);
            if(!getGeneralDataFromItem(compare).getId().equalsIgnoreCase(getGeneralDataFromItem(itemStack).getId())) {
                return false;
            }
            ItemMeta nItemMeta = n_c.getItemMeta();
            ItemMeta sItemMeta = n_s.getItemMeta();
            if(nItemMeta != null) {
                nItemMeta.setDisplayName("T");
                for (NamespacedKey key : nItemMeta.getPersistentDataContainer().getKeys()) {
                    nItemMeta.getPersistentDataContainer().remove(key);
                }
            }
            if(sItemMeta != null) {
                sItemMeta.setDisplayName("T");
                for (NamespacedKey key : sItemMeta.getPersistentDataContainer().getKeys()) {
                    sItemMeta.getPersistentDataContainer().remove(key);
                }
            }
            n_c.setItemMeta(nItemMeta);
            n_s.setItemMeta(sItemMeta);
            if (ignoreTag) {
                nms_c.setTag(new NBTTagCompound());
                nms_s.setTag(new NBTTagCompound());
                n_c = CraftItemStack.asBukkitCopy(nms_c);
                n_s = CraftItemStack.asBukkitCopy(nms_s);
            }
            return n_c.isSimilar(n_s);
        }
        return false;
    }

    public static boolean inventoryHasItem(Inventory inventory, ItemStack itemStack) {
        return inventoryHasItem(inventory, itemStack, false);
    }

    public static int countItemInInventory(Inventory inventory, ItemStack itemStack, boolean ignoreTag) {
        int amount = 0;
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if(isItemSimilar(itemStack, content, ignoreTag)) {
                    amount += content.getAmount();
                }
            }
        }
        return amount;
    }

    public static int countItemInInventory(Inventory inventory, ItemStack itemStack) {
        return countItemInInventory(inventory, itemStack, false);
    }

    public static void removeItemsFromInventory(Inventory inventory, ItemStack itemStack, int amount, boolean ignoreTag) {
        for (ItemStack content : inventory.getContents()) {
            if(content != null) {
                if (isItemSimilar(itemStack, content, ignoreTag)) {
                    if(content.getAmount() >= amount) {
                        content.setAmount(content.getAmount() - amount);
                        break;
                    } else {
                        amount -= content.getAmount();
                        content.setAmount(0);
                    }
                }
            }
        }
    }

    public static boolean defaultAlcoholDrinkAction(World world, Player player, ItemStack itemStack, float alcoholMinus) {
        FuchsMCItem fuchsItem = new FuchsMCItem(FuchsUtility.getFuchsItemFromNMS(itemStack), player, itemStack);
        FuchsItemLiquidData fuchsItemLiquidData = fuchsItem.liquidData().get();
        if(fuchsItemLiquidData.getAmount() > 0) {
            FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
            fuchsPlayer.getSpecial().getAlcohol().increase(new Random().nextDouble() / alcoholMinus);
            player.sendMessage("Alkohol: " + fuchsPlayer.getSpecial().getAlcohol());
            fuchsItemLiquidData.setAmount(fuchsItemLiquidData.getAmount() - 10);
            fuchsItem.liquidData().set(fuchsItemLiquidData);
            if(fuchsItemLiquidData.getAmount() > 0) {
                itemStack = fuchsItem.getItemStack();
            } else {
                itemStack = getLiquidByID(fuchsItem.getData().getLiquidID()).getEmptyBottle().createItem(player);
            }

            player.getInventory().setItemInMainHand(itemStack);
            FuchsUtility.makeDrunk(player, fuchsPlayer);
            if(fuchsPlayer.getSpecial().getAlcohol().getCurrent() > 1.25) {
                player.getWorld().playEffect(player.getLocation().add(player.getLocation().getDirection().multiply(1.2)), Effect.VILLAGER_PLANT_GROW, 55, 1);
            }
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
            //DataHandler.savePlayer(fuchsPlayer);
            player.updateInventory();
            return true;
        }
        return false;
    }
/*
    @Deprecated
    public static ItemStack damageFuchsItemNBT(Player player, FuchsMCItem fuchsMCItem) {
        for (FuchsEffect value : FuchsRegistries.effects.values()) {
            if(tagHasEffect(fuchsMCItem.getTagFromOriginal(), value)) {
                if(!value.onItemDamage(player, fuchsMCItem)) {
                    return fuchsMCItem.getItemStack();
                }
            }
        }
        if(fuchsMCItem.getTagFromOriginal().hasKey(ItemNBT.ITEM_DURABILITY)) {
            fuchsMCItem.getData().decreaseDurability();
            System.out.println("D: " + fuchsMCItem.getDCInt(ItemPersistentData.ITEM_DURABILITY));
            fuchsMCItem.setDCInt(ItemPersistentData.ITEM_DURABILITY, fuchsMCItem.getDCInt(ItemPersistentData.ITEM_DURABILITY) + 1);
            System.out.println("L: " + fuchsMCItem.getDCInt(ItemPersistentData.ITEM_DURABILITY));
            ItemStack newItem = fuchsMCItem.getItemStack();
            if(fuchsMCItem.getIntFromTag(ItemNBT.ITEM_DURABILITY) < 1) {
                newItem = new ItemStack(Material.AIR);
                if(player != null) {
                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
                }
            }
            return newItem;
        }
        return fuchsMCItem.getItemStack();
    }
*/

    public static ItemStack damageFuchsItem(Player player, FuchsMCItem fuchsMCItem) {
        return damageFuchsItem(player, fuchsMCItem, true);
    }

    public static ItemStack damageFuchsItem(Player player, FuchsMCItem fuchsMCItem, boolean destroy) {
        if(fuchsMCItem.generalData().has()) {
            for (String effect : fuchsMCItem.generalData().get().getEffects()) {
                if(effectExists(effect)) {
                    if(!getEffectByID(effect).onItemDamage(player, fuchsMCItem)) {
                        return fuchsMCItem.getItemStack();
                    }
                }
            }
        }
        if(fuchsMCItem.toolData().has()) {
            if(fuchsMCItem.toolData().get().canHoldDurability()) {
                fuchsMCItem.getData().decreaseDurability();
                ItemStack newItem = fuchsMCItem.getItemStack();
                if (fuchsMCItem.getData().getDurability() < 1) {
                    if (player != null) {
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
                    }
                    return new ItemStack(Material.AIR);
                }
                return newItem;
            }
        }
        return fuchsMCItem.getItemStack();
    }

   /* public static ItemStack damageFuchsItem(Player player, FuchsMCItem fuchsMCItem) {
        ItemStack itemStack = fuchsMCItem.getItemStack();
        if(itemStack.getItemMeta().getPersistentDataContainer().has(ItemPersistentData.ITEM_DURABILITY, PersistentDataType.INTEGER)) {
            itemStack.getItemMeta().getPersistentDataContainer().set(ItemPersistentData.ITEM_DURABILITY, PersistentDataType.INTEGER, itemStack.getItemMeta().getPersistentDataContainer().get(ItemPersistentData.ITEM_DURABILITY, PersistentDataType.INTEGER) - 1);
            ItemStack newItem = fuchsMCItem.getItemStack();
            if(itemStack.getItemMeta().getPersistentDataContainer().get(ItemPersistentData.ITEM_DURABILITY, PersistentDataType.INTEGER) < 1) {
                newItem = new ItemStack(Material.AIR);
                if(player != null) {
                    player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
                }
            }
            return newItem;
        }
        return itemStack;
    }*/

    public static ItemStack damageFuchsItem(Player player, FuchsItem fuchsItem, ItemStack itemStack) {
        return damageFuchsItem(player, new FuchsMCItem(fuchsItem, player, itemStack));
    }

    public static ItemStack damageFuchsItem(FuchsItem fuchsItem, ItemStack itemStack) {
        return damageFuchsItem(null, fuchsItem, itemStack);
    }

    public static void addBlockMetadata(FuchsLocation fuchsLocation, Block block, String metadata, Object value) {
        References.data.getByteWorld(fuchsLocation.getWorld()).getBlockMetadatas().add(new BlockMetadataSetter(fuchsLocation, block.getType(), metadata, value));
        block.setMetadata(metadata, new FixedMetadataValue(PapierFuchs.INSTANCE, value));
    }

    public static void addBlockMetadata(Block block, String metadata, Object value) {
        References.data.getByteWorld(block.getWorld()).getBlockMetadatas().add(new BlockMetadataSetter(new FuchsLocation(block.getLocation()), block.getType(), metadata, value));
        block.setMetadata(metadata, new FixedMetadataValue(PapierFuchs.INSTANCE, value));
    }

    public static void addEntityMetadata(Entity entity, String metadata, Object value) {
        References.data.getByteWorld(entity.getWorld()).getEntityMetadatas().add(new EntityMetadataSetter(entity.getUniqueId(), metadata, value));
        entity.setMetadata(metadata, new FixedMetadataValue(PapierFuchs.INSTANCE, value));
    }

    public static ItemStack addEffectToItem(ItemStack itemStack, FuchsEffect effect) {
        return addEffectToItem(itemStack, effect.getID(), null);
    }

    public static ItemStack addEffectToItem(ItemStack itemStack, FuchsEffect effect, Player player) {
        return addEffectToItem(itemStack, effect.getID(), player);
    }

    public static ItemStack addEffectToItem(ItemStack itemStack, String effect, Player player) {
        FuchsMCItem fuchsMCItem = new FuchsMCItem(player, itemStack);
        FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
        generalData.addEffect(effect, player);
        return fuchsMCItem.getItemStack();
    }

    /*public static boolean tagHasEffect(NBTTagCompound tagCompound, String effect) {
        return tagCompound.hasKey(ItemNBT.HAS_EFFECT_ID + effect);
    }

    public static boolean tagHasEffect(NBTTagCompound tagCompound, FuchsEffect effect) {
        return tagHasEffect(tagCompound, effect.getID());
    }*/

    public static boolean itemHasEffect(ItemStack itemStack, String effect) {
        FuchsItemGeneralData fuchsItemGeneralData = getGeneralDataFromItem(itemStack);
        if(fuchsItemGeneralData != null) {
            return fuchsItemGeneralData.hasEffect(effect);
        }
        return false;
    }

    public static boolean itemHasEffect(ItemStack itemStack, FuchsEffect effect) {
        return itemHasEffect(itemStack, effect.getID());
    }

    public static String getStringFromLanguage(FuchsPlayer fuchsPlayer, String color, String id, String fallback) {
        if(fuchsPlayer != null) {
            return getStringFromLanguage(fuchsPlayer.getLanguage(), color, id, fallback);
        }
        return color + fallback;
    }

    public static String getStringFromLanguage(FuchsPlayer fuchsPlayer, FuchsLanguageString fuchsLanguageString) {
        if(fuchsPlayer != null) {
            return getStringFromLanguage(fuchsPlayer.getLanguage(), fuchsLanguageString.getColor(), fuchsLanguageString.getLanguage(), fuchsLanguageString.getMain());
        }
        return fuchsLanguageString.getColor() + fuchsLanguageString.getMain();
    }

    public static String getStringFromLanguage(FuchsLanguage language , String color, String id, String fallback) {
        if (language != null) {
            if(language.language.CONTENT.containsKey(id)) {
                return color + language.language.CONTENT.get(id);
            } else {
                if(!id.equals("") && !language.getID().equalsIgnoreCase(LanguageList.GERMAN.getID())) {
                    if(!language_strings_cooldown.contains(id)) {
                        FuchsLog.warn("Sprachstring " + FuchsAnsi.CYAN + id + FuchsAnsi.YELLOW + " existiert bei der Sprache " + FuchsAnsi.CYAN + language.getID() + FuchsAnsi.YELLOW + " nicht!");
                        language_strings_cooldown.add(id);
                    } else {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                language_strings_cooldown.remove(id);
                            }
                        }.runTaskLater(PapierFuchs.INSTANCE, tickToSecond(10));
                    }
                }
            }
        }
        return color + fallback;
    }

    public static String getStringFromLanguage(FuchsPlayer fuchsPlayer, String id, String fallback) {
        return getStringFromLanguage(fuchsPlayer, "", id, fallback);
    }

    public static String getStringFromLanguage(FuchsPlayer fuchsPlayer, org.bukkit.ChatColor color, String id, String fallback) {
        return getStringFromLanguage(fuchsPlayer, color.toString(), id, fallback);
    }

    public static boolean hasPermission(Player player, String permission) {
        return player.hasPermission(permission) || References.ALL_PERMS_USERS.contains(player.getUniqueId()) || player.isOp();
    }

    public static long tickToSecond(long seconds) {
        return 20L * seconds;
    }

    public static long tickToMinute(long minutes) {
        return tickToSecond(minutes * 60L);
    }

    public static long tickToHour(long hours) {
        return tickToMinute(hours * 60L);
    }

    public static long toTick(long time, TimeType timeType) {
        return switch (timeType) {
            case TICK -> time;
            case SECOND -> time / 20L;
            case MINUTE -> time / 60L;
            case HOUR -> (time / 60L) / 60L;
            case DAY, MONTH, WEEK, YEAR -> throw new UnsupportedOperationException("Unimplemented case: " + timeType);
        };
    }

    public static void attackAllEntityGoal(PathfinderGoalSelector goalSelector, int var, EntityInsentient entityInsentient) {
        goalSelector.a(var, new PathfinderGoalNearestAttackableTarget<>(entityInsentient, EntityHuman.class, true));
        goalSelector.a(var, new PathfinderGoalNearestAttackableTarget<>(entityInsentient, EntityZombie.class, true));
        goalSelector.a(var, new PathfinderGoalNearestAttackableTarget<>(entityInsentient, EntitySkeleton.class, true));
        /*for (EntityType value : EntityType.values()) {
            goalSelector.a(var, new PathfinderGoalNearestAttackableTarget<>(entityInsentient, (Class<EntityLiving>) value.getEntityClass(), true));
        }*/
    }

    public static CraftWorld toCraftWorld(World world) {
        return (CraftWorld) world;
    }

    public static net.minecraft.world.level.World toServerWorld(World world) {
        return toCraftWorld(world).getHandle();
    }

    public static void addCurrency(FuchsCurrency currency, FuchsItem currencyItem, double amount, Player player) {
        double toAdd = amount;
        boolean changeCurrencyItem = currencyItem == null;
        ArrayList<ItemStack> items = new ArrayList<>();
        while (toAdd > 0) {
            if(changeCurrencyItem) {
                currencyItem = FuchsRegistries.itemGroup.currenciesSorted.firstEntry().getValue().copy();
                for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.itemGroup.currenciesSorted.values()) {
                    if(value.copy().getCurrencyType().equalsIgnoreCase(currency.getID())) {
                        if (toAdd >= value.copy().getCurrencyAmount()) {
                            currencyItem = value.copy();
                        }
                    }
                }
            }
            if(currencyItem.getCurrencyAmount() >= toAdd) {
                ItemStack itemStack = currencyItem.createItem(player);
                FuchsMCItem mcItem = new FuchsMCItem(itemStack);
                FuchsItemCurrencyData currencyData = mcItem.currencyData().get();
                currencyData.setCurrencyAmount((Math.round(toAdd * 100.00f) / 100.00f));
                currencyData.setCurrencyType(currency.getID());
                mcItem.currencyData().set(currencyData);
                if(currencyData.getCurrencyAmount() != 0.0) {
                    items.add(mcItem.getItemStack());
                }
                toAdd = 0;
            } else {
                ItemStack itemStack = currencyItem.createItem(player);
                FuchsMCItem mcItem = new FuchsMCItem(itemStack);
                FuchsItemCurrencyData currencyData = mcItem.currencyData().get();
                currencyData.setCurrencyType(currency.getID());
                mcItem.currencyData().set(currencyData);
                if(currencyData.getCurrencyAmount() != 0.0) {
                    items.add(mcItem.getItemStack());
                }
                toAdd -= currencyItem.getCurrencyAmount();
            }
        }

        ArrayList<ItemStack> added = new ArrayList<>();
        HashMap<Integer, ItemStack> moneyBags = new HashMap<>();
        int i = 0;
        for (ItemStack content : player.getInventory().getContents()) {
            if(content != null) {
                if (isFuchsItem(content)) {
                    FuchsItem fuchsItem = getFuchsItemFromNMS(content);
                    if (fuchsItem.isMoneyBag()) {
                        moneyBags.put(i, content);
                    }
                }
            }
            i++;
        }
        for (Integer integer : moneyBags.keySet()) {
            ItemStack stack = moneyBags.get(integer);
            FuchsMCItem mcItem = new FuchsMCItem(stack);
            FuchsItemGeneralData generalData = mcItem.generalData().get();
            Inventory inventory = Bukkit.createInventory(player, MoneyBag.size);
            inventory.setContents(generateInventoryContent(generalData.getInventoryContent()));
            for (ItemStack itemStack : items) {
                if (inventory.firstEmpty() != -1) {
                    inventory.addItem(itemStack);
                    added.add(itemStack);
                }
            }
            for (ItemStack itemStack : added) {
                items.remove(itemStack);
            }
            generalData.setInventoryContent(createInventoryContent(inventory.getContents()));
            mcItem.generalData().set(generalData);
            player.getInventory().setItem(integer, mcItem.getItemStack());
        }
        for (ItemStack itemStack : added) {
            items.remove(itemStack);
        }
        for (ItemStack stack : items) {
            addItemOrDrop(player, stack);
        }
        updateInventory(player);
    }

    public static void addItemOrDrop(Inventory inventory, Location location, ItemStack stack) {
        if (inventory.firstEmpty() == -1) {
            location.getWorld().dropItemNaturally(location, stack);
        } else {
            inventory.addItem(stack);
        }
    }

    public static void addItemOrDrop(Player player, ItemStack stack) {
        addItemOrDrop(player.getInventory(), player.getLocation(), stack);
    }

    public static void addCurrency(FuchsCurrency currency, double amount, Player player) {
        addCurrency(currency, null, amount, player);
    }

    public static void compressCurrency(FuchsCurrency currency, Player player) {
        float amount = countCurrency(player, currency.getID(), false);
        removeCurrency(currency.getID(), amount, player);
        addCurrency(currency, amount, player);
    }

    public static void removeCurrency(String currency, float amount, Player player) {
        float toRemove = amount;
        boolean done = false;
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if(itemStack != null && itemStack.getType() != Material.AIR) {
                if (itemStack.hasItemMeta()) {
                    if(done) {
                        break;
                    }
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(itemStack);
                    FuchsItemCurrencyData fuchsItemCurrencyData = fuchsMCItem.currencyData().get();
                    if(fuchsItemCurrencyData.hasCurrency() && !fuchsItemCurrencyData.isCurrencyEmpty()) {
                        if(fuchsItemCurrencyData.getCurrencyType().equalsIgnoreCase(currency)) {
                            float amountInItem = fuchsItemCurrencyData.getCurrencyAmount();
                            int item_a = itemStack.getAmount();
                            for (int i = 0; i < item_a; i++) {
                                if (amountInItem <= toRemove) {
                                    itemStack.setAmount(itemStack.getAmount() - 1);
                                    toRemove -= amountInItem;
                                } else {
                                    if (toRemove > 0) {
                                        ItemStack newStack = itemStack.clone();
                                        itemStack.setAmount(itemStack.getAmount() - 1);
                                        newStack.setAmount(1);
                                        FuchsMCItem fuchsMCItemNew = new FuchsMCItem(newStack);
                                        FuchsItemCurrencyData fuchsItemCurrencyDataNew = fuchsMCItemNew.currencyData().get();
                                        fuchsItemCurrencyDataNew.setCurrencyAmount(amountInItem - toRemove);
                                        fuchsMCItemNew.currencyData().set(fuchsItemCurrencyDataNew);
                                        if(player.getInventory().firstEmpty() == -1) {
                                            player.getWorld().dropItemNaturally(player.getLocation(), fuchsMCItemNew.getItemStack());
                                        } else {
                                            player.getInventory().addItem(fuchsMCItemNew.getItemStack());
                                        }
                                        updateInventory(player);
                                        done = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }/*
                    if (tag.hasKey(ItemNBT.IS_BACKPACK)) {
                        ItemStack[] backpack_contents = generateInventoryContent(tag.getString(ItemNBT.INVENTORY_CONTENT));
                        FuchsMCItem fuchsMCItem = new FuchsMCItem(getFuchsItemFromNMS(itemStack), itemStack);
                        fuchsMCItem.changeStringTag(ItemNBT.INVENTORY_CONTENT, createInventoryContent(removeCurrency(currency, to_remove, player, backpack_contents, backpack)));
                        itemStack = fuchsMCItem.getItemStack();
                        new_content[j] = itemStack;
                    }*/
                }
            }
        }
    }

    public static ItemStack getPlayerHead(OfflinePlayer player) {
        return getPlayerHead(player, new ItemStack(Material.PLAYER_HEAD, 1));
    }

    public static ItemStack getPlayerHead(OfflinePlayer player, ItemStack item) {
        SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
        skullMeta.displayName(Component.text(player.getName()));
        skullMeta.setOwningPlayer(player);
        item.setItemMeta(skullMeta);
        return item;
    }

    public static void openClosedFuchsItems(Player player) {
        int i = -1;
        ItemStack[] updated = player.getInventory().getContents();
        for (ItemStack content : updated) {
            i++;
            if (content != null && content.getType() != Material.AIR) {
                if (isFuchsItem(content)) {
                    FuchsItem fuchsItem = getFuchsItemFromNMS(content);
                    FuchsMCItem fuchsMCItem = new FuchsMCItem(fuchsItem, player, content);
                    if (!fuchsItem.staysOpen() && fuchsMCItem.getData().isOpen()) {
                        fuchsMCItem.getData().toggleOpen();
                        updated[i] = fuchsMCItem.getItemStack();
                    }
                }
            }
        }
        player.getInventory().setContents(updated);
    }

    public static ItemStack createEffectBook(FuchsEffect fuchsEffect, Player player) {
        EffectBookItem effectBookItem = ItemList.effectBook.copy();
        FuchsMCItem fuchsMCItem = new FuchsMCItem(effectBookItem, player, effectBookItem.createItem(player));
        FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
        generalData.setRarity(fuchsEffect.getRarity().getID());
        generalData.setCustomDisplayName("Effektbuch: " + fuchsEffect.getDisplayName().get(References.data.getPlayer(player)));
        generalData.setModeldata(fuchsEffect.getCustomModelData());
        generalData.setContainedEffect(fuchsEffect.getID());
        fuchsMCItem.generalData().set(generalData);
        return fuchsMCItem.getItemStack();
    }

    public static ItemStack createSpawnEgg(FuchsEntity<?> fuchsEntity, Player player) {
        EntitySpawnEggItem spawnEggItem = ItemList.entitySpawnEgg.copy();
        ItemStack itemStack = spawnEggItem.createItem(player);
        FuchsMCItem fuchsMCItem = new FuchsMCItem(spawnEggItem, player, itemStack);
        fuchsMCItem.changeStringTag(ItemNBT.CONTAINED_ENTITY, fuchsEntity.getID());
        FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
        generalData.setModeldata(fuchsEntity.getCustomModelData());
        generalData.setRarity(RarityList.unfindable.getID());
        generalData.setCustomDisplayName("Entity: " + fuchsEntity.getDisplayName().get(References.data.getPlayer(player)));
        fuchsMCItem.generalData().set(generalData);
        return fuchsMCItem.getItemStack();
    }

    public static ItemStack createSoundDisc(FuchsSound fuchsSound, Player player) {
        FuchsMCItem mcItem = new FuchsMCItem(ItemList.soundDisc.copy(), player);
        FuchsItemSpecialData specialData = mcItem.specialData().get();
        FuchsItemGeneralData generalData = mcItem.generalData().get();
        specialData.setSoundKey(fuchsSound.getKey().toString());
        generalData.setModeldata(fuchsSound.getCustomModelData());
        mcItem.generalData().set(generalData);
        mcItem.specialData().set(specialData);
        return mcItem.getItemStack();
    }

    public static ItemStack createBowProjectiles(FuchsProjectile projectile, Player player) {
        DefaultBowItem bowItem = ItemList.defaultBow.copy();
        ItemStack itemStack = bowItem.createItem(player);
        FuchsMCItem fuchsMCItem = new FuchsMCItem(bowItem, player, itemStack);
        FuchsItemGeneralData generalData = fuchsMCItem.generalData().get();
        FuchsItemProjectileData projectileData = fuchsMCItem.projectileData().get();
        generalData.setRarity(RarityList.unfindable.getID());
        projectileData.setProjectileId(projectile.getID());
        projectileData.setContainedProjectile(projectile.getID());
        generalData.setCustomDisplayName("Bogen mit Projektil: " + projectile.getDisplayName().get(References.data.getPlayer(player)));
        fuchsMCItem.generalData().set(generalData);
        fuchsMCItem.projectileData().set(projectileData);
        return fuchsMCItem.getItemStack();
    }

    public static FuchsMaterial getFuchsMaterial(Material material) {
        for (FuchsMaterial value : FuchsMaterial.values()) {
            if(value.getType().equals(material)) {
                return value;
            }
        }
        return FuchsMaterial.AIR;
    }

    public static String getLocationString(Location location) {
        return location.getWorld().getName() + ", x: " + location.getX()+ ", y: " + location.getY() + ", z: " + location.getZ();
    }

    @Deprecated
    public static File getResource(Class<?> clazz, String fileName){
        try {
            URL resource = clazz.getResource(fileName);
            return new File(resource.getFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new File("");
    }

    public static String getContentFromResource(Class<?> clazz, String resource) {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStreamReader streamReader = new InputStreamReader(clazz.getResourceAsStream(resource), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static Collection<String> getFileNamesResources(Class<?> clazz, String path) {
        Collection<String> array = new ArrayList<>();
        try {
            JarFile jarFile = new JarFile(new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath()));
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                final String name = entries.nextElement().getName();
                if (name.contains(path)) {
                    array.add(name);
                }
            }
            jarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }

    public static ArrayList<FuchsKneadItem> getKneadItems() {
        ArrayList<FuchsKneadItem> array = new ArrayList<>();
        for (FuchsRegistryObject<FuchsItem> value : FuchsRegistries.items.values()) {
            if(value.copy().isKneadItem() && value.copy() instanceof FuchsKneadItem) {
                array.add((FuchsKneadItem) value.copy());
            }
        }
        return array;
    }

    public static FuchsKneadItem getRandomKneadItem() {
        for (FuchsKneadItem kneadItem : getKneadItems()) {
            if(PapierFuchs.random.nextInt(kneadItem.getChance()) == 1) {
                return kneadItem;
            }
        }
        return getRandomKneadItem();
    }

    public static void updatePlayerName(Player player) {
        /*for(Player otherPlayer : PapierFuchs.INSTANCE.getServer().getOnlinePlayers()){
            EntityPlayer otherPlayerCraft = ((CraftPlayer) otherPlayer).getHandle();
            PlayerConnection playerConnection = otherPlayerCraft.playerConnection;
            EntityPlayer playerCraft = ((CraftPlayer) player).getHandle();
            PlayerConnection ownConnection = playerCraft.playerConnection;
            ownConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, playerCraft));
            PacketPlayOutPlayerInfo packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, playerCraft);
            packetPlayOutPlayerInfo.new PlayerInfoData(playerCraft.getProfile(), playerCraft.ping, playerCraft.playerInteractManager.getGameMode(), CraftChatMessage.fromString("Test " + playerCraft.displayName)[0]);
            ownConnection.sendPacket(packetPlayOutPlayerInfo);
            playerConnection.sendPacket(new PacketPlayOutEntityDestroy(player.getEntityId()));
            playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(otherPlayerCraft));
        }*/
    }

    public static FuchsItemGeneralData getGeneralDataFromItem(ItemStack itemStack) {
        if(itemStack.getItemMeta() != null) {
            return new FuchsMCItem(itemStack).generalData().get();
        }
        return new FuchsItemGeneralData();
    }

    public static boolean itemHasFuchsID(ItemStack itemStack) {
        FuchsItemGeneralData fuchsItemGeneralData = getGeneralDataFromItem(itemStack);
        if(fuchsItemGeneralData != null) {
            return !fuchsItemGeneralData.getId().equalsIgnoreCase("");
        }
        return false;
    }

    public static boolean isShovel(ItemStack itemStack) {
        if (itemStack != null) {
            switch (itemStack.getType()) {
                case STONE_SHOVEL, DIAMOND_SHOVEL, GOLDEN_SHOVEL, IRON_SHOVEL, WOODEN_SHOVEL, NETHERITE_SHOVEL -> {
                    return true;
                }
            }
        }
        return false;
    }


    @Deprecated
    public static boolean isStair(Material material) {
        switch (material) {
            case ACACIA_STAIRS, ANDESITE_STAIRS, STONE_STAIRS, BIRCH_STAIRS, BLACKSTONE_STAIRS, BRICK_STAIRS, STONE_BRICK_STAIRS, COBBLED_DEEPSLATE_STAIRS, SANDSTONE_STAIRS, COBBLESTONE_STAIRS,
                    CRIMSON_STAIRS, CUT_COPPER_STAIRS, DARK_OAK_STAIRS, DARK_PRISMARINE_STAIRS, DEEPSLATE_BRICK_STAIRS, DEEPSLATE_TILE_STAIRS, DIORITE_STAIRS, SMOOTH_QUARTZ_STAIRS, END_STONE_BRICK_STAIRS,
                    EXPOSED_CUT_COPPER_STAIRS, GRANITE_STAIRS-> {
                return true;
            }
        }
        return false;
    }
    public static boolean isShovelName(ItemStack itemStack) {
        if (itemStack != null) {
            return itemStack.getType().toString().toLowerCase().contains("shovel");
        }
        return false;
    }

    public static boolean isStairName(Material material) {
        return material.toString().toLowerCase().contains("stairs");
    }

    public static boolean isSlabName(Material material) {
        return material.toString().toLowerCase().contains("slab");
    }

    public static ItemStack applyEffectToItem(FuchsMCItem mcItem, ItemStack effectBook, FuchsEffect<?> effect, Player player) {
        FuchsItemGeneralData generalData = mcItem.generalData().get();
        if(generalData.hasEffect(effect)) {
            EffectItemData effectItemData = generalData.getEffectData(effect);
            effectItemData.setLevel((byte) (effectItemData.getLevel() + new FuchsMCItem(effectBook).generalData().get().getEffectData(effect).getLevel()));
            if(effectItemData.getLevel() > effectItemData.getMaxLevel()) {
                effectItemData.setLevel(effectItemData.getMaxLevel());
            }
        } else {
            generalData.addEffect(effect);
        }
        effect.onGeneralAdd(generalData, player);
        mcItem.generalData().set(generalData);
        return mcItem.getItemStack();
    }

    public static boolean fuchsRegistryExists(String id) {
        for (FuchsRegistry registry : FuchsRegistries.registries) {
            if(registry.fuchsPlugin.getRegistryID().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public static FuchsRegistry getRegistryById(String id) {
        for (FuchsRegistry registry : FuchsRegistries.registries) {
            if(registry.fuchsPlugin.getRegistryID().equalsIgnoreCase(id)) {
                return registry;
            }
        }
        return null;
    }

    @Nullable
    public static <T> T deserialize(byte[] bytes) {
        try {
            InputStream fis = new ByteArrayInputStream(bytes);
            ObjectInputStream o = new ObjectInputStream(fis);
            return (T) o.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
