package fsiAdministration.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
//    private static String url ="jdbc:postgresql://localhost:5432/FSI_GestionAdmin";
//
//    private static String user ="postgres";
//
//    private static String pass ="EfaZynWu";

    private static String url ="jdbc:postgresql://172.20.102.201:5432/P2025_FSI_G1";

    private static String user ="groupe1";

    private static String pass ="2SIO_ORT";

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
