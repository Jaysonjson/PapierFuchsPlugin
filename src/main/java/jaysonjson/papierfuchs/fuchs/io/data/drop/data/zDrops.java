package jaysonjson.papierfuchs.fuchs.io.data.drop.data;

import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.FuchsBlockDrop;
import jaysonjson.papierfuchs.fuchs.io.data.drop.obj.zMobDrop;

import java.util.ArrayList;

public class zDrops {

    private final ArrayList<zMobDrop> mobDrops = new ArrayList<>();
    private final ArrayList<FuchsBlockDrop> block_drops = new ArrayList<>();

    public ArrayList<zMobDrop> getMobDrops() {
        return mobDrops;
    }

    public ArrayList<FuchsBlockDrop> getBlockDrops() {
        return block_drops;
    }
}
