package jaysonjson.papierfuchs.fuchs.io.data.drop.obj;

import org.bukkit.entity.EntityType;

public class FuchsMobDrop extends FuchsDrop {

    private EntityType entity = EntityType.EGG;
    private String fuchs = "";


    public EntityType getEntity() {
        return entity;
    }

    public String getFuchs() {
        return fuchs;
    }

    public void setEntity(EntityType entity) {
        this.entity = entity;
    }

    public void setFuchs(String fuchs) {
        this.fuchs = fuchs;
    }
}
