package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.object.effect.FuchsEffect;
import jaysonjson.papierfuchs.object.item.interfaces.*;
import jaysonjson.papierfuchs.object.item.interfaces.gun.IFuchsGunMagazine;
import jaysonjson.papierfuchs.object.item.interfaces.gun.IFuchsItemAmmo;
import jaysonjson.papierfuchs.object.item.interfaces.gun.IFuchsItemGun;
import jaysonjson.papierfuchs.object.rarity.FuchsRarity;
import jaysonjson.papierfuchs.object.rarity.RarityList;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FuchsItem implements IFuchsRegistryObject, IConsumeable, IFuchsItem, IFuchsItemAlchemy,
        IFuchsItemTexture, IFuchsItemUse, IFuchsItemEntityInteraction,
        IFuchsItemBlockInteraction, IFuchsItemGun, IFuchsItemTool, IFuchsItemCurrency {

    private String id;
    private String displayName = "";
    private final Material material;
    private final IItemUseType itemUseType;
    public FuchsItem(String id, Material material, IItemUseType itemUseType) {
        this.id = id;
        this.material = material;
        this.itemUseType = itemUseType;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public RegistryType getType() {
        return RegistryType.ITEM;
    }

    @Override
    public void consume() {

    }

    @Override
    public IItemUseType getItemUse() {
        return itemUseType;
    }

    @Override
    public @NotNull Material getMaterial() {
        return material;
    }

    @Override
    public int getEarthValue() {
        return 0;
    }

    @Override
    public int getWaterValue() {
        return 0;
    }

    @Override
    public int getMetalValue() {
        return 0;
    }

    @Override
    public int getFireValue() {
        return 0;
    }

    @Override
    public int getCustomModelData() {
        return 0;
    }

    @Override
    @Deprecated
    public void ability(World world, Player player, ItemStack itemStack) {

    }

    public boolean isVanillaOverride() {
        return false;
    }

    public Material defaultVanillaOverride() {
        return material;
    }

    @Override
    @Deprecated
    public boolean isAbilityItem() {
        return false;
    }

    @Override
    public int requiredIntelligence() {
        return 0;
    }

    public double itemVersion() {
        return 0.1;
    }

    @Override
    public String toString() {
        return "FuchsItem{" +
                "id='" + id + '\'' +
                ", material=" + material +
                ", itemUseType=" + itemUseType +
                '}';
    }

    @Override
    public void onEntityInteract(PlayerInteractEntityEvent event) {

    }

    @Override
    public void onBlockInteract(PlayerInteractEvent event) {

    }

    @Override
    public void onItemLeftClickAir(PlayerInteractEvent event) {
        //onItemUse(event);
    }

    @Override
    public void onItemRightClickAir(PlayerInteractEvent event) {
        //onItemUse(event);
    }

    @Override
    public void onItemLeftClickBlock(PlayerInteractEvent event) {
        //onItemUse(event);
    }

    @Override
    public void onItemRightClickBlock(PlayerInteractEvent event) {
        //onItemUse(event);
    }

    @Override
    public void onItemUse(PlayerInteractEvent event) {

    }

    @Override
    public void onItemLeftClick(PlayerInteractEvent event) { }

    @Override
    public void onItemRightClick(PlayerInteractEvent event) { }

    @Override
    public Vector getVelocity() {
        return null;
    }

    @Override
    public IFuchsItemAmmo getAmmo() {
        return null;
    }

    @Override
    public IFuchsGunMagazine getMagazine() {
        return null;
    }

    @Override
    public int getMaxDurability() {
        return 0;
    }

    @Override
    public int getToolDamage() {
        return 0;
    }

    @Override
    public int getToolEfficiency() {
        return 0;
    }

    @Override
    public int getDamageProtection() {
        return 0;
    }

    @Override
    public double getCurrencyAmount() {
        return 0;
    }

    @Override
    public String getCurrencyType() {
        return "";
    }

    @Override
    public float getToolAttackSpeed() {
        return 0;
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.COMMON;
    }

    @Override
    public String getLanguageString() {
        return "";
    }

    @Deprecated
    @Override
    public String getDefaultDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public boolean stackAble() {
        return true;
    }

    @Override
    @Nullable
    @Deprecated
    public IFuchsItem createCopy() {
        return null;
    }

    @Override
    public boolean isPlaceAble() {
        return false;
    }

    @Override
    public void updateID(IFuchsPlugin fuchsPlugin) {
        this.id = fuchsPlugin.getPluginID() + ":" + getID();
    }
}
