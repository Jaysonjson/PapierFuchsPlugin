package jaysonjson.papierfuchs.fuchs.io.data.player.data.special;

public class FPSAlcohol {

    private float current = 0f;
    private float max = 4f;

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

    public void increase(double amount) {
        current += amount;
    }

    public void decrease(double amount) {
        current -= amount;
    }

    public void reset() {
        current = 0;
    }
}
