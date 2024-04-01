package jaysonjson.papierfuchs.fuchs.io.database;

public class FuchsTableEntry {

    private String name = "";
    private FuchsTableEntryBase base;
    private Integer index;
    public FuchsTableEntry() {

    }

    public FuchsTableEntry(String name, FuchsTableEntryBase base) {
        this.name = name;
        this.base = base;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public FuchsTableEntryBase getBase() {
        return base;
    }

    public void setBase(FuchsTableEntryBase base) {
        this.base = base;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
