package jaysonjson.papierfuchs.fuchs.object.intern.skillclass;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;

import java.io.File;

public class FuchsSkillclassData {
    int level = 1;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void increaseLevel(int amount) {
        this.level += amount;
    }

    public void increaseLevel() {
        increaseLevel(1);
    }

    public void decreaseLevel(int amount) {
        increaseLevel(-amount);
    }

    public void decreaseLevel() {
        decreaseLevel(1);
    }

    public static String defaultPath(FuchsPlayer fuchsPlayer, FuchsSkillclass<?> fuchsSkillclass) {
        String path = fuchsPlayer.getPath() + "classes/" + fuchsSkillclass.getFuchsPlugin().getRegistryID() + "/";
        new File(path).mkdirs();
        return path + fuchsSkillclass.getRealID() + ".json";
    }
}
