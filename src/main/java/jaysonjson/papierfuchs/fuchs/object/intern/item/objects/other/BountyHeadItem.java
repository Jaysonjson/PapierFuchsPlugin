package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.other;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import org.bukkit.Material;

public class BountyHeadItem extends FuchsItem {

    public BountyHeadItem(String id) {
        super(id, Material.PLAYER_HEAD);
    }

   /* @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        if(player != null) {
            return super.createItem(player, FuchsUtility.getPlayerHead(player));
        }
        return super.createItem(stack);
    }*/
}
