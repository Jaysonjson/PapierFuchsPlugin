package jaysonjson.papierfuchs.fuchs.io.database;

public enum FuchsTableEntryBase {

    INT11("int(11)", "INT", FuchsTableEntryBaseType.INT),
    VARCHAR32("varchar(32)", "char", FuchsTableEntryBaseType.STR)
    ;

    private final String sql;
    private final String sqlUp;
    private final FuchsTableEntryBaseType type;
    FuchsTableEntryBase(String sql, String sqlUp, FuchsTableEntryBaseType type) {
        this.sql = sql;
        this.type = type;
        this.sqlUp = sqlUp;
    }

    public String getSql() {
        return sql;
    }

    public FuchsTableEntryBaseType getType() {
        return type;
    }

    public String getSqlUp() {
        return sqlUp;
    }
}
