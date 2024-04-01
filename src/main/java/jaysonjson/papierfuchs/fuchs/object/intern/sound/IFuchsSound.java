package jaysonjson.papierfuchs.fuchs.object.intern.sound;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public interface IFuchsSound {

    SoundType getSoundType();
    float defaultPitch();
    float defaultVolume();
    default void playSound(Player player) {
        playSound(player, player.getLocation());
    }
    default void playSound(Player player, Location location) {
        playSound(player.getWorld(), location);
    }
    void playSound(World world, Location location);
}
