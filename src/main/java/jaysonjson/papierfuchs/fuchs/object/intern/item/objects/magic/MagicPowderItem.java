package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.magic;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemMagicData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class MagicPowderItem extends FuchsItem {

    public MagicType magicType;
    public int modelData;
    public MagicPowderItem(String id, Material material, IItemUseType itemUseType, MagicType magicType, int modelData) {
        super(id, material, itemUseType);
        this.magicType = magicType;
        this.modelData = modelData;
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        FuchsItemData oItem = new FuchsItemData(this, player, stack);

        oItem.addToLore(ChatColor.RESET + "" + ChatColor.GRAY + "Verstärkt das Zauber-Attribut von " + magicType.getColor() + magicType.getName() + ChatColor.GRAY + " Gegenständen");
        oItem.setItem(magicType.getColor() + magicType.getName() + ChatColor.RESET + "-Zauber Staub");
        return oItem.item;
    }
    @Override
    public void setDefaultPersistentData(FuchsMCItem fuchsMCItem, @Nullable Player player) {
        FuchsItemMagicData fuchsItemMagicData = fuchsMCItem.magicData().get();
        fuchsItemMagicData.setType(magicType.getNbt());
        fuchsMCItem.magicData().set(fuchsItemMagicData);
        super.setDefaultPersistentData(fuchsMCItem, player);
    }

    @Override
    public int getCustomModelData() {
        return modelData;
    }
}
