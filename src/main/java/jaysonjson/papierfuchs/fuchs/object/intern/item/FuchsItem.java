package jaysonjson.papierfuchs.fuchs.object.intern.item;

import jaysonjson.papierfuchs.fuchs.io.persistentdata.objects.FuchsItemProjectileData;
import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.object.IModelData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces.*;
import jaysonjson.papierfuchs.fuchs.object.intern.item.interfaces.gun.IFuchsItemGun;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsMCItem;
import jaysonjson.papierfuchs.fuchs.object.intern.projectile.FuchsProjectile;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.entity.projectile.EntitySnowball;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftSnowball;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class FuchsItem extends FuchsObject implements IConsumeable, IFuchsItem, IFuchsItemAlchemy, IFuchsItemUse, IFuchsItemGun, IFuchsItemTool,
        IFuchsItemCurrency, Cloneable, IFuchsDisplayName, IFuchsItemArmor, IFuchsItemFood, IFuchsItemItemFrame,
        IFuchsItemMagic, IFuchsItemStats, IFuchsItemPlant, IFuchsItemDual, IFuchsItemFertilizer, IModelData {

    private FuchsLanguageString displayName = new FuchsLanguageString(DISPLAY_NO_NAME);
    private final Material material;
    private final IItemUseType itemUseType;

    public FuchsItem(String id, Material material, IItemUseType itemUseType) {
        super(id, RegistryType.ITEM);
        this.material = material;
        this.itemUseType = itemUseType;
    }

    public FuchsItem(String id, Material material) {
        super(id, RegistryType.ITEM);
        this.material = material;
        this.itemUseType = UseTypeList.OTHER.copy();
    }

    @Override
    public ItemStack createItem(Player player, ItemStack stack) {
        return new FuchsItemData(this, player, stack).setItem();
    }

    @Override
    @Deprecated
    public void consume() {

    }

    @Override
    public int getFoodLevelFromMaterial() {
        return getFoodLevelFromMaterial(getMaterial());
    }

    @Override
    public IItemUseType getItemUse() {
        return itemUseType;
    }

    @Override
    public @NotNull Material getMaterial() {
        return material;
    }

    public boolean isVanillaOverride() {
        return false;
    }

    public Material defaultVanillaOverride() {
        return material;
    }

    public double itemVersion() {
        return 0.1;
    }

    @Override
    public String toString() {
        return "FuchsItem{" +
                "id='" + getID() + '\'' +
                ", material=" + material +
                ", itemUseType=" + itemUseType +
                ", displayName=" + getDisplayName() +
                '}';
    }

    @Override
    public FuchsRarity getDefaultRarity() {
        return RarityList.common.copy();
    }

    @Override
    @Nullable
    @Deprecated
    public IFuchsItem createCopy() {
        try {
            return (IFuchsItem) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(FuchsLanguageString fuchsLanguageString) {
        this.displayName = fuchsLanguageString;
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }

    @Override
    public NBTTagCompound getTag(NBTTagCompound tag) {
        return tag;
    }

    /*@Override
    public ItemMeta setDefaultPersistentData(ItemMeta itemMeta) {
        return itemMeta;
    }*/

    @Override
    public void shoot(PlayerInteractEvent event) {
        if(getProjectile() != null) {
            Player player = event.getPlayer();
            ItemStack itemStack = event.getItem();
            FuchsMCItem fuchsMCItemPre = new FuchsMCItem(player, itemStack);
            FuchsItemProjectileData projectileData = fuchsMCItemPre.projectileData().get();
            FuchsProjectile fuchsProjectile = FuchsUtility.projectileExists(projectileData.getContainedProjectile()) ? FuchsUtility.getProjectileById(projectileData.getContainedProjectile()) : getProjectile();
            if (fuchsProjectile != null) {
                Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
                snowball.setShooter(player);
                snowball.setVelocity(player.getLocation().getDirection().multiply((getProjectileSpeed() + fuchsProjectile.getExtraVelocity())));
                EntitySnowball entitysnowball = ((CraftSnowball) snowball).getHandle();
                FuchsMCItem fuchsMCItem = new FuchsMCItem(fuchsProjectile.asItem(player, itemStack));
                FuchsItemProjectileData projectileData1 = fuchsMCItem.projectileData().get();
                projectileData1.setProjectileId(fuchsProjectile.getID());
                projectileData1.setShooter(player.getUniqueId());
                fuchsMCItem.projectileData().set(projectileData1);
                net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(fuchsMCItem.getItemStack());
                entitysnowball.setItem(stack);
                itemStack.subtract();
            }
        }
    }

    @Override
    public void shootBow(EntityShootBowEvent event) {
        event.setCancelled(true);
        if(event.getEntity() instanceof Player player) {
            ItemStack bow = event.getBow();
            ItemStack projectile = event.getConsumable();
            FuchsMCItem fuchsMCItemBow = new FuchsMCItem(player, bow);
            FuchsItemProjectileData projectileDataPre = fuchsMCItemBow.projectileData().get();
            FuchsItemProjectileData projectileDataPro = new FuchsMCItem(player, projectile).projectileData().get();
            if(!projectileDataPre.getContainedProjectile().equalsIgnoreCase(projectileDataPro.getProjectileId()) && requiresProjectileInv()) {
                return;
            }
            FuchsProjectile fuchsProjectile = FuchsUtility.projectileExists(projectileDataPre.getProjectileId()) ? FuchsUtility.getProjectileById(projectileDataPre.getProjectileId()) : getProjectile();
            if (fuchsProjectile != null) {
                Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);
                snowball.setShooter(player);
                snowball.setVelocity(player.getLocation().getDirection().multiply((getProjectileSpeed() * event.getForce()) + fuchsProjectile.getExtraVelocity()));
                EntitySnowball entitysnowball = ((CraftSnowball) snowball).getHandle();
                FuchsMCItem fuchsMCItem = new FuchsMCItem(fuchsProjectile.asItem(player, null));
                FuchsItemProjectileData projectileData = fuchsMCItem.projectileData().get();
                projectileData.setProjectileId(fuchsProjectile.getID());
                projectileData.setShooter(player.getUniqueId());
                fuchsMCItem.projectileData().set(projectileData);
                net.minecraft.world.item.ItemStack stack = CraftItemStack.asNMSCopy(fuchsMCItem.getItemStack());
                entitysnowball.setItem(stack);
            }
        }
    }
    
}
