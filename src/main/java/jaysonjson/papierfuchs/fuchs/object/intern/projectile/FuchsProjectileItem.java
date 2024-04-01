package jaysonjson.papierfuchs.fuchs.object.intern.projectile;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import org.bukkit.Material;

public class FuchsProjectileItem extends FuchsItem {

    public FuchsProjectileItem(String id, Material material) {
        super(id, material, UseTypeList.PROJECTILE.copy());
    }

}
