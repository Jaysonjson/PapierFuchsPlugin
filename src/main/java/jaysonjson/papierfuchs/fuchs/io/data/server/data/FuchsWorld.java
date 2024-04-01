package jaysonjson.papierfuchs.fuchs.io.data.server.data;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemPlantData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPCData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.Nullable;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class FuchsWorld implements Serializable {

	@Serial
	private static transient final long serialVersionUID = 0L;
	
	private ArrayList<UUID> plants = new ArrayList<>();
    private ArrayList<FuchsNPCData> npcs = new ArrayList<>();
    private UUID worldUuid;

    public ArrayList<UUID> getPlants() {
        return plants;
    }

    public void setPlants(ArrayList<UUID> plants) {
        this.plants = plants;
    }

    public void addPlant(UUID plant) {
        if(!getPlants().contains(plant)) {
            getPlants().add(plant);
        }
    }

    public void addPlant(ArmorStand armorStand) {
        addPlant(armorStand.getUniqueId());
    }

    @Nullable
    public ArmorStand getPlant(World world, UUID uuid) {
        if(world.getEntity(uuid) != null) {
            if (world.getEntity(uuid) instanceof ArmorStand armorStand) {
                return armorStand;
            }
        }
        //removePlant(uuid);
        return null;
    }

    public void checkPlants() {
        ArrayList<UUID> toRemove = new ArrayList<>();
        for (UUID plant : getPlants()) {
            if(getPlant(plant) == null) {
                toRemove.add(plant);
            }
        }
        for (UUID uuid : toRemove) {
            getPlants().remove(uuid);
        }
    }

    @Nullable
    public ArmorStand getPlant(UUID uuid) {
        if(getWorldUuid() != null) {
            if (Bukkit.getWorld(getWorldUuid()) != null) {
                return getPlant(Bukkit.getWorld(getWorldUuid()), uuid);
            }
        }
        return null;
    }

    public void removePlant(UUID uuid) {
        getPlants().remove(uuid);
    }

    @Nullable
    public UUID getWorldUuid() {
        return worldUuid;
    }

    public void setWorldUuid(UUID worldUuid) {
        this.worldUuid = worldUuid;
    }

    public void increasePlantGrow(UUID plant, float amount) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(PapierFuchs.INSTANCE, () -> {
            if (getPlant(plant) != null) {
                ArmorStand stand = getPlant(plant);
                FuchsMCItem mcItem = new FuchsMCItem(stand.getItem(EquipmentSlot.HEAD));
                FuchsItemPlantData plantData = mcItem.plantData().get();
                FuchsItemGeneralData generalData = mcItem.generalData().get();
                if (!plantData.isFinished() && plantData.getCurrentGrow() < plantData.getGrowTime()) {
                    plantData.setCurrentGrow(plantData.getCurrentGrow() + amount + PapierFuchs.random.nextDouble());
                } else {
                    generalData.setModeldata(plantData.getFinishedModelData());
                    plantData.setFinished(true);
                }
                mcItem.generalData().set(generalData);
                mcItem.plantData().set(plantData);
                stand.setItem(EquipmentSlot.HEAD, mcItem.getItemStack());
            }
        });
    }

    public ArrayList<FuchsNPCData> getNpcs() {
        return npcs;
    }
}
