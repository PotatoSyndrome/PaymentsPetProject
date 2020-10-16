package org.epam.training.kocherhin.DAO;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {

    private static ConnectionProvider instance;
    private static String absolutePath;

    static {
        instance = new ConnectionProvider();
        absolutePath = instance.getPath();
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



    private String getFromProperties(String key) throws IOException {
        Properties property = new Properties();
        String path = absolutePath + "/app.properties";
        try(FileInputStream fis = new FileInputStream(path)) {
            property.load(fis);
            return property.getProperty("connection." + key);
        } catch (IOException e) {
            throw new IOException("Can`t reach app.properties file", e);
        }
    }


    private String getPath() { // костиль для підгружання пропертіс мавеном автоматично і все працювало без
                                // додаткових танців з бубном при переході від ідеї до томкату
                                // додатково використовується налаштування плагіну resources у pom.xml

        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath;
        try {
            fullPath = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            fullPath = path;
        }
        String[] pathArr = fullPath.split("/(?:WEB-INF|target)");
        fullPath = pathArr[0];

        return fullPath;

    }
}
