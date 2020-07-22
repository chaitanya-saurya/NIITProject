package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConfiguration {
    public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String url="jdbc:sqlserver://CHAITANYA-LAPTO:1433;databaseName=swing_demo";
        String username="hello";
        String password="admin123";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn= DriverManager.getConnection(url,username,password);
        return conn;
    }
}

