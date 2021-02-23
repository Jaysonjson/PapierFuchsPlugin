package jaysonjson.papierfuchs.object;

import jaysonjson.papierfuchs.Language;

public enum Languages {
    GERMAN(),
    ENGLISH(),
    NOT_SET();

    Language language;
    Languages() {
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getContent() {
        return language;
    }
}
