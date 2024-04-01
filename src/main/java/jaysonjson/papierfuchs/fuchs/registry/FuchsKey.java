package jaysonjson.papierfuchs.fuchs.registry;

import com.destroystokyo.paper.Namespaced;
import net.kyori.adventure.key.Key;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class FuchsKey implements Key, Namespaced {

    private final String key;
    private final String namespace;
    public boolean valid;
    private static final Pattern VALIDATOR = Pattern.compile("[a-z0-9/._-]{2,31}");

    public FuchsKey(String namespace, String key) {
        this.key = key;
        this.namespace = namespace;
        check();
    }

    public FuchsKey(FuchsRegistry fuchsRegistry, String key) {
        this.key = key;
        this.namespace = fuchsRegistry.getFuchsPlugin().getRegistryID();
        check();
    }

    public FuchsKey(IFuchsPlugin fuchsPlugin, String key) {
        this.key = key;
        this.namespace = fuchsPlugin.getRegistryID();
        check();
    }

    public FuchsKey(NamespacedKey key) {
        this.key = key.getKey();
        this.namespace = key.getNamespace();
        check();
    }

    public boolean valid() {
        return valid;
    }

    public void check() {
        valid = VALIDATOR.matcher(key).matches() && VALIDATOR.matcher(namespace).matches();
    }

    @Override
    public @NotNull String namespace() {
        return getNamespace();
    }

    @Override
    public @NotNull String value() {
        return getKey();
    }

    @Override
    public @NotNull String asString() {
        return getNamespace() + ":" + getKey();
    }

    @Override
    public String toString() {
        return asString();
    }

    @Override
    public @NotNull String getNamespace() {
        return namespace;
    }

    @Override
    public @NotNull String getKey() {
        return key;
    }
}
