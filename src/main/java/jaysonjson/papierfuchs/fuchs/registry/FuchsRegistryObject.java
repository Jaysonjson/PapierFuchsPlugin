package jaysonjson.papierfuchs.fuchs.registry;

import jaysonjson.papierfuchs.fuchs.object.intern.block.FuchsBlock;
import jaysonjson.papierfuchs.fuchs.object.intern.entity.FuchsEntity;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.FuchsBlockItem;
import jaysonjson.papierfuchs.fuchs.object.intern.item.objects.generic.FuchsSpawnEggItem;
import jaysonjson.papierfuchs.fuchs.object.intern.usetype.UseTypeList;
import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class FuchsRegistryObject<T extends FuchsObject> implements Supplier<T> {

    T obj;

    public FuchsRegistryObject(Supplier<T> obj) {
        this.obj = obj.get();
    }

    public FuchsRegistryObject(T obj) {
        this.obj = obj;
    }

    /**
     * Just don't touch. Bad. Updates ID inside {@link FuchsRegistry#register(Class)}
     * @param fuchsPlugin Plugin
     */
    protected void register(IFuchsPlugin fuchsPlugin) {
        this.obj.setFuchsPlugin(fuchsPlugin);
        this.obj.setRegistryObject(this);
        if(obj instanceof FuchsBlock fuchsBlock) {
            if(fuchsBlock.asItem() == null) {
                FuchsRegistryObject<FuchsBlockItem> item = new FuchsRegistryObject<>(new FuchsBlockItem(fuchsBlock.getKey().getKey(), fuchsBlock.getMaterial(), UseTypeList.NONE.copy(), (FuchsRegistryObject<? extends FuchsBlock>) fuchsBlock.getRegistryObject(), fuchsBlock.getItemModelData()));
                getFuchsPlugin().getRegistry().registerItems(item);
                fuchsBlock.setItem(item);
                FuchsLog.log(ChatColor.RESET + "Fehlendes FuchsItem für FuchsBlock " + ChatColor.AQUA + fuchsBlock.getKey() + ChatColor.RESET +" ergänzt!");
            }
        }
        if(obj instanceof FuchsEntity<?> fuchsEntity) {
            if(fuchsEntity.shouldHaveSpawnegg() && fuchsEntity.getSpawnEgg() == null) {
                FuchsRegistryObject<FuchsSpawnEggItem> spawnEgg = new FuchsRegistryObject<>(new FuchsSpawnEggItem(fuchsEntity.getKey().getKey() + "_egg", Material.FEATHER, UseTypeList.NONE.copy(), fuchsEntity));
                getFuchsPlugin().getRegistry().registerItems(spawnEgg);
                fuchsEntity.setSpawnEgg(spawnEgg);
                FuchsLog.log(ChatColor.RESET + "Fehlendes Spawnegg für FuchsEntity " + ChatColor.AQUA + fuchsEntity.getKey() + ChatColor.RESET +" ergänzt!");
            }
        }
    }


    /**
     * Creates a Copy of said FuchsObject
     * @return T Object Param
     */
    @NotNull
    @Deprecated
    public T get() {
        try {
            T t = (T) obj.clone();
            //Keep ID fresh with Plugin ID!
            t.updateID();
            //t.setRegistryObject((FuchsRegistryObject<FuchsObject>) this);
            return t;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return (T) new FuchsObject("FALLBACK_" + e.getLocalizedMessage(), RegistryType.UNDEFINED);
        }
    }

    @NotNull
    @Deprecated
    public T g() {
        return get();
    }

    public String getID() {
        return get().getID();
    }

    public FuchsKey getKey() {
        return copy().getKey();
    }

    public String getRealID() {
        return get().getRealID();
    }

    public RegistryType getType() {
        return obj.getType();
    }

    public IFuchsPlugin getFuchsPlugin() {
        return obj.getFuchsPlugin();
    }

    public String id() {
        return getID();
    }

    public T copy() {
        return get();
    }
}

