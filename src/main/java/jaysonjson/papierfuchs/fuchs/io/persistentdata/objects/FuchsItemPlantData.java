package jaysonjson.papierfuchs.fuchs.io.persistentdata.objects;

import jaysonjson.papierfuchs.PapierFuchs;
import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;

import java.io.Serial;
import java.io.Serializable;

public class FuchsItemPlantData implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;

    private double growTime = 0;
    private double currentGrow = 0;
    private String fruit = "";
    private int modelData = 0;
    private int finishedModelData = 0;
    private int maxFruitDrop = 0;
    private int minFruitDrop = 0;
    private boolean finished = false;

    public FuchsItemPlantData() {

    }

    public FuchsItemPlantData(FuchsItem fuchsItem) {
        fromFuchsItem(fuchsItem);
    }

    public void fromFuchsItem(FuchsItem fuchsItem)  {
        if(fuchsItem.hasFruit()) {
            fruit = fuchsItem.getFruit().getID();
            growTime = fuchsItem.getPlantGrowTime() + (PapierFuchs.random.nextDouble() * (PapierFuchs.random.nextInt(3)));
            modelData = fuchsItem.getPlantModelData();
            finishedModelData = fuchsItem.getFinishedPlantModelData();
            maxFruitDrop = fuchsItem.maxFruitDrop();
            minFruitDrop = fuchsItem.minFruitDrop();
        }
    }

    public double getGrowTime() {
        return growTime;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public void setGrowTime(double growTime) {
        this.growTime = growTime;
    }

    public void setCurrentGrow(double currentGrow) {
        this.currentGrow = currentGrow;
    }

    public double getCurrentGrow() {
        return currentGrow;
    }

    public int getFinishedModelData() {
        return finishedModelData;
    }

    public int getModelData() {
        return modelData;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getMaxFruitDrop() {
        return maxFruitDrop;
    }

    public int getMinFruitDrop() {
        return minFruitDrop;
    }

    public void setMaxFruitDrop(int maxFruitDrop) {
        this.maxFruitDrop = maxFruitDrop;
    }

    public void setMinFruitDrop(int minFruitDrop) {
        this.minFruitDrop = minFruitDrop;
    }
}
