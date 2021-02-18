package jaysonjson.papierfuchs.data.server.data;

import jaysonjson.papierfuchs.data.FuchsLocation;
import jaysonjson.papierfuchs.data.server.obj.FuchsNPC;

import java.util.ArrayList;

public class FuchsServer {

    public ArrayList<FuchsNPC> fuchsNPC = new ArrayList<>();
    public ArrayList<FuchsLocation> OPEN_CHESTS = new ArrayList<>();
    public ArrayList<EntityMetadataSetter> ENTITY_METADATA = new ArrayList<>();
    public ArrayList<BlockMetadataSetter> BLOCK_METADATA = new ArrayList<>();
    //public ArrayList<UUID> BLOCK_ARMORSTANDS = new ArrayList<>();
    public ArrayList<FuchsNPC> getFuchsNPC() {
		return fuchsNPC;
	}
}
