package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.Utility;
import jaysonjson.papierfuchs.object.gas.FuchsGas;
import jaysonjson.papierfuchs.object.liquid.FuchsLiquid;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public class FuchsMCItem {
    private FuchsItem item;
    private int amount = 1;
    private Player player = null;
    private ItemStack original = null;
    private boolean vanillaOverride = false;
    private net.minecraft.server.v1_16_R3.ItemStack nmsStack = null;

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
    }

    public FuchsMCItem(FuchsItem item, ItemStack itemStack) {
        this.item = item;
        this.original = itemStack;
    }

    public FuchsMCItem(FuchsItem item, Player player, ItemStack itemStack) {
        this.item = item;
        this.player = player;
        this.original = itemStack;
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
        NBTTagCompound tag = nmsStack.getTag();
        return tag;
    }

    public double getDoubleFromTag(String key) {
        NBTTagCompound tagI = getTagFromOriginal();
        return tagI.getDouble(key);
    }

    public int getIntFromTag(String key) {
        NBTTagCompound tagI = getTagFromOriginal();
        return tagI.getInt(key);
    }

    public String getStringFromTag(String key) {
        NBTTagCompound tagI = getTagFromOriginal();
        return tagI.getString(key);
    }

    public float getFloatFromTag(String key) {
        NBTTagCompound tagI = getTagFromOriginal();
        return tagI.getFloat(key);
    }

    public byte getByteFromTag(String key) {
        NBTTagCompound tagI = getTagFromOriginal();
        return tagI.getByte(key);
    }

    public void updateOriginalTag(NBTTagCompound tagCompound) {
        nmsStack.setTag(tagCompound);
        original = CraftItemStack.asBukkitCopy(nmsStack);
        original = item.createItem(original);
    }

    public void updateNMSStack() {
        nmsStack = Utility.createNMSCopy(original);
    }

    public net.minecraft.server.v1_16_R3.ItemStack getNmsStack() {
        return nmsStack;
    }

    public void setNmsStack(net.minecraft.server.v1_16_R3.ItemStack nmsStack) {
        this.nmsStack = nmsStack;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

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
        ItemStack itemStack = item.createItem(player, original);
        itemStack.setAmount(amount);
        return itemStack;
    }

    public void setItem(FuchsItem item) {
        this.item = item;
    }

    public FuchsItem getItem() {
        return item;
    }

    public String getLiquidID() {
        return getTagFromOriginal().getString(ItemNBT.CONTAINED_LIQUID);
    }

    @Nullable
    public FuchsLiquid getLiquid() {
        return Utility.getLiquidByID(getLiquidID());
    }

    public void setLiquidID(String id) {
        NBTTagCompound tag = getTagFromOriginal();
        tag.setString(ItemNBT.CONTAINED_LIQUID, id);
        updateOriginalTag(tag);
    }

    public String getGasID() {
        return getTagFromOriginal().getString(ItemNBT.CONTAINED_GAS);
    }

    @Nullable
    public FuchsGas getGas() {
        return Utility.getGasByID(getGasID());
    }

    public void setGasID(String id) {
        NBTTagCompound tag = getTagFromOriginal();
        tag.setString(ItemNBT.CONTAINED_GAS, id);
        updateOriginalTag(tag);
    }

    public double getLiquidAmount() {
        return getTagFromOriginal().getDouble(ItemNBT.LIQUID_AMOUNT);
    }

    public double getGasAmount() {
        return getTagFromOriginal().getDouble(ItemNBT.GAS_AMOUNT);
    }

    public void setLiquidAmount(double amount) {
        NBTTagCompound tag = getTagFromOriginal();
        tag.setDouble(ItemNBT.LIQUID_AMOUNT, amount);
        updateOriginalTag(tag);
    }

    public void setGasAmount(double amount) {
        NBTTagCompound tag = getTagFromOriginal();
        tag.setDouble(ItemNBT.GAS_AMOUNT, amount);
        updateOriginalTag(tag);
    }

    public boolean isCharged() {
        return getTagFromOriginal().getBoolean(ItemNBT.CHARGED);
    }

    public void setCharged(boolean value) {
        NBTTagCompound tag = getTagFromOriginal();
        tag.setBoolean(ItemNBT.CHARGED, value);
        updateOriginalTag(tag);
    }

    public void toggleCharged() {
        setCharged(!isCharged());
    }
}
