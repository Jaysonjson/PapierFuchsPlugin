package jaysonjson.papierfuchs.fuchs.io.data.server.data;

import jaysonjson.papierfuchs.fuchs.io.data.FuchsLocation;
import jaysonjson.papierfuchs.fuchs.object.intern.npc.FuchsNPCData;

import java.util.ArrayList;

public class FuchsServer {

    public ArrayList<FuchsNPCData> fuchsNPCData = new ArrayList<>();
    public ArrayList<FuchsLocation> OPEN_CHESTS = new ArrayList<>();
    //public ArrayList<UUID> BLOCK_ARMORSTANDS = new ArrayList<>();
    public ArrayList<FuchsNPCData> getFuchsNPC() {
		return fuchsNPCData;
	}

}
