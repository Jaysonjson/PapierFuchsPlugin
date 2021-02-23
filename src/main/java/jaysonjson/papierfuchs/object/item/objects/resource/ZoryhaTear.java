package jaysonjson.papierfuchs.object.item.objects.resource;

import jaysonjson.papierfuchs.data.DataHandler;
import jaysonjson.papierfuchs.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.FuchsItemData;
import jaysonjson.papierfuchs.object.item.ItemNBT;
import jaysonjson.papierfuchs.object.item.interfaces.IItemUseType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ZoryhaTear extends FuchsItem {

    public ZoryhaTear(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        if(player != null) {
            FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(player.getUniqueId());
            fuchsItemData.setItem(fuchsPlayer.getLanguage().getContent().CONTENT.get("zoryha_tear"));
        } else {
            fuchsItemData.setItem(ChatColor.AQUA + "Zoryha's Träne");
        }
        return fuchsItemData.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public int getCustomModelData() {
        return 30;
    }
}
