package jaysonjson.papierfuchs.fuchs.object.intern.block;

import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import org.jetbrains.annotations.Nullable;

/*
    FuchsBlock -> uses a FuchsItem on an ArmorStand and Barriers to create a kind of custom Block
    If no FuchsItem is given, a default one will be generated
 */
public abstract class FuchsBlock extends FuchsObject implements IFuchsBlock, IFuchsDisplayName {

    private FuchsRegistryObject<? extends FuchsItem> item = null;
    public FuchsBlock(String id, FuchsRegistryObject<? extends FuchsItem> item) {
        super(id, RegistryType.BLOCK);
        this.item = item;
    }

    public FuchsBlock(String id) {
        super(id, RegistryType.BLOCK);
    }

    @Nullable
    @Override
    public FuchsRegistryObject<? extends FuchsItem> asItem() {
        return item;
    }

    public void setItem(FuchsRegistryObject<? extends FuchsItem> item) {
        this.item = item;
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }
}
