package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.io.Serial;
import java.io.Serializable;

public class FuchsItemToolData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private int durability = 0;
    private int maxDurability = 0;
    private double attackSpeed = 0d;
    private double attackDamage = 0d;
    private double extraRange = 0d;

    public FuchsItemToolData() {

    }

    public FuchsItemToolData(FuchsItem fuchsItem) {
        this.durability = fuchsItem.getMaxDurability();
        this.attackSpeed = fuchsItem.getToolAttackSpeed();
        this.attackDamage = fuchsItem.getToolDamage();
        this.maxDurability = fuchsItem.getMaxDurability();
        this.extraRange = fuchsItem.getExtraRange();
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public void increaseDurability(int value) {
        setDurability(getDurability() + value);
    }

    public void increaseDurability() {
        increaseDurability(1);
    }

    public void decreaseDurability(int value) {
        increaseDurability(-value);
    }

    public void decreaseDurability() {
        decreaseDurability(1);
    }

    public double getAttackDamage() {
        return attackDamage;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackDamage(double attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public boolean canHoldDurability() {
        return maxDurability > 0;
    }

    public double getExtraRange() {
        return extraRange;
    }

    public void setExtraRange(double extraRange) {
        this.extraRange = extraRange;
    }

    public int getMaxDurability() {
        return maxDurability;
    }

    public void setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
    }

    public boolean isExtraRanged() {
        return getExtraRange() > 0;
    }

    @Override
    public String toString() {
        return "FuchsItemToolData{" +
                "durability=" + durability +
                ", maxDurability=" + maxDurability +
                ", attackSpeed=" + attackSpeed +
                ", attackDamage=" + attackDamage +
                '}';
    }
}
