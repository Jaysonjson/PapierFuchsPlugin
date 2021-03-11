package jaysonjson.papierfuchs.object;

import jaysonjson.papierfuchs.object.language.LanguageData;

@Deprecated
public enum Languages {
    GERMAN(),
    ENGLISH(),
    NOT_SET();

    LanguageData language;
    Languages() {
    }

    public void setLanguage(LanguageData language) {
        this.language = language;
    }

    public LanguageData getContent() {
        return language;
    }
}
