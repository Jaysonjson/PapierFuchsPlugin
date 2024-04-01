package jaysonjson.papierfuchs.fuchs.object.intern.block;

import org.bukkit.util.Vector;

public class FuchsBlockArmorStand {

    private Vector vector;
    private int modeldata;

    public FuchsBlockArmorStand(Vector vector, int modeldata) {
        this.vector = vector;
        this.modeldata = modeldata;
    }

    public int getModeldata() {
        return modeldata;
    }

    public Vector getVector() {
        return vector;
    }

    public void setModeldata(int modeldata) {
        this.modeldata = modeldata;
    }

    public void setVector(Vector vector) {
        this.vector = vector;
    }
}
