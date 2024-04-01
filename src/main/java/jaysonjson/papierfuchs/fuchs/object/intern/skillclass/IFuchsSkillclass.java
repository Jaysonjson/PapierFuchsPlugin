package jaysonjson.papierfuchs.fuchs.object.intern.skillclass;

import jaysonjson.papierfuchs.fuchs.io.data.player.FuchsPlayer;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import org.bukkit.Material;

public interface IFuchsSkillclass<T extends FuchsSkillclassData> {

    void loadData(FuchsPlayer fuchsPlayer);

    T getData();

    void saveData(FuchsPlayer fuchsPlayer);

    void editItemData(FuchsItemData fuchsItemData);

    Material getItemMaterial();
}
