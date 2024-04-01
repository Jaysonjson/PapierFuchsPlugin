package jaysonjson.papierfuchs.fuchs.object.intern.rarity;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;

public interface IFuchsRarity {
    int getTier();
    FuchsLanguageString getDisplayName();
    float sellValueModifier();
}
