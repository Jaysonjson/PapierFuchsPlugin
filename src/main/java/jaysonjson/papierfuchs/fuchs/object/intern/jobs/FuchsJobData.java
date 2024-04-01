package jaysonjson.papierfuchs.fuchs.object.intern.jobs;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;

import java.io.File;

public class FuchsJobData {
    public static String defaultPath(FuchsPlayer fuchsPlayer, FuchsJob<?> fuchsJob) {
        new File(fuchsPlayer.getPath() + "jobs/" + fuchsJob.getFuchsPlugin().getRegistryID() + "/").mkdirs();
        return fuchsPlayer.getPath() + "jobs/" +  fuchsJob.getFuchsPlugin().getRegistryID() + "/" + fuchsJob.getRealID() + ".json";
    }
}
