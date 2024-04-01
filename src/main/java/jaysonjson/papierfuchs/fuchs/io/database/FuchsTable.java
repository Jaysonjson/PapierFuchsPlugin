package jaysonjson.papierfuchs.fuchs.io.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FuchsTable {

    private String name = "";
    private String token = "";
    private String key = "";
    private Collection<FuchsTableEntry> entries = new ArrayList<>();
    public FuchsTable() {

    }

    public FuchsTable(String name, String token, String key) {
        this.name = name;
        this.token = token;
        this.key = key;
    }

    public FuchsTable(String name, String key, FuchsTableEntry... entries) {
        this.name = name;
        this.key = key;
        this.entries.addAll(Arrays.stream(entries).toList());

        StringBuilder tokenBuilder = new StringBuilder();
        tokenBuilder.append("CREATE TABLE IF NOT EXISTS '").append(name).append("' (");
        tokenBuilder.append("`").append(key).append("` ").append(FuchsTableEntryBase.VARCHAR32.getSql()).append(" NOT NULL,");
        int i = 1;
        for (FuchsTableEntry entry : this.entries) {
            //tokenBuilder.append("`").append(entry.getName()).append("` ").append(entry.getBase().getSql()).append(" NOT NULL,");
            tokenBuilder.append("`").append(entry.getName()).append("` ").append(entry.getBase().getSql()).append(" ,");
            i++;
            entry.setIndex(i);
        }
        tokenBuilder.append("PRIMARY KEY (`").append(key).append("`));");
        this.token = tokenBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Collection<FuchsTableEntry> getEntries() {
        return entries;
    }

    public void setEntries(Collection<FuchsTableEntry> entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return getName();
    }
}
