package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.useable;

import jaysonjson.papierfuchs.fuchs.utility.References;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
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
        FuchsItemData oItem = new FuchsItemData(this, player, stack);

        oItem.setItem(ChatColor.GRAY + "Alkohol Tester");
        return oItem.item;
    }

    @Override
    public void onEntityInteract(PlayerInteractEntityEvent event) {
        if(event.getRightClicked() instanceof Player) {
            Player clickedPlayer = (Player) event.getRightClicked();
            FuchsPlayer fuchsPlayer = References.data.getPlayer(clickedPlayer.getUniqueId());
            event.getPlayer().sendMessage(fuchsPlayer.getSpecial().getAlcohol() + "");
        }
    }



}
