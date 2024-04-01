package jaysonjson.papierfuchs.fuchs.object.intern.entity.objects;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.EntityMetaData;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.cosmetic.FuchsPetItem;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.minecraft.core.Vector3f;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import net.minecraft.world.level.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.Instant;

public class PetEntityData extends EntityArmorStand {

    Player player;
    FuchsPlayer fuchsPlayer;
    public PetEntityData(World world, Player player, FuchsEntity<PetEntityData> fuchsEntity) {
        super(EntityTypes.c, world);
        this.player = player;
        fuchsPlayer = References.data.getPlayer(player.getUniqueId());
        if(FuchsUtility.itemIDExists(fuchsPlayer.getCosmetic().getPet().getCurrent())) {
            setArms(true);
            setInvulnerable(true);
            setRightArmPose(new Vector3f(0f, 0f, 0f));
            setLeftArmPose(new Vector3f(0f, 0f, 0f));
            setMarker(true);
            setInvisible(true);
            FuchsItem petItem = FuchsUtility.getFuchsItemByID(fuchsPlayer.getCosmetic().getPet().getCurrent());
            ((ArmorStand) getBukkitEntity()).setItem(petItem.getItemPlaceType().toEquipment(), petItem.createItem());
            FuchsUtility.addEntityMetadata(getBukkitEntity(), EntityMetaData.IS_PET, true);
            References.pets.add(getUniqueID());
            fuchsPlayer.getCosmetic().getPet().setEntity(getUniqueID());
            //final Instant[] before = {Instant.now()};
            new BukkitRunnable() {
                @Override
                public void run() {
                    //Instant after = Instant.now();
                    //System.out.println(Duration.between(before[0], after).toMillis());
                    //before[0] = Instant.now();
                    getBukkitEntity().teleport(player.getLocation().subtract(player.getEyeLocation().getDirection().multiply(0.4)));
                    if (!player.isOnline()) {
                        killEntity();
                        References.pets.remove(getUniqueID());
                        fuchsPlayer.getCosmetic().getPet().setEntity(null);
                        cancel();
                    }
                }
            }.runTaskTimer(PapierFuchs.INSTANCE, 0L, 1L);
            if(petItem instanceof FuchsPetItem defaultPetItem) {
                defaultPetItem.onCreate(world, player);
            }
        } else {
            killEntity();
        }
    }
}
