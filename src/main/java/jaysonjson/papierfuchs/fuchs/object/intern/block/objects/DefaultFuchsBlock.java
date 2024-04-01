package jaysonjson.papierfuchs.fuchs.object.intern.block.objects;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;

public class DefaultFuchsBlock extends FuchsBlock {

    FuchsLanguageString languageString;
    public DefaultFuchsBlock(String id, FuchsLanguageString languageString) {
        super(id);
        this.languageString = languageString;
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return languageString;
    }

}
