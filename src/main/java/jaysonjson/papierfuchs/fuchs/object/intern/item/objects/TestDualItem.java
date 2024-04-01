package jaysonjson.papierfuchs.fuchs.object.intern.item.objects;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.lists.ItemList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsRegistryObject;
import org.bukkit.Material;
import org.jetbrains.annotations.Nullable;

public class TestDualItem extends FuchsItem {
    public TestDualItem(String id, Material material) {
        super(id, material);
    }


    @Nullable
    @Override
    public FuchsRegistryObject<? extends FuchsItem> getDualItem() {
        return ItemList.testDual;
    }

    @Override
    public boolean isSword() {
        return true;
    }
}
