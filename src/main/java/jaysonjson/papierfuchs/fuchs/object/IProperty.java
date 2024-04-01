package jaysonjson.papierfuchs.fuchs.object;

import java.util.ArrayList;

public interface IProperty extends IPropertyEmpty {

    ArrayList<String> properties = new ArrayList<>();

    @Override
    default ArrayList<String> getProperties() {
        return properties;
    }
}
