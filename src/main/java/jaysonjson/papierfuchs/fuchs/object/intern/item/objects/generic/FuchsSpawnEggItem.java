package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.IItemUseType;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.Nullable;

public class FuchsSpawnEggItem extends FuchsItem {

    private FuchsEntity<?> entity;
    public FuchsSpawnEggItem(String id, Material material, IItemUseType itemUseType) {
        super(id, material, itemUseType);
    }

    public FuchsSpawnEggItem(String id, Material material, IItemUseType itemUseType, FuchsEntity<?> entity) {
        super(id, material, itemUseType);
        this.entity = entity;
    }

    @Nullable
    public FuchsEntity<?> getEntity() {
        return entity;
    }

    @Override
    public void onItemRightClick(PlayerInteractEvent event) {
        entity.create(event.getPlayer(), event.getInteractionPoint());
        super.onItemRightClick(event);
    }

    public void setEntity(FuchsEntity<?> entity) {
        this.entity = entity;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.entity.copy() };
    }

    @Override
    public int getCustomModelData() {
        if(entity != null) {
            return entity.getCustomModelData();
        }
        return -1;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        if(entity != null) {
            return entity.getDisplayName();
        }
        return FuchsLanguageString.c("Unbekanntes Spawnegg");
    }
}
