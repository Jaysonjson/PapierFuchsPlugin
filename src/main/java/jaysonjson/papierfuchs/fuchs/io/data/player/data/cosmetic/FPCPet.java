package jaysonjson.papierfuchs.fuchs.io.data.player.data.cosmetic;

import jaysonjson.papierfuchs.fuchs.object.intern.entity.EntityList;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class FPCPet {
    private final ArrayList<String> unlocked_pets = new ArrayList<>();
    private String current = "";
    private transient UUID entity;
    public ArrayList<String> getUnlockedPets() {
        return unlocked_pets;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public UUID getEntity() {
        return entity;
    }

    public void setEntity(UUID entity) {
        this.entity = entity;
    }

    public void spawnPet(World world, Player player) {
        Entity entity = EntityList.PET.copy().create(FuchsUtility.toServerWorld(player.getWorld()), player, player.getLocation());
        world.addEntity(entity);
        setEntity(entity.getUniqueID());
    }

    public void unlockPet(String id) {
        if(!hasPetUnlocked(id)) {
            unlocked_pets.add(id);
        }
    }

    public boolean hasPetUnlocked(String id) {
        return unlocked_pets.contains(id);
    }
}
