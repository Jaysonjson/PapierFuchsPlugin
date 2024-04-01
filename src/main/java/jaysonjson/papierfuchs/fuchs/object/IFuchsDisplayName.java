package jaysonjson.papierfuchs.fuchs.object;

public interface IFuchsDisplayName {

    String DISPLAY_NO_NAME = "FUCHS_NO_NAME";
    String PLACEHOLDER = "%s";

    FuchsLanguageString getDisplayName();
    boolean isDisplayNameChangeable();
    default void setDisplayName(FuchsLanguageString displayName) {
        System.out.println("Leeres setDisplayName");
    }
    default boolean hasLanguageString() {
        return !getDisplayName().getLanguage().equals("");
    }
    default boolean hasDisplayName() {
        return !getDisplayName().getMain().equals("") && !getDisplayName().getMain().equalsIgnoreCase(DISPLAY_NO_NAME);
    }
}
