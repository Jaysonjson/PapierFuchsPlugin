package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;

public class FuchsArmorItem extends FuchsItem {

    FuchsLanguageString fuchsLanguageString;
    double armor;
    double toughness;
    int modeldata;
    int armordata;
    public FuchsArmorItem(String id, Material material, FuchsLanguageString fuchsLanguageString, double armor, double toughness, int modeldata, int armordata) {
        super(id, material, UseTypeList.ARMOR.copy());
        this.armor = armor;
        this.toughness = toughness;
        this.modeldata = modeldata;
        this.armordata = armordata;
        this.fuchsLanguageString = fuchsLanguageString;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        tag.setInt(ItemNBT.CUSTOM_ARMOR_DATA, armordata);
        return super.getTag(tag);
    }

    @Override
    public double getArmorToughness() {
        return toughness;
    }

    @Override
    public int getArmorModel() {
        return armordata;
    }

    @Override
    public int getCustomModelData() {
        return modeldata;
    }

    @Override
    public double getArmor() {
        return armor;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return fuchsLanguageString;
    }

    @Override
    public boolean isArmor() {
        return true;
    }

    @Override
    public boolean stackAble() {
        return false;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.armor.copy() };
    }

}
