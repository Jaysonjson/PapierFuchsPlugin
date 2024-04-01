package jaysonjson.papierfuchs.fuchs.object.intern.skillclass;

import jaysonjson.papierfuchs.fuchs.io.data.DataHandler;
import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

public abstract class FuchsSkillclass<T extends FuchsSkillclassData> extends FuchsObject implements IFuchsSkillclass<T>, IFuchsDisplayName {


    T data;
    public FuchsSkillclass(String id, T data) {
        super(id, RegistryType.SKILLCLASS);
        this.data = data;
    }

    @Override
    public void loadData(FuchsPlayer fuchsPlayer) {
        if(!new File(FuchsSkillclassData.defaultPath(fuchsPlayer, this)).exists()) {
            saveData(fuchsPlayer);
        }
        data = DataHandler.gson.fromJson(DataHandler.readData(new File(FuchsSkillclassData.defaultPath(fuchsPlayer, this))), (Type) data.getClass());
    }

    @Override
    public void saveData(FuchsPlayer fuchsPlayer) {
        String json = DataHandler.gson.toJson(data);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FuchsSkillclassData.defaultPath(fuchsPlayer, this));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(json);
            outputStreamWriter.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public boolean isDisplayNameChangeable() {
        return false;
    }

    @Override
    public void editItemData(FuchsItemData fuchsItemData) {
    }

    public ItemStack createClassStack(FuchsPlayer fuchsPlayer) {
        ItemStack itemStack = new ItemStack(getItemMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (ItemFlag value : ItemFlag.values()) {
            itemMeta.addItemFlags(value);
        }
        itemMeta.setDisplayName(getDisplayName().get(fuchsPlayer));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
