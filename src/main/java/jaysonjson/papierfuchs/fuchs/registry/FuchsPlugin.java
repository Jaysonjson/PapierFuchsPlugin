package jaysonjson.papierfuchs.fuchs.registry;

import jaysonjson.papierfuchs.fuchs.object.intern.item.FuchsItem;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class FuchsPlugin extends JavaPlugin implements IFuchsPlugin {

    public final FuchsRegistry fuchsRegistry = new FuchsRegistry(this);

    @Override
    public FuchsRegistry getRegistry() {
        return fuchsRegistry;
    }

    @Override
    public FuchsItem getIcon() {
        for (FuchsRegistryObject<? extends FuchsObject> object : fuchsRegistry.objects) {
            if(object.copy() instanceof FuchsItem) {
                return (FuchsItem) object.copy();
            }
        }
        return null;
    }
}
