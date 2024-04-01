package jaysonjson.papierfuchs.fuchs.object.intern.effect;

import jaysonjson.papierfuchs.fuchs.object.IFuchsDisplayName;
import jaysonjson.papierfuchs.fuchs.object.IModelData;
import jaysonjson.papierfuchs.fuchs.object.intern.item.itemdata.FuchsItemData;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.FuchsRarity;
import jaysonjson.papierfuchs.fuchs.object.intern.rarity.RarityList;
import jaysonjson.papierfuchs.fuchs.registry.FuchsObject;
import jaysonjson.papierfuchs.fuchs.registry.RegistryType;

public abstract class FuchsEffect<T extends EffectItemData> extends FuchsObject implements IFuchsEffect<T>, IFuchsDisplayName, IModelData {

	public FuchsEffect(String id) {
		super(id, RegistryType.EFFECT);
	}

	@Override
	public FuchsRarity getRarity() {
		return RarityList.common.copy();
	}

	@Override
	public boolean isDisplayNameChangeable() {
		return false;
	}

	@Override
	public void editItemData(FuchsItemData fuchsItemData) {
		if(fuchsItemData.generalData.hasEffectData(getID())) {
			EffectItemData effectItemData = fuchsItemData.generalData.getEffectData(getID());
			if(effectItemData.getLevel() > 1) {
				fuchsItemData.addToLore(getDisplayName().get(fuchsItemData.fuchsPlayer) + " (" + effectItemData.getLevel() + ")");
			} else {
				fuchsItemData.addToLore(getDisplayName().get(fuchsItemData.fuchsPlayer));
			}
		} else {
			fuchsItemData.addToLore(getDisplayName().get(fuchsItemData.fuchsPlayer));
		}
	}
}
