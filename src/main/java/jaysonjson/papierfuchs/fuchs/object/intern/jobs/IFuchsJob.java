package jaysonjson.papierfuchs.fuchs.object.intern.jobs;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;

public interface IFuchsJob<T extends FuchsJobData> {
    T getData();
    void loadData(FuchsPlayer fuchsPlayer);
    void saveData(FuchsPlayer fuchsPlayer);
}
