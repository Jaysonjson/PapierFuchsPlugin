package jaysonjson.papierfuchs.object.effect.objects;

import jaysonjson.papierfuchs.object.effect.FuchsEffect;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BloodboundEffect extends FuchsEffect {

    public BloodboundEffect(String id) {
        super(id);
    }

    @Override
    public String getDisplayName() {
        return ChatColor.RED + "Blutgebunden";
    }

    public String getUser(NBTTagCompound tag) {
        return tag.getString(ItemNBT.BLOOD_BOUND_USER);
    }

    public void setUser(NBTTagCompound tag, Player player) {
        tag.setString(ItemNBT.BLOOD_BOUND_USER, player.getUniqueId().toString());
    }

}
