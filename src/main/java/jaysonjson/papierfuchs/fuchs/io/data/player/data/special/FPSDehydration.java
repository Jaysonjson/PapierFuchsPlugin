package jaysonjson.papierfuchs.fuchs.io.data.player.data.special;

public class FPSDehydration {
    private float current = 0;
    private float max = 100;

    public float getCurrent() {
        return current;
    }

    public float getMax() {
        return max;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public void increaseCurrent(float increase) {
        setCurrent(getCurrent() + increase);
    }

    public void increaseCurrent() {
        increaseCurrent(1);
    }

    public void decreaseCurrent(float decrement) {
        increaseCurrent(-decrement);
    }

    public void decreaseCurrent() {
        decreaseCurrent(1);
    }
}
