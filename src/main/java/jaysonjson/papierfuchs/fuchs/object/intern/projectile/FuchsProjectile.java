package jaysonjson.papierfuchs.fuchs.object.intern.projectile;

import jaysonjson.papierfuchs.fuchs.object.FuchsLanguageString;
import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;

public abstract class FuchsProjectile extends FuchsObject implements IFuchsProjectile, IFuchsDisplayName {

    private FuchsLanguageString displayName = new FuchsLanguageString(DISPLAY_NO_NAME);
    public FuchsProjectile(String id) {
        super(id, RegistryType.PROJECTILE);
    }

    @Override
    public FuchsLanguageString getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(FuchsLanguageString displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }
}
