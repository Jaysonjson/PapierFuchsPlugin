package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.io.Serial;
import java.io.Serializable;

public class FuchsItemArmorData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private double armorToughness = 0;
    private double armor = 0;

    //private int armorModel = -1;
    public FuchsItemArmorData() {

    }

    public FuchsItemArmorData(FuchsItem fuchsItem) {
        this.armor = fuchsItem.getArmor();
        this.armorToughness = fuchsItem.getArmorToughness();
        //this.armorModel = fuchsItem.getArmorModel();
    }

    public double getArmor() {
        return armor;
    }

    public double getArmorToughness() {
        return armorToughness;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public void setArmorToughness(double armorToughness) {
        this.armorToughness = armorToughness;
    }

    @Override
    public String toString() {
        return "FuchsItemArmorData{" +
                "armorToughness=" + armorToughness +
                ", armor=" + armor +
                '}';
    }
}
