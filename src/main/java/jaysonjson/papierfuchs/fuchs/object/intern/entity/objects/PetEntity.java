package jaysonjson.papierfuchs.fuchs.object.intern.entity.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PetEntity extends FuchsEntity<PetEntityData> {

    public PetEntity(String id) {
        super(id);
    }

    @Override
    public PetEntityData create(World world, Player player, Location location) {
        return new PetEntityData(world, player, this);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString("Haustier");
    }

    @Override
    public boolean delayed() {
        return false;
    }

    @Override
    public int getCustomModelData() {
        return 51;
    }

    @Override
    public boolean shouldHaveSpawnegg() {
        return false;
    }
}
