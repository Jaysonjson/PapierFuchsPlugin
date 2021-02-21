package jaysonjson.papierfuchs.data.drop.data;

import jaysonjson.papierfuchs.data.drop.obj.FuchsBlockDrop;
import jaysonjson.papierfuchs.data.drop.obj.zMobDrop;

import java.util.ArrayList;

public class zDrops {

    private ArrayList<zMobDrop> mobDrops = new ArrayList<>();
    private ArrayList<FuchsBlockDrop> block_drops = new ArrayList<>();

    public ArrayList<zMobDrop> getMobDrops() {
        return mobDrops;
    }

    public ArrayList<FuchsBlockDrop> getBlockDrops() {
        return block_drops;
    }
}
