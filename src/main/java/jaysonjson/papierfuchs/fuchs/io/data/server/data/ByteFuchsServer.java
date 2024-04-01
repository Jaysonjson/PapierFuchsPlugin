package jaysonjson.papierfuchs.fuchs.io.data.server.data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class ByteFuchsServer implements Serializable {

    @Serial
    private static final long serialVersionUID = 0L;


    private ArrayList<FuchsReport> reports = new ArrayList<>();

    //private ArrayList<String> registeredObjects = new ArrayList<>();

    /*public ArrayList<String> getRegisteredObjects() {
        return registeredObjects;
    }*/

    public ArrayList<FuchsReport> getReports() {
        return reports;
    }
}
