package jaysonjson.papierfuchs.fuchs.object.intern.npc;

public interface IFuchsNPC<T extends FuchsNPCData> {

    void onInteract();
    T getData();

}
