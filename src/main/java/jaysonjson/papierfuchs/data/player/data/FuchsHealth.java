package jaysonjson.papierfuchs.data.player.data;

public class FuchsHealth {
    public int maxHealth = 20;
    public int health = 20;

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
