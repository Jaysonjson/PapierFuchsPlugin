package jaysonjson.papierfuchs.old.other;

import java.util.ArrayList;

public class InventoryPageContainer<T> {
    public ArrayList<InventoryPage> pages = new ArrayList<>();
    public InventoryPage<T> addPage(InventoryPage<T> page) {
        page.index = size();
        pages.add(page);
        return page;
    }
    public InventoryPage<T> getPage(Integer index) {
        return this.pages.get(index);
    }
    public Integer size() {
        return pages.size() + 1;
    }
}
