package jaysonjson.papierfuchs.fuchs.io.data.backpack.data;

import jaysonjson.papierfuchs.fuchs.utility.FuchsUtility;
import jaysonjson.papierfuchs.fuchs.io.data.IHasUUID;

import org.bukkit.inventory.ItemStack;
import java.util.UUID;

@Deprecated
public class zBackPack implements IHasUUID {
    public String inventoryContent = "";
    public UUID uuid = UUID.randomUUID();

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public ItemStack[] getItemStacks() {
        return FuchsUtility.generateInventoryContent(inventoryContent);
    }

    public void setItemStacks(ItemStack[] itemStacks) {
        this.inventoryContent = FuchsUtility.createInventoryContent(itemStacks);
    }

    @Override
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public UUID randomUUID() {
        return null;
    }
}
