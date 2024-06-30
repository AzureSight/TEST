package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class db {

    static Connection conn = null;

    public static Connection java_db() {
        String url = "jdbc:mysql://localhost:3306/espssFINAL";
        String username = "root", password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage(), "Connection Error!", 0);
            return null;
        }
    }
}
