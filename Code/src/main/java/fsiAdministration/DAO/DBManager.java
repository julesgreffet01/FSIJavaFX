package fsiAdministration.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static String url ="jdbc:postgresql://localhost:5432/FSI_GestionAdmin";

    private static String user ="postgres";

    private static String pass ="cannelle01";

    private static Connection connect;

    public static Connection getInstance() {
        if (connect == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connect = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connect;
    }
}
