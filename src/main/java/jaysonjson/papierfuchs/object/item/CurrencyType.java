package jaysonjson.papierfuchs.object.item;

public enum CurrencyType {
    HACKSILVER("hacksilver"),
    ZORYHA_SHARD("zoryhaShard");

    String id;
    CurrencyType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
