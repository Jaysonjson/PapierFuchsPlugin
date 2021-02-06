package jaysonjson.papierfuchs.data.drop.obj;

import jaysonjson.papierfuchs.object.item.FuchsItem;

public class ItemDropChance {
    public String item;
    public int chance;
    public int max_amount;
    public int min_amount;
    public transient FuchsItem fuchsItem;

    public ItemDropChance(String item, int chance, int min_amount, int max_amount) {
        this.item = item;
        this.chance = chance;
        this.min_amount = min_amount;
        this.max_amount = max_amount;
    }

    public String getItem() {
        return item;
    }

    public int getChance() {
        return chance;
    }

    public void setFuchsItem(FuchsItem fuchsItem) {
        this.fuchsItem = fuchsItem;
    }

    public FuchsItem getFuchsItem() {
        return fuchsItem;
    }

    public int getMaxAmount() {
        return max_amount;
    }

    public int getMinAmount() {
        return min_amount;
    }

    @Override
    public String toString() {
        return "ItemDropChance{" +
                "item='" + item + '\'' +
                ", chance=" + chance +
                ", max_amount=" + max_amount +
                ", min_amount=" + min_amount +
                ", fuchsItem=" + fuchsItem +
                '}';
    }
}
