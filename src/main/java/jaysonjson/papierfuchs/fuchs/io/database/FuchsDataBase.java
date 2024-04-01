package jaysonjson.papierfuchs.fuchs.io.database;

import jaysonjson.papierfuchs.fuchs.utility.FuchsLog;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FuchsDataBase {

    //-> https://www.spigotmc.org/threads/how-to-sqlite.56847/?__cf_chl_jschl_tk__=0c486f4689825a5122195653918ecbad49b7499f-1626570149-0-ASr0dpCuGjLhGtdm0b-NgidPz69bkiU9mgx4O675ZP8HcvR6BDvSvai_dBDEr0uEbixRN2pLpAHjIaPHPgGkr-VixH5bsY3jiz1lUiQlJy2cq2SfA1ACnz-ocPQtDCA951XiqDltnJKc3uHJiv-xnWieYYlScndt5KrOniGcknPfe5QaNe6VSQQGTDKcTgfZB49YoDwt6AyVUDema4_IFKC0Guur4K9ExSfQgRBLU8yxb1YvyKBmKipaWjXlTovMrQLa0uxhdXrUdRklDUfSlkMH8Ijnpgn1rHIqeu4wkHMX1i2l2U04p-8t6C7h8jo2_8xGiU5fpFFiW4dxv3SEIaCqVc3hrwwJX9mxT-1jnEZB__NND8ex5G1-8K6ZIReU2jQzd7dS8tTjhA6ngu0FZ_P-ORW-_144loEz4iYZ65LGz2sNjY1-HQPnXNTtQ1AIog

    Connection connection;
    Collection<FuchsTable> tables = new ArrayList<>();
    String fileName;

    public FuchsDataBase(String fileName, FuchsTable... tables) {
        this.tables.addAll(Arrays.stream(tables).toList());
        this.fileName = fileName;
    }

    public FuchsDataBase(String fileName, Collection<FuchsTable> tables) {
        this.fileName = fileName;
        this.tables = tables;
    }

    public void initialize(){
        connection = getSQLConnection();
        try{
            for (FuchsTable table : tables) {
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM '" + table.getName() + "' WHERE '" + table.getKey() + "' = ?");
                ResultSet rs = ps.executeQuery();
                close(ps,rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Object getData(FuchsTable table, FuchsTableEntry entry, String keyValue) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM '" + table.getName() + "' WHERE " + table.getKey() + " = '" + keyValue + "';");

            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString(table.getKey()).equalsIgnoreCase(keyValue.toLowerCase())) {
                    FuchsTableEntryBase base = entry.getBase();
                    switch (base.getType()) {
                        case INT -> {
                            return rs.getInt(entry.getName());
                        }
                        case STR -> {
                            return rs.getString(entry.getName());
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public void updateData(FuchsTable table, FuchsTableEntry entry, String keyValue, Object object) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            //String statement = "REPLACE INTO " + table.getName() + " (" + table.getKey() + "," +
              //      entry.getName() + ") VALUES(?, ?)";
            //ps = conn.prepareStatement(statement);
            //ps.setString(1, keyValue);
            //ps.setInt(2, (Integer) object);
            //ps.executeUpdate();
            conn.prepareStatement("UPDATE '" + table.getName() + "' SET " + entry.getName() + " = '" + object + "' WHERE " + table.getKey() + " = '" + keyValue + "'").executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeData(FuchsTable table, String keyValue) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            conn.prepareStatement(" DELETE FROM '" + table.getName() +  "' WHERE " + table.getKey() + "='" + keyValue+  "'").executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<String> loopKeys(FuchsTable table, String entry) {
        ArrayList<String> keys = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ResultSet resultSet = conn.prepareStatement("SELECT " + entry + " FROM '" + table.getName() + "'").executeQuery();
            while (resultSet.next()) {
                keys.add(resultSet.getString(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return keys;
    }

    public void createData(FuchsTable table, String keyValue) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            StringBuilder statement = new StringBuilder();
            statement.append("REPLACE INTO '").append(table.getName()).append("' (").append(table.getKey()).append(",");
            int i = 0;
            for (FuchsTableEntry entry : table.getEntries()) {
                i++;
                statement.append(entry.getName());
                if(table.getEntries().size() != i) {
                    statement.append(",");
                }
            }
            i = 0;
            statement.append(") VALUES(?,");
            //statement.append("?,".repeat(table.getEntries().size()));
            for (int j = 0; j < table.getEntries().size(); j++) {
                i++;
                statement.append("?");
                if(table.getEntries().size() != i) {
                    statement.append(",");
                }
            }
            statement.append(")");
            ps = conn.prepareStatement(statement.toString());
            ps.setString(1, keyValue);
            for (FuchsTableEntry entry : table.getEntries()) {
                switch (entry.getBase().getType()) {
                    case STR -> {
                        ps.setString(entry.getIndex(), "");
                    }

                    case INT -> {
                        ps.setInt(entry.getIndex(), 0);
                    }
                }
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Deprecated
    private void setData(FuchsTable table, String keyValue, int index, Object object) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            StringBuilder statement = new StringBuilder();
            statement.append("REPLACE INTO '").append(table.getName()).append("' (").append(table.getKey()).append(",");
            int i = 0;
            for (FuchsTableEntry entry : table.getEntries()) {
                i++;
                statement.append(entry.getName());
                if(table.getEntries().size() != i) {
                    statement.append(",");
                }
            }
            i = 0;
            statement.append(") VALUES(?,");
            //statement.append("?,".repeat(table.getEntries().size()));
            for (int j = 0; j < table.getEntries().size(); j++) {
                i++;
                statement.append("?");
                if(table.getEntries().size() != i) {
                    statement.append(",");
                }
            }
            statement.append(")");
            ps = conn.prepareStatement(statement.toString());
            ps.setString(1, keyValue);
            ps.setInt(index, (Integer) object);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void close(PreparedStatement ps,ResultSet rs){
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            for (FuchsTable table : tables) {
                s.executeUpdate(table.getToken());
                for (FuchsTableEntry entry : table.getEntries()) {
                    DatabaseMetaData md = connection.getMetaData();
                    ResultSet rs = md.getColumns(null, null, table.getName(), entry.getName());
                    if (rs.next()) {
                        FuchsLog.log("Table geladen: " + table.getName() + " Column: " + entry.getName());
                    } else {
                        FuchsLog.warn("Column fehlt: " + entry.getName() + " in Table: " + table.getName() + " , erstellen...");
                        //s.executeUpdate("ALTER TABLE " + table.getName() + "ADD " + entry.getName() + " " + entry.getBase().getSql());
                        s.executeUpdate("ALTER TABLE test ADD " + entry.getName() + " " + entry.getBase().getSqlUp());
                    }
                }
            }
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }

    public Connection getSQLConnection() {
        File dataFolder = new File( fileName + ".db");
        boolean fileCreated = dataFolder.exists();
        if (!fileCreated){
            try {
                fileCreated = dataFolder.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(fileCreated) {
            try {
                if (connection != null && !connection.isClosed()) {
                    return connection;
                }
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
                return connection;
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

}
