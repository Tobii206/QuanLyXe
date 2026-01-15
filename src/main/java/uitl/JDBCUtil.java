/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uitl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility to obtain JDBC Connections. Reads configuration from `/db.properties` on classpath.
 */
public class JDBCUtil {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        Properties props = new Properties();
        try (InputStream in = JDBCUtil.class.getResourceAsStream("/db.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Prefer environment variables when set (DB_DRIVER, DB_URL, DB_USER, DB_PASSWORD)
        String driver = System.getenv().getOrDefault("DB_DRIVER", props.getProperty("db.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver"));
        URL = System.getenv().getOrDefault("DB_URL", props.getProperty("db.url", "jdbc:sqlserver://localhost:1433;databaseName=QLBanXe"));
        USER = System.getenv().getOrDefault("DB_USER", props.getProperty("db.user", "sa"));
        PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", props.getProperty("db.password", "123"));

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
