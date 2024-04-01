package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.cosmetic;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class DefaultHatItem extends FuchsItem {

    FuchsLanguageString displayName;
    int modeldata;
    public DefaultHatItem(String id, Material material, int modeldata, FuchsLanguageString displayName) {
        super(id, material, UseTypeList.COSMETIC.copy());
        this.modeldata = modeldata;
        this.displayName = displayName;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.setItem();
        return fuchsItemData.item;
    }

    @Override
    public boolean canPutOnHead() {
        return true;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return displayName;
    }

    @Override
    public int getCustomModelData() {
        return modeldata;
    }


    @Override
    public void onItemPickup(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Item item = event.getItem();
            ItemStack itemStack = item.getItemStack();
            FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
            if(!fuchsPlayer.getCosmetic().getHat().hasHatUnlocked(getID())) {
                fuchsPlayer.getCosmetic().getHat().unlockHat(getID());
                player.sendActionBar(Component.text(ChatColor.LIGHT_PURPLE + "Kopfbedeckung " + ChatColor.AQUA + getDisplayName().get(fuchsPlayer) + ChatColor.LIGHT_PURPLE + " freigeschaltet!"));
                itemStack.setAmount(itemStack.getAmount() - 1);
            }
        }
    }
}
