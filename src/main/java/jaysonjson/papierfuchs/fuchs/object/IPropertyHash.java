package jaysonjson.papierfuchs.fuchs.object;

import java.util.HashMap;

public interface IPropertyHash {

    HashMap<String, Boolean> getProperties();

    default boolean getProperty(String property) {
        return getProperties().getOrDefault(property, false);
    }

    default void toggleProperty(String property) {
        setProperty(property, !getProperty(property));
    }

    default void setProperty(String property, boolean value) {
        if(value) {
            getProperties().put(property, true);
        } else {
            getProperties().remove(property);
        }
    }

}
