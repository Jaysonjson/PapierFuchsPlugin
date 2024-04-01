package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FallBackItem extends FuchsItem {
    public FallBackItem() {
        super("fallback", Material.DIRT, UseTypeList.OTHER.copy());
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData fuchsItemData = new FuchsItemData(this, player, stack);
        fuchsItemData.addToLore("Irgentein Fehler ist passiert, lol.");
        return fuchsItemData.setItem();
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.unfindable.copy();
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Fallback");
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        return tag;
    }
}
