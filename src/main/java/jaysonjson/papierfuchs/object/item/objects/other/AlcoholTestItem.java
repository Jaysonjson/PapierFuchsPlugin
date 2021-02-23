package jaysonjson.papierfuchs.object.item.objects.other;

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
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class AlcoholTestItem extends FuchsItem {

    public AlcoholTestItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player);

        oItem.setItem(ChatColor.GRAY + "Alkohol Tester");
        return oItem.item;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setBoolean(ItemNBT.CAN_CRAFT, true);
        tag.setBoolean(ItemNBT.CAN_CRAFT_MINECRAFT, false);
        return tag;
    }

    @Override
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        if(event.getRightClicked() instanceof Player) {
            Player clickedPlayer = (Player) event.getRightClicked();
            FuchsPlayer fuchsPlayer = DataHandler.loadPlayer(clickedPlayer.getUniqueId());
            event.getPlayer().sendMessage(fuchsPlayer.getPlayerSpecial().getAlcohol() + "");
        }
    }
}
