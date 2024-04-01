package jaysonjson.papierfuchs.fuchs.object.intern.item.objects.weapons.bows;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.FuchsItemCategory;
import jaysonjson.papierfuchs.fuchs.object.intern.category.item.ItemCategoryList;
import jaysonjson.papierfuchs.fuchs.object.intern.inventory.objects.item.ItemCategoryInventoryOld;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityShootBowEvent;

public class DefaultBowItem extends FuchsItem {

    public DefaultBowItem() {
        super("bow_with_projectile", Material.BOW, UseTypeList.BOW.copy());
    }

    public DefaultBowItem(String id) {
        super(id, Material.BOW, UseTypeList.BOW.copy());
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("Bogen");
    }

    @Override
    public void onBowShoot(EntityShootBowEvent event) {
        shootBow(event);
        super.onBowShoot(event);
    }

    @Override
    public double getProjectileSpeed() {
        return 1;
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }

    @Override
    public FuchsItemCategory[] getCategories() {
        return new FuchsItemCategory[] { ItemCategoryList.bow.copy(), ItemCategoryList.weapon.copy() };
    }

}
