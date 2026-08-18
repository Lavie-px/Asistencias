package Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {

    public static final String URL = "jdbc:mysql://granalla.cl/granalla_ChemsHub?useSSL=false&allowPublicKeyRetrieval=true";
    public static final String USER = "granalla_Net";
    public static final String CLAVE = "GranallaNet";

    private static Connection con = null;

    public Connection getConexion() {

        try {
            
            // Cargar driver UNA sola vez
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Si la conexión NO existe o está cerrada la crea si no la reutiliza
            if (con == null || con.isClosed() || !con.isValid(2)) {
                con = DriverManager.getConnection(URL, USER, CLAVE);
                System.out.println("Conexion creada");
            } else {
                System.out.println("Conexion reutilizada");
            }

        } catch (Exception e) {
            System.err.println("Error conexion: " + e.getMessage());
        }

        return con;
    }

    public void throwcon() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Conexion cerrada");
            }
        } catch (Exception e) {
            System.err.println("Error cerrando conexión: " + e.getMessage());
        }
    }
}
