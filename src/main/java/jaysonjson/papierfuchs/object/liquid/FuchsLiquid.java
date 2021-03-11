package jaysonjson.papierfuchs.object.liquid;

import jaysonjson.papierfuchs.object.item.FuchsItem;
import jaysonjson.papierfuchs.object.item.ItemList;
import jaysonjson.papierfuchs.object.item.interfaces.IFuchsItemName;
import jaysonjson.papierfuchs.object.item.interfaces.IFuchsItemTexture;
import jaysonjson.papierfuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.registry.IFuchsPlugin;
import jaysonjson.papierfuchs.registry.IFuchsRegistryObject;
import jaysonjson.papierfuchs.registry.RegistryType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class FuchsLiquid extends FuchsObject implements IFuchsItemTexture, IFuchsLiquid, IFuchsItemName {

    public FuchsLiquid(String id) {
        super(id, RegistryType.LIQUID);
    }


    @Override
    public int getCustomModelData() {
        return 0;
    }

    @Override
    public String getDisplayName() {
        return "Nichts";
    }

    @Override
    public boolean drinkAble() {
        return false;
    }

    @Override
    public void drinkAction(Player player, ItemStack itemStack) {
    }

    @Override
    public Material getMinecraftEquivalent() {
        return null;
    }

    @Override
    public FuchsItem getEmptyBottle() {
        return ItemList.GLASS;
    }

    @Override
    public int getEmptyModelData() {
        return getCustomModelData();
    }

    @Override
    @Deprecated
    public void setDisplayName(String displayName) {
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }
}
