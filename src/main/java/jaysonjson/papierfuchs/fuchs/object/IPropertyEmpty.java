package jaysonjson.papierfuchs.fuchs.object;

import java.util.ArrayList;

public interface IPropertyEmpty {

    ArrayList<String> getProperties();

    default boolean getProperty(String property) {
        return getProperties().contains(property);
    }

    default void toggleProperty(String property) {
        setProperty(property, !getProperty(property));
    }

    default void setProperty(String property, boolean value) {
        if(value) {
            getProperties().add(property);
        } else {
            getProperties().remove(property);
        }
    }

}
