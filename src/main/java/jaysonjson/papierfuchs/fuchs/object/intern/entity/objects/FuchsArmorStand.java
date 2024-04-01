package jaysonjson.papierfuchs.fuchs.object.intern.entity.objects;

import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EnumMoveType;
import net.minecraft.world.entity.decoration.EntityArmorStand;
import net.minecraft.world.level.World;
import net.minecraft.world.phys.Vec3D;
import org.jetbrains.annotations.NotNull;

public class FuchsArmorStand extends EntityArmorStand {

    public FuchsArmorStand(EntityTypes<? extends EntityArmorStand> type, World world) {
        super(type, world);
    }

    public FuchsArmorStand(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    public void g(@NotNull Vec3D vec3d) {
        if (!super.isNoGravity()) {
            super.g(vec3d);
        } else {
            move(EnumMoveType.c, super.getMot());
        }
    }
}
