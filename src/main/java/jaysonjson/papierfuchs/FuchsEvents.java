package jaysonjson.papierfuchs;

import jaysonjson.papierfuchs.fuchs.event.FuchsEvent;
import jaysonjson.papierfuchs.paper.events.ChatEvent;
import jaysonjson.papierfuchs.paper.events.Smelting;
import jaysonjson.papierfuchs.paper.events.WorldLoad;
import jaysonjson.papierfuchs.paper.events.block.BlockEvents;
import jaysonjson.papierfuchs.paper.events.entity.*;
import jaysonjson.papierfuchs.paper.events.entity.player.*;
import jaysonjson.papierfuchs.paper.events.inventory.ItemClick;
import jaysonjson.papierfuchs.paper.events.item.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class FuchsEvents {

    public static void register(FuchsEvent<?> event) {
        PapierFuchs.INSTANCE.eventHandler.getEvents().add(event);
    }

    protected static void registerPlugin() {
        registerEvents(
                new WorldLoad(),
                new PlayerJoin(),
                new PlayerDeath(),
                new PlayerRespawn(),
                new PlayerMove(),
                new MobSpawn(),
                new CraftItem(),
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
                new PlayerInteractEntity(),
                new EnchantItem(),
                new PlayerLeave(),
                new EntityTarget(),
                new PlayerInteract(),
                new PlayerEat(),
                new ProjectileHit(),
                new PlayerSwapItem(),
                new BowShootEvent(),
                new ProjectileThrowEvent(),
                new BlockEvents(),
                new PlayerExitVehicle()
        );
    }

    protected static void registerFuchs() {

    }

    private static void registerEvents(Listener... listener) {
        for (Listener listener1 : listener) {
            Bukkit.getPluginManager().registerEvents(listener1, PapierFuchs.INSTANCE);
        }
    }
}
