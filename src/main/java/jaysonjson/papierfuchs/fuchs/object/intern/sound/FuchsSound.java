package jaysonjson.papierfuchs.fuchs.object.intern.sound;

import jaysonjson.papierfuchs.fuchs.object.IModelData;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import org.bukkit.Location;
import org.bukkit.World;

public class FuchsSound extends FuchsObject implements IFuchsSound, IModelData {

    public FuchsSound(String id) {
        super(id, RegistryType.SOUND);
    }

    @Override
    public void playSound(World world, Location location) {
        world.playSound(location, getRealID(), defaultPitch(), defaultVolume());
    }

    @Override
    public SoundType getSoundType() {
        return SoundType.OTHER;
    }

    @Override
    public float defaultPitch() {
        return 1;
    }

    @Override
    public float defaultVolume() {
        return 1;
    }
}
