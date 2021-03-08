package jaysonjson.papierfuchs.data.area.obj;

import org.bukkit.World;

public enum FuchsWorld {
    OVERWORLD(World.Environment.NORMAL),
    NETHER(World.Environment.NETHER),
    END(World.Environment.THE_END);

    World.Environment environment;
    FuchsWorld(World.Environment environment) {
        this.environment = environment;
    }

    public World.Environment getEnvironment() {
        return environment;
    }
}
