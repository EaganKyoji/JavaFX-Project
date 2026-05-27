package Indodax;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SqlConnector {
    private static String URL;
    private static String User; 
    private static String Pass;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(".env"));
            URL  = props.getProperty("DB_URL");
            User = props.getProperty("DB_USER");
            Pass = props.getProperty("DB_PASS");
        } catch (Exception e) {
            throw new RuntimeException("File .env tidak ditemukan!", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, User, Pass);
    }
}
