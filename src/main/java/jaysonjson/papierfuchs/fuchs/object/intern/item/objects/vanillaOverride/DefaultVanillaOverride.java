package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.vanillaOverride;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import org.bukkit.Material;

public class DefaultVanillaOverride extends FuchsItem {

    float sellValue;
    float buyValue;
    public DefaultVanillaOverride(String id, Material material, float sellValue, float buyValue) {
        super(id, material, UseTypeList.NONE.copy());
        this.sellValue = sellValue;
        this.buyValue = buyValue;
    }

    public DefaultVanillaOverride(String id, Material material, float sellValue) {
        super(id, material, UseTypeList.NONE.copy());
        this.sellValue = sellValue;
        this.buyValue = buyMaterial(sellValue);
    }

    public static float buyMaterial(float material) {
        return material + (material / 1.2f) + (material * 2.7f);
    }


    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.none.copy();
    }

    @Override
    public float getDefaultSellValue() {
        return sellValue;
    }

    @Override
    public float getDefaultBuyValue() {
        return buyValue;
    }

    @Override
    public boolean canSmeltMinecraft() {
        return true;
    }

    @Override
    public boolean canCraftMinecraft() {
        return true;
    }

    @Override
    public boolean hasDisplayName() {
        return false;
    }

    @Override
    public boolean isVanillaOverride() {
        return true;
    }

    @Override
    public boolean showInItemList() {
        return false;
    }
}
