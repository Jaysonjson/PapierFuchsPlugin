package jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.FuchsPersistentData;
import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.*;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.ItemPersistentData;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;

public class FuchsMCItem {
    private FuchsItem item;
    private int amount = 1;
    private Player player = null;
    private ItemStack original = null;
    private boolean vanillaOverride = false;
    private net.minecraft.world.item.ItemStack nmsStack = null;
    private final FuchsMCItemData data = new FuchsMCItemData(this);
    private ItemMeta itemMeta;

    public FuchsMCItem(FuchsItem item) {
        this.item = item;
    }

    public boolean isVanillaOverride() {
        return vanillaOverride;
    }

    public void setVanillaOverride(boolean vanillaOverride) {
        this.vanillaOverride = vanillaOverride;
    }

    public FuchsMCItem(FuchsItem item, Player player) {
        this.item = item;
        this.player = player;
        this.original = item.createItem(player);
        this.itemMeta = original.getItemMeta();
        this.amount = original.getAmount();
    }

    @Deprecated
    public FuchsMCItem(FuchsItem item, ItemStack itemStack) {
        this.item = item;
        this.original = itemStack;
        this.itemMeta = original.getItemMeta();
        this.amount = original.getAmount();
    }

    public FuchsMCItem(FuchsItem item, Player player, ItemStack itemStack) {
        this.item = item;
        this.player = player;
        this.original = itemStack;
        this.itemMeta = original.getItemMeta();
        this.amount = original.getAmount();
    }

    public FuchsMCItem(Player player, ItemStack itemStack) {
        this.player = player;
        this.original = itemStack;
        this.itemMeta = original.getItemMeta();
        this.amount = original.getAmount();
    }

    public FuchsMCItem(ItemStack itemStack) {
        this.original = itemStack;
        if(itemStack == null) {
            this.original = new ItemStack(Material.AIR);
        }
        this.itemMeta = original.getItemMeta();
        this.amount = original.getAmount();
    }

    public void changeStringTag(String key, String value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setString(key, value);
        updateOriginalTag(tagI);
    }

    public void changeIntTag(String key, int value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setInt(key, value);
        updateOriginalTag(tagI);
    }

    public void changeDoubleTag(String key, Double value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setDouble(key, value);
        updateOriginalTag(tagI);
    }

    public void changeBooleanTag(String key, Boolean value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setBoolean(key, value);
        updateOriginalTag(tagI);
    }

    public void changeFloatTag(String key, Float value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setFloat(key, value);
        updateOriginalTag(tagI);
    }

    public void changeByteTag(String key, Byte value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setByte(key, value);
        updateOriginalTag(tagI);
    }

    public void changeShortTag(String key, Short value) {
        NBTTagCompound tagI = getTagFromOriginal();
        tagI.setShort(key, value);
        updateOriginalTag(tagI);
    }

    public NBTTagCompound getTagFromOriginal() {
        updateNMSStack();
        return nmsStack.hasTag() ? nmsStack.getTag() : new NBTTagCompound();
    }

    public double getDoubleFromTag(String key) {
        return getTagFromOriginal().getDouble(key);
    }

    public int getIntFromTag(String key) {
        return getTagFromOriginal().getInt(key);
    }

    public String getStringFromTag(String key) {
        return getTagFromOriginal().getString(key);
    }

    public float getFloatFromTag(String key) {
        return getTagFromOriginal().getFloat(key);
    }

    public byte getByteFromTag(String key) {
        return getTagFromOriginal().getByte(key);
    }

    public boolean getBooleanFromTag(String key) {
        return getTagFromOriginal().getBoolean(key);
    }

    public void updateOriginalTag(NBTTagCompound tagCompound) {
        nmsStack.setTag(tagCompound);
        original = CraftItemStack.asBukkitCopy(nmsStack);
        //original = item.createItem(player, original);
    }

    public void updateNMSStack() {
        nmsStack = FuchsUtility.createNMSCopy(original);
    }

    public net.minecraft.world.item.ItemStack getNmsStack() {
        return nmsStack;
    }

    public void setNmsStack(net.minecraft.world.item.ItemStack nmsStack) {
        this.nmsStack = nmsStack;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Nullable
    public Player getPlayer() {
        return player;
    }

    public void setOriginal(ItemStack original) {
        this.original = original;
    }

    public ItemStack getOriginal() {
        return original;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = original;
        if(item != null) {
             itemStack = item.createItem(player, original);
        }
        itemStack.setAmount(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            if(generalData().get().getModeldata() != -1) {
                itemMeta.setCustomModelData(generalData().get().getModeldata());
                itemStack.setItemMeta(itemMeta);
            }
        }
        return itemStack;
    }

    @Nullable
    public PersistentDataContainer getDataContainer() {
        itemMeta = original.getItemMeta();
        if(itemMeta != null) {
            return itemMeta.getPersistentDataContainer();
        }
        return null;
    }

    public void setDCString(NamespacedKey key, String value) {
        if(getDataContainer() != null) {
            getDataContainer().set(key, PersistentDataType.STRING, value);
        }
        setOriginalItemMeta();
    }

    public void setOriginalItemMeta() {
        original.setItemMeta(itemMeta);
    }

    public String getDCString(NamespacedKey key) {
        if(getDataContainer() != null) {
            if (getDataContainer().has(key, PersistentDataType.STRING)) {
                return getDataContainer().get(key, PersistentDataType.STRING);
            }
        }
        return "";
    }

    public boolean dcHasString(NamespacedKey key) {
        if(getDataContainer() != null) {
            return getDataContainer().has(key, PersistentDataType.STRING);
        }
        return false;
    }

    public void setDCInt(NamespacedKey key, int value) {
        if(getDataContainer() != null) {
            getDataContainer().set(key, PersistentDataType.INTEGER, value);
            setOriginalItemMeta();
        }
    }

    public boolean dcHasInt(NamespacedKey key) {
        if(getDataContainer() != null) {
            return getDataContainer().has(key, PersistentDataType.INTEGER);
        }
        return false;
    }

    public int getDCInt(NamespacedKey key) {
        if(dcHasInt(key)) {
            return getDataContainer().get(key, PersistentDataType.INTEGER);
        }
        return 0;
    }

    public void setDCDouble(NamespacedKey key, double value) {
        if(getDataContainer() != null) {
            getDataContainer().set(key, PersistentDataType.DOUBLE, value);
            setOriginalItemMeta();
        }
    }

    public boolean dcHasDouble(NamespacedKey key) {
        if(getDataContainer() != null) {
            return getDataContainer().has(key, PersistentDataType.DOUBLE);
        }
        return false;
    }

    public double getDCDouble(NamespacedKey key) {
        if(dcHasDouble(key)) {
            return getDataContainer().get(key, PersistentDataType.DOUBLE);
        }
        return 0;
    }

    public void setDCFloat(NamespacedKey key, float value) {
        if(getDataContainer() != null) {
            getDataContainer().set(key, PersistentDataType.FLOAT, value);
            setOriginalItemMeta();
        }
    }

    public boolean dcHasFloat(NamespacedKey key) {
        if(getDataContainer() != null) {
            return getDataContainer().has(key, PersistentDataType.FLOAT);
        }
        return false;
    }

    public float getDCFloat(NamespacedKey key) {
        if(dcHasFloat(key)) {
            return getDataContainer().get(key, PersistentDataType.FLOAT);
        }
        return 0;
    }

    public void setDCByte(NamespacedKey key, byte value) {
        if(getDataContainer() != null) {
            getDataContainer().set(key, PersistentDataType.BYTE, value);
            setOriginalItemMeta();
        }
    }

    public boolean dcHasByte(NamespacedKey key) {
        if(getDataContainer() != null) {
            return getDataContainer().has(key, PersistentDataType.BYTE);
        }
        return false;
    }

    public float getDCByte(NamespacedKey key) {
        if(dcHasFloat(key)) {
            return getDataContainer().get(key, PersistentDataType.BYTE);
        }
        return 0;
    }

    public void setDCBool(NamespacedKey key, boolean value) {
        if(getDataContainer() != null) {
            getDataContainer().set(key, FuchsPersistentData.BOOL, value);
            setOriginalItemMeta();
        }
    }

    public boolean dcHasBool(NamespacedKey key) {
        if(getDataContainer() != null) {
            return getDataContainer().has(key, FuchsPersistentData.BOOL);
        }
        return false;
    }

    public boolean getDCBool(NamespacedKey key) {
        if(dcHasBool(key)) {
            return getDataContainer().get(key, FuchsPersistentData.BOOL);
        }
        return false;
    }

    public void setDCData(NamespacedKey key, PersistentDataType<byte[], Object> data, Object value) {
        if(getDataContainer() != null) {
            getDataContainer().set(key, data, value);
        }
    }

    public boolean hasDCData(NamespacedKey key, PersistentDataType<byte[], Object> data) {
        if(getDataContainer() != null) {
            return getDataContainer().has(key, data);
        }
        return false;
    }

    public Object getDCData(NamespacedKey key, PersistentDataType<byte[], Object> data) {
        if(hasDCData(key, data)) {
            return getDataContainer().get(key, data);
        }
        return new Object();
    }

    public void setItem(FuchsItem item) {
        this.item = item;
    }

    @Nullable
    public FuchsItem getItem() {
        return item;
    }

    public FuchsMCItemData getData() {
        return data;
    }

    public FuchsMCDCData<FuchsItemGeneralData> generalData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_ITEM_GENERAL;
        return new FuchsMCDCData<>(new FuchsItemGeneralData(), ItemPersistentData.FUCHS_ITEM_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemAlchemyData> alchemyData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_ALCHEMY_GENERAL;
        return new FuchsMCDCData<>(new  FuchsItemAlchemyData(), ItemPersistentData.FUCHS_ALCHEMY_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemArmorData> armorData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_ARMOR_GENERAL;
        return new FuchsMCDCData<>(new  FuchsItemArmorData(), ItemPersistentData.FUCHS_ARMOR_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemCurrencyData> currencyData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_CURRENCY_GENERAL;
        return new FuchsMCDCData<>(new  FuchsItemCurrencyData(), ItemPersistentData.FUCHS_CURRENCY_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemGasData> gasData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_GAS_GENERAL;
        return new FuchsMCDCData<>(new FuchsItemGasData(), ItemPersistentData.FUCHS_GAS_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemBlockData> blockData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_BLOCK_GENERAL;
        return new FuchsMCDCData<>(new FuchsItemBlockData(), ItemPersistentData.FUCHS_BLOCK_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemLiquidData> liquidData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_LIQUID_GENERAL;
        return new FuchsMCDCData<>(new FuchsItemLiquidData(), ItemPersistentData.FUCHS_LIQUID_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemMagicData> magicData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_MAGIC_GENERAL;
        return new FuchsMCDCData<>(new FuchsItemMagicData(), ItemPersistentData.FUCHS_MAGIC_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemProjectileData> projectileData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_PROJECTILE_GENERAL;
        return new FuchsMCDCData<>(new FuchsItemProjectileData(), ItemPersistentData.FUCHS_PROJECTILE_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemToolData> toolData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_TOOL_GENERAL;
        return new FuchsMCDCData<>(new FuchsItemToolData(), ItemPersistentData.FUCHS_TOOL_GENERAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemSpecialData> specialData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_SPECIAL;
        return new FuchsMCDCData<>(new FuchsItemSpecialData(), ItemPersistentData.FUCHS_SPECIAL, (PersistentDataType<byte[], Object>) p, this);
    }

    public FuchsMCDCData<FuchsItemPlantData> plantData() {
        PersistentDataType<byte[], ?> p = FuchsPersistentData.FUCHS_PLANT;
        return new FuchsMCDCData<>(new FuchsItemPlantData(), ItemPersistentData.FUCHS_PLANT, (PersistentDataType<byte[], Object>) p, this);
    }

}
