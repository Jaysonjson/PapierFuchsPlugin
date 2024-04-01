package jaysonjson.papierfuchs.fuchs.object.intern.jobs;

import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

public abstract class FuchsJob<T extends FuchsJobData> extends FuchsObject implements IFuchsJob<T>, IFuchsDisplayName {

    T data;
    public FuchsJob(String id, T data) {
        super(id, RegistryType.JOB);
        this.data = data;
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void loadData(FuchsPlayer fuchsPlayer) {
        if(!new File(FuchsJobData.defaultPath(fuchsPlayer, this)).exists()) {
            saveData(fuchsPlayer);
        }
        data = DataHandler.gson.fromJson(DataHandler.readData(new File(FuchsJobData.defaultPath(fuchsPlayer, this))), (Type) data.getClass());
    }

    @Override
    public void saveData(FuchsPlayer fuchsPlayer) {
        String json = DataHandler.gson.toJson(data);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FuchsJobData.defaultPath(fuchsPlayer, this));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
