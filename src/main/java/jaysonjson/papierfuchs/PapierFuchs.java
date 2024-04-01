package jaysonjson.papierfuchs;

import jaysonjson.papierfuchs.fuchs.event.EventHandler;
import jaysonjson.papierfuchs.fuchs.io.data.FileHandler;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.ByteFuchsWorld;
import jaysonjson.papierfuchs.fuchs.io.data.server.data.FuchsWorld;
import jaysonjson.papierfuchs.fuchs.object.FuchsVanillaRecipes;
import jaysonjson.papierfuchs.fuchs.object.extern.custom.CustomObjectHandler;
import jaysonjson.papierfuchs.fuchs.object.intern.block.BlockList;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.currency.CurrencyList;
import jaysonjson.papierfuchs.fuchs.object.intern.effect.EffectList;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.EntityList;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.GasList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.InventoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.*;
import jaysonjson.papierfuchs.fuchs.object.intern.jobs.JobList;
import jaysonjson.papierfuchs.fuchs.object.intern.language.LanguageList;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.LiquidList;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.NPCList;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.ProjectileList;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.skillclass.SkillClassList;
import jaysonjson.papierfuchs.fuchs.object.intern.sound.SoundList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsPlugin;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRecipes;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.fuchs.utility.FuchsAnsi;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.old.npc.NPC;
import org.bukkit.*;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Properties;
import java.util.Random;
import java.util.UUID;

public final class PapierFuchs extends FuchsPlugin {

    //load: STARTUP -> yml

    public static PapierFuchs INSTANCE;

    private static String VERSION = "unknown";
    public static final String STAGE = "pAx";
    public static final String MC = "1.17.1";

    private static String VERSION_S = STAGE + VERSION + "m" + MC;
    public PapierFuchs() {
        INSTANCE = this;
        try {
            Properties properties = new Properties();
            properties.load(PapierFuchs.class.getResourceAsStream("/pom.properties"));
            VERSION = properties.getProperty("version");
            VERSION_S = STAGE + VERSION + "m" + MC;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean firstJoin = false;
    public final EventHandler eventHandler = new EventHandler();
    public World cooldownWorld = getServer().getWorld("cooldown");
    public static Random random = new Random();

    @Override
    public void onEnable() {
        new WorldCreator("cooldown").environment(World.Environment.NORMAL).type(WorldType.FLAT).createWorld();
        cooldownWorld = getServer().getWorld("cooldown");
        FileHandler.createDirectories();
        FuchsEvents.registerPlugin();
        FuchsEvents.registerFuchs();
        fuchsRegistry.register(UseTypeList.class);
        fuchsRegistry.register(RarityList.class);
        fuchsRegistry.register(EffectList.class);
        fuchsRegistry.register(EntityList.class);
        fuchsRegistry.register(SkillClassList.class);
        fuchsRegistry.register(CurrencyList.class);
        fuchsRegistry.register(ItemList.class);
        fuchsRegistry.register(ItemCategoryList.class);
        fuchsRegistry.register(VanillaItemList.class);
        fuchsRegistry.register(CurrencyItemList.class);
        fuchsRegistry.register(KneadItemList.class);
        fuchsRegistry.register(ArmorList.class);
        fuchsRegistry.register(PetItemList.class);
        fuchsRegistry.register(CosmeticItemList.class);
        fuchsRegistry.register(GasList.class);
        fuchsRegistry.register(LiquidList.class);
        fuchsRegistry.register(InventoryList.class);
        fuchsRegistry.register(NPCList.class);
        fuchsRegistry.register(SoundList.class);
        fuchsRegistry.register(JobList.class);
        fuchsRegistry.register(ProjectileList.class);
        fuchsRegistry.register(BlockList.class);
        fuchsRegistry.register(LanguageList.class);

        CustomObjectHandler.registerItems(fuchsRegistry);
        CustomObjectHandler.registerCurrencies(fuchsRegistry);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            References.loadData();
            //FuchsRegistries.sort();
            References.reload();
            NPC.loadNPCS();
            FuchsCommands.register();
            FuchsVanillaRecipes.addRecipes();
            FuchsRecipes.loadSmithing();
            FuchsRecipes.loadWorkbench();
            FuchsRegistries.CREATE(INSTANCE);
            Bukkit.getScheduler().scheduleSyncRepeatingTask(INSTANCE, () -> {
                References.data.save();
                //Bukkit.broadcastMessage("Server gespeichert!");
                FuchsLog.log(FuchsAnsi.GREEN + "Gespeichert!");
            }, FuchsUtility.tickToMinute(5), FuchsUtility.tickToMinute(30));

            for (World world : Bukkit.getWorlds()) {
                if(!References.data.worlds.containsKey(world.getUID())) {
                    FuchsWorld fuchsWorld = new FuchsWorld();
                    fuchsWorld.setWorldUuid(world.getUID());
                    FuchsLog.log(FuchsAnsi.GREEN + "FuchsWelt " + FuchsAnsi.CYAN + fuchsWorld.getWorldUuid() + FuchsAnsi.GREEN + " erstellt!");
                    References.data.saveWorld(fuchsWorld);
                }
                if(!References.data.byteWorlds.containsKey(world.getUID())) {
                    ByteFuchsWorld byteFuchsWorld = new ByteFuchsWorld();
                    byteFuchsWorld.setWorldUuid(world.getUID());
                    FuchsLog.log(FuchsAnsi.GREEN + "ByteFuchsWelt " + FuchsAnsi.CYAN + byteFuchsWorld.getWorldUuid() + FuchsAnsi.GREEN + " erstellt!");
                    References.data.saveByteWorld(byteFuchsWorld);
                }
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    for (FuchsWorld value : References.data.worlds.values()) {
                        //                    value.checkPlants();
                        for (UUID plant : value.getPlants()) {
                            value.increasePlantGrow(plant, 1);
                        }
                    }
                }
            }.runTaskTimer(PapierFuchs.INSTANCE, 0L, FuchsUtility.tickToMinute(1));

            FuchsRegistries.itemGroup.create();

            if(!References.data.papierFuchs.getVersion().equals(VERSION_S) && !References.data.papierFuchs.getVersion().equals("")) {
                FuchsLog.log(ChatColor.GOLD + "Neue PapierFuchs Version entdeckt! Derzeitig: " + References.data.papierFuchs.getVersion() + " | Neu: " + VERSION_S);
            }
            References.data.papierFuchs.setVersion(VERSION_S);
            References.data.savePapierFuchs();
            for (FuchsRegistry registry : FuchsRegistries.registries) {
                registry.createArray();
            }
        });
    }

    @Override
    public void onDisable() {
        /*for (FuchsWorld value : References.data.worlds.values()) {
            value.checkPlants();
        }*/
        References.data.save();
        for (UUID pet : References.pets) {
            for (World world : getServer().getWorlds()) {
                if(world.getEntity(pet) != null) {
                    world.getEntity(pet).remove();
                }
            }
        }
    }

    public static String getVersion() {
        return VERSION;
    }

    public static String getBuild() {
        return VERSION_S;
    }

    @Override
    public String getRegistryID() {
        return "papierfuchs";
    }

    @Override
    public FuchsItem getIcon() {
        return ItemList.gasContainer.copy();
    }
}
