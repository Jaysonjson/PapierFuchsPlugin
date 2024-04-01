package jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGasData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemGeneralData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemLiquidData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemToolData;
import jaysonjson.papierfuchs.fuchs.object.intern.gas.FuchsGas;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemNBT;
import jaysonjson.papierfuchs.fuchs.object.intern.liquid.FuchsLiquid;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import org.jetbrains.annotations.Nullable;

public class FuchsMCItemData {

    private final FuchsMCItem item;
    public FuchsMCItemData(FuchsMCItem item) {
        this.item = item;
    }


    public String getLiquidID() {
        return item.liquidData().get().getLiquid();
    }

    @Nullable
    public FuchsLiquid getLiquid() {
        return FuchsUtility.getLiquidByID(getLiquidID());
    }

    public void setLiquidID(String id) {
        FuchsItemLiquidData fuchsItemLiquidData = item.liquidData().get();
        fuchsItemLiquidData.setLiquid(id);
        item.liquidData().set(fuchsItemLiquidData);
    }

    public String getGasID() {
        return item.gasData().get().getGas();
    }

    @Nullable
    public FuchsGas getGas() {
        return FuchsUtility.getGasByID(getGasID());
    }

    public void setGasID(String id) {
        FuchsItemGasData fuchsItemGasData = item.gasData().get();
        fuchsItemGasData.setGas(id);
        item.gasData().set(fuchsItemGasData);
    }

    public double getLiquidAmount() {
        return item.liquidData().get().getAmount();
    }

    public double getGasAmount() {
        return item.gasData().get().getAmount();
    }

    public void setLiquidAmount(float amount) {
       FuchsItemLiquidData fuchsItemLiquidData = item.liquidData().get();
       fuchsItemLiquidData.setAmount(amount);
       item.liquidData().set(fuchsItemLiquidData);
    }

    public void setGasAmount(float amount) {
        FuchsItemGasData fuchsItemGasData = item.gasData().get();
        fuchsItemGasData.setAmount(amount);
        item.gasData().set(fuchsItemGasData);
    }

    public boolean isCharged() {
        return item.getBooleanFromTag(ItemNBT.CHARGED);
    }

    public void setCharged(boolean value) {
        item.changeBooleanTag(ItemNBT.CHARGED, value);
    }

    public boolean isOpen() {
        return item.getBooleanFromTag(ItemNBT.IS_OPEN);
    }

    public void setOpen(boolean value) {
        item.changeBooleanTag(ItemNBT.IS_OPEN, value);
    }

    public void toggleCharged() {
        setCharged(!isCharged());
    }

    public void toggleOpen() {
        setOpen(!isOpen());
    }

    public String getRarityString() {
        return item.generalData().get().getRarity();
    }

    public void setRarity(String rarity) {
        FuchsItemGeneralData fuchsItemGeneralData = item.generalData().get();
        fuchsItemGeneralData.setRarity(rarity);
        item.generalData().set(fuchsItemGeneralData);
    }

    public void setRarity(FuchsRarity fuchsRarity) {
        setRarity(fuchsRarity.getID());
    }

    public int getDurability() {
        return item.toolData().get().getDurability();
    }

    public void setDurability(int value) {
        FuchsItemToolData fuchsItemToolData = item.toolData().get();
        fuchsItemToolData.setDurability(value);
        item.toolData().set(fuchsItemToolData);
    }

    public void increaseDurability(int value) {
        setDurability(getDurability() + value);
    }

    public void increaseDurability() {
        increaseDurability(1);
    }

    public void decreaseDurability(int value) {
        increaseDurability(-value);
    }

    public void decreaseDurability() {
        decreaseDurability(1);
    }

/*
    @Deprecated
    public int getDurabilityNBT() {
        return item.getIntFromTag(ItemNBT.ITEM_DURABILITY);
    }

    @Deprecated
    public void setDurabilityNBT(int value) {
        item.changeIntTag(ItemNBT.ITEM_DURABILITY, value);
    }
*/
   /* @Deprecated
    public void increaseDurabilityNBT(int value) {
        //item.changeIntTag(ItemNBT.ITEM_DURABILITY, getDurability() + value);
        item.getItemStack().getItemMeta().getPersistentDataContainer().set(ItemPersistentData.ITEM_DURABILITY, PersistentDataType.INTEGER, getDurability() + value);
    }

    @Deprecated
    public void increaseDurabilityNBT() {
        increaseDurability(1);
    }

    @Deprecated
    public void decreaseDurabilityNBT(int value) {
        increaseDurability(-value);
    }

    @Deprecated
    public void decreaseDurabilityNBT() {
        decreaseDurability(1);
    }
*/
    public int getExtraRange() {
        return item.getIntFromTag(ItemNBT.ITEM_EXTRA_RANGE);
    }

    /*
    public String getCurrencyString() {
        return item.getStringFromTag(ItemNBT.CURRENCY_TYPE);
    }*/
/*
    @Nullable
    public FuchsCurrency getCurrency() {
        return FuchsUtility.getCurrencyByID(getCurrencyString());
    }*/

    /*public int getCustomModelData() {
        return item.getIntFromTag(ItemNBT.CUSTOM_MODEL_DATA);
    }*//*
    public void setCustomModelData(int data) {
        item.changeIntTag(ItemNBT.CUSTOM_MODEL_DATA, data);
    }*/
}

