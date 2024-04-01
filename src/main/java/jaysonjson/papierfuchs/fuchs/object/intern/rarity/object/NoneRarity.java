package jaysonjson.papierfuchs.fuchs.object.intern.rarity.object;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;

public class NoneRarity extends FuchsRarity {
    public NoneRarity(String id) {
        super(id);
    }

    @Override
    public int getTier() {
        return 0;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return FuchsLanguageString.c("");
    }

    @Override
    public float sellValueModifier() {
        return 0;
    }
}
