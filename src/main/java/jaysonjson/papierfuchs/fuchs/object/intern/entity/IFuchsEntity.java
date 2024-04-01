package jaysonjson.papierfuchs.fuchs.object.intern.entity;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;


public interface IFuchsEntity<T> {

	/*Entity createEntity(Player player);
	EntityTypes<?> getEntityType();

	default Entity createEntity() {
		return createEntity(null);
	}

	int getMaxHealth();*/

    T create(World world, Player player, Location location);
    default T create(World world, Location location) {
        return create(world, null, location);
    }
    default T create(Player player) {
        return create(((CraftWorld) player.getWorld()).getHandle(), player, player.getLocation());
    }
    default T create(Player player, Location location) {
        return create(((CraftWorld) player.getWorld()).getHandle(), player, location);
    }

    default void spawn(Player player, Location location) {
        WorldServer worldServer = ((CraftWorld)player.getWorld()).getHandle();
        worldServer.addEntity((Entity) create(worldServer, player, location));
    }

    FuchsRegistryObject<? extends FuchsItem> getSpawnEgg();

    default boolean shouldHaveSpawnegg() {
        return true;
    }

}
