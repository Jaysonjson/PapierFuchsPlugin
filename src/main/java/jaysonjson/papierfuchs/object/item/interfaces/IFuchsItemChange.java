package jaysonjson.papierfuchs.object.item.interfaces;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface IFuchsItemChange {
    void changeStringTag(String key, String value);

    void changeIntTag(String key, int value);

    void changeDoubleTag(String key, Double value);

    void changeBooleanTag(String key, Boolean value);

    void changeFloatTag(String key, Float value);

    void changeByteTag(String key, Byte value);

    void changeShortTag(String key, Short value);

    NBTTagCompound getTagFromOriginal();

    double getDoubleFromTag(String key);

    int getIntFromTag(String key);

    String getStringFromTag(String key);

    float getFloatFromTag(String key);

    byte getByteFromTag(String key);

    void updateMinecraftOriginalTag(NBTTagCompound tagCompound);

    void updateNMSStack();

    net.minecraft.server.v1_16_R3.ItemStack getNMSStack();

    void setNMSStack(net.minecraft.server.v1_16_R3.ItemStack nmsStack);

    void setPlayer(Player player);

    Player getPlayer();

    void setMinecraftOriginal(ItemStack original);

    ItemStack getMinecraftOriginal();

    void setAmount(int amount);

    int getAmount();

    ItemStack getMinecraftItemStack();
}
