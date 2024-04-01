package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.cosmetic;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemPlaceType;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.utility.References;
import net.kyori.adventure.text.Component;
import net.minecraft.world.level.World;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class FuchsPetItem extends FuchsItem {

    int modeldata;
    FuchsLanguageString fuchsLanguageString;
    FuchsRarity rarity;
    ItemPlaceType placeType = ItemPlaceType.RIGHT_ARM;
    public FuchsPetItem(String id, Material material, int modeldata, FuchsLanguageString fuchsLanguageString, FuchsRarity rarity, ItemPlaceType placeType) {
        super(id, material, UseTypeList.COSMETIC.copy());
        this.modeldata = modeldata;
        this.fuchsLanguageString = fuchsLanguageString;
        this.rarity = rarity;
        this.placeType = placeType;
    }

    public FuchsPetItem(String id, Material material, int modeldata, FuchsLanguageString fuchsLanguageString, FuchsRarity rarity) {
        super(id, material, UseTypeList.COSMETIC.copy());
        this.modeldata = modeldata;
        this.fuchsLanguageString = fuchsLanguageString;
        this.rarity = rarity;
    }

    @Override
    public ItemPlaceType getItemPlaceType() {
        return placeType;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.setItem();
        return fuchsItemData.item;
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return rarity;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return fuchsLanguageString;
    }

    @Override
    public int getCustomModelData() {
        return modeldata;
    }

    @Override
    public boolean isPetItem() {
        return true;
    }

    @Override
    public void onItemPickup(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player player) {
            Item item = event.getItem();
            ItemStack itemStack = item.getItemStack();
            FuchsPlayer fuchsPlayer = References.data.getPlayer(player.getUniqueId());
            if(!fuchsPlayer.getCosmetic().getPet().hasPetUnlocked(getID())) {
                fuchsPlayer.getCosmetic().getPet().unlockPet(getID());
                player.sendActionBar(Component.text(ChatColor.LIGHT_PURPLE + "Haustier " + ChatColor.AQUA + getDisplayName().get(fuchsPlayer) + ChatColor.LIGHT_PURPLE + " freigeschaltet!"));
                itemStack.setAmount(itemStack.getAmount() - 1);
            }
        }
    }

    public void onCreate(World world, Player player) {

    }

    public void onDelete(Player player) {

    }
}
