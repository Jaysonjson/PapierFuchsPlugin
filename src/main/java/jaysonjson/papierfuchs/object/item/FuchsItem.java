package jaysonjson.papierfuchs.object.item;

import jaysonjson.papierfuchs.object.item.interfaces.*;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class FuchsItem implements IFuchsRegistryObject, IConsumeable, IFuchsItem, IFuchsItemAlchemy, IFuchsItemTexture, IItemUseType, IFuchsItemUse, IFuchsItemEntityInteraction, IFuchsItemBlockInteraction {

    String id;
    Material material;
    IItemUseType itemUseType;
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
    public NBTTagCompound getTag(NBTTagCompound tag) {
        return null;
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
    public String getLoreText() {
        return null;
    }

    @Override
    public void ability(World world, Player player, ItemStack itemStack) {

    }

    public boolean isVanillaOverride() {
        return false;
    }

    public Material defaultVanillaOverride() {
        return material;
    }

    @Override
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
    public void onItemLeftClickAir() {

    }

    @Override
    public void onItemRightClickAir() {

    }

    @Override
    public void onItemLeftClickBlock() {

    }

    @Override
    public void onItemRightClickBlock() {

    }
}
