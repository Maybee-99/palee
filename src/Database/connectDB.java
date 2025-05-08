
package Database;

import Model.message;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class connectDB {

    public static String selectedDB = "server"; 
    private static Connection connection = null;

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String LOCAL_URL = "jdbc:mysql://localhost:3306/dbpalee";
    private static final String LOCAL_USER = "root";
    private static final String LOCAL_PASSWORD = "@020mbdev";

    private static final String SERVER_URL = "jdbc:mysql://trolley.proxy.rlwy.net:51581/railway";
    private static final String SERVER_USER = "root";
    private static final String SERVER_PASSWORD = "PSdZLvQLTWJLWlkntomCVjQVgCHPUPfA";

    public static Connection getConnect() {
        return getConnect(selectedDB);
    }

    public static synchronized Connection getConnect(String dbType) {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            Class.forName(DRIVER);

            String url;
            String user;
            String password;

            if ("local".equalsIgnoreCase(dbType)) {
                url = LOCAL_URL;
                user = LOCAL_USER;
                password = LOCAL_PASSWORD;
            } else {
                url = SERVER_URL;
                user = SERVER_USER;
                password = SERVER_PASSWORD;
            }

            connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, new message("ບໍ່ພົບ Driver!"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, new message("ບໍ່ສາມາດເຊື່ອມຕໍ່ຖານຂໍ້ມູນ!"));
        }
        return null;
    }

    public static void resetConnection() {
        closeConnection();
        connection = getConnect(selectedDB);
    }

    public static void setDB(String db) {
        selectedDB = db;
        resetConnection();
    }

    public static Connection getServerConnection() {
        return getConnect("server");
    }

    public static Connection getLocalConnection() {
        return getConnect("local");
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            // Optional: log or notify
        }
    }
}
