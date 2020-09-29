package org.epam.training.kocherhin.DAO;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

    private static ConnectionProvider instance;

    static {
        instance = new ConnectionProvider();
    }

    public static ConnectionProvider getInstance() {
        return instance;
    }

    private ConnectionProvider() {
    }

    public Connection getConnection() throws SQLException {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        try {
            ds.setUser(getFromProperties("user"));
            ds.setPassword(getFromProperties("password"));
            ds.setServerTimezone(getFromProperties("timezone"));
            ds.setURL(getFromProperties("url"));
        } catch (IOException e) {
            throw new SQLException("Can`t connect to database, missed properties", e);
        }
        return ds.getConnection();
    }

    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con
     *            Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private String getFromProperties(String key) throws IOException {
        Properties property = new Properties();
        try(FileInputStream fis = new FileInputStream("app.properties")) {
            property.load(fis);
            return property.getProperty("connection." + key);
        } catch (IOException e) {
            throw new IOException("Can`t reach app.properties file", e);
        }
    }


}
