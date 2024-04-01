package jaysonjson.papierfuchs.fuchs.object.intern.liquid;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.object.IModelData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class FuchsLiquid extends FuchsObject implements IModelData, IFuchsLiquid, IFuchsDisplayName {

    public FuchsLiquid(String id) {
        super(id, RegistryType.LIQUID);
    }

    @Override
    public int getCustomModelData() {
        return 0;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return new FuchsLanguageString();
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
        return ItemList.glass.copy();
    }

    @Override
    public int getEmptyModelData() {
        return getCustomModelData();
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }

}
