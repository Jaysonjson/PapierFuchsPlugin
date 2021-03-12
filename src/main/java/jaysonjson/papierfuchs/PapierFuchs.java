package jaysonjson.papierfuchs;

import jaysonjson.papierfuchs.commands.*;
import jaysonjson.papierfuchs.commands.area.AreaCommand;
import jaysonjson.papierfuchs.commands.area.CreateAreaCommand;
import jaysonjson.papierfuchs.commands.guild.CreateGuildCommand;
import jaysonjson.papierfuchs.commands.item.ItemCommand;
import jaysonjson.papierfuchs.commands.item.ItemIDCommand;
import jaysonjson.papierfuchs.data.FileHandler;
import jaysonjson.papierfuchs.events.ChatEvent;
import jaysonjson.papierfuchs.events.Smelting;
import jaysonjson.papierfuchs.events.WorldLoad;
import jaysonjson.papierfuchs.events.block.BlockBreak;
import jaysonjson.papierfuchs.events.block.BlockPlace;
import jaysonjson.papierfuchs.events.entity.EntityDamage;
import jaysonjson.papierfuchs.events.entity.EntityDeath;
import jaysonjson.papierfuchs.events.entity.MobSpawn;
import jaysonjson.papierfuchs.events.entity.player.*;
import jaysonjson.papierfuchs.events.inventory.ItemClick;
import jaysonjson.papierfuchs.events.item.*;
import jaysonjson.papierfuchs.npc.NPC;
import jaysonjson.papierfuchs.object.FuchsVanillaRecipes;
import jaysonjson.papierfuchs.object.currency.CurrencyList;
import jaysonjson.papierfuchs.object.effect.EffectList;
import jaysonjson.papierfuchs.object.entity.EntityList;
import jaysonjson.papierfuchs.object.gas.GasList;
import jaysonjson.papierfuchs.object.inventory.InventoryList;
import jaysonjson.papierfuchs.object.item.ItemList;
import jaysonjson.papierfuchs.object.language.LanguageList;
import jaysonjson.papierfuchs.object.liquid.LiquidList;
import jaysonjson.papierfuchs.object.npc.NPCList;
import jaysonjson.papierfuchs.object.rarity.RarityList;
import jaysonjson.papierfuchs.object.skillclass.SkillClassList;
import jaysonjson.papierfuchs.registry.FuchsRegistries;
import jaysonjson.papierfuchs.registry.FuchsRegistry;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class PapierFuchs extends JavaPlugin implements IFuchsPlugin {

    public static PapierFuchs INSTANCE;

    public static double version = 0.1;
    public PapierFuchs() {
        INSTANCE = this;
    }
    public boolean firstJoin = false;

    @Override
    public void onEnable() {
        try {
            FileHandler.createDirectories();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        References.loadServerData();
        //DataHandler.createGermanLangTest();
        FuchsRegistry fuchsRegistry = new FuchsRegistry(this);
        fuchsRegistry.register(LanguageList.class);
        fuchsRegistry.register(RarityList.class);
        fuchsRegistry.register(EffectList.class);
        fuchsRegistry.register(EntityList.class);
        fuchsRegistry.register(SkillClassList.class);
        fuchsRegistry.register(CurrencyList.class);
        fuchsRegistry.register(ItemList.class);
        fuchsRegistry.register(GasList.class);
        fuchsRegistry.register(LiquidList.class);
        fuchsRegistry.register(InventoryList.class);
        fuchsRegistry.register(NPCList.class);
        FuchsRegistries.sort();
        References.reloadDrops();
        References.reloadCraftings();
        References.reloadAreas();
        NPC.loadNPCS();
        registerEvents(
                new PlayerJoin(),
                new PlayerDeath(),
                new PlayerRespawn(),
                new PlayerMove(),
                new MobSpawn(),
                new CraftItem(),
                new BlockBreak(),
                new BlockPlace(),
                new DropItem(),
                new EntityDeath(),
                new ItemClick(),
                new ChatEvent(),
                new ItemUse(),
                new EntityDamage(),
                new BanPlayer(),
                new ItemPickup(),
                new ItemDespawn(),
                new Smelting(),
                new PlayerSleep(),
                new WorldLoad(),
                new PlayerInteractEntity(),
                new EnchantItem(),
                new PlayerLeave()
        );

        registerCommands(
                new SpigotCommand("areas", new AreaCommand()),
                new SpigotCommand("sethealth", new SetHealthCommand()),
                new SpigotCommand("area", new CreateAreaCommand()),
                new SpigotCommand("guild", new CreateGuildCommand()),
                new SpigotCommand("items", new ItemCommand()),
                new SpigotCommand("npc", new CreateNPCCommand()),
                new SpigotCommand("gba", new SetGuildBannerCommand()),
                new SpigotCommand("gmc", new GamemodeCommand()),
                new SpigotCommand("gms", new GamemodeCommand()),
                new SpigotCommand("gma", new GamemodeCommand()),
                new SpigotCommand("gmsp", new GamemodeCommand()),
                new SpigotCommand("discordlink", new DiscordLinkCommand()),
                new SpigotCommand("itemids", new ItemIDCommand()),
                new SpigotCommand("classlist", new ClassListCommand()),
                new SpigotCommand("fuchs", new FuchsCommand()),
                new SpigotCommand("general_crafting", new CraftingInventoryCommand()),
                new SpigotCommand("armorstandmetadata", new ArmorStandMetadata())
        );

        FuchsVanillaRecipes.addRecipes();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            References.data.savePlayers();
            References.data.saveServer();
        }, 0L, 20L * 300);

    }

    @Override
    public void onDisable() {
        References.data.savePlayers();
        References.data.saveServer();
    }

    public static void registerEvents(Listener... listener) {
        for (Listener listener1 : listener) {
            Bukkit.getPluginManager().registerEvents(listener1, INSTANCE);
        }
    }

    public void registerCommands(SpigotCommand... commands) {
        for(SpigotCommand command : commands) {
            this.getCommand(command.getCommand()).setExecutor(command.getCommandExecutor());
        }
    }

    @Override
    @Deprecated
    public String getPluginID() {
        return "papierfuchs";
    }
}
