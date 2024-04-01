package jaysonjson.papierfuchs.fuchs.object.intern.effect;

import java.io.Serializable;

public class EffectItemData implements Serializable {

    private byte level = 1;
    private byte maxLevel = 3;

    public EffectItemData() {

    }

    public EffectItemData(byte maxLevel) {
        this.maxLevel = maxLevel;
    }

    public byte getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(byte maxLevel) {
        this.maxLevel = maxLevel;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }
}
