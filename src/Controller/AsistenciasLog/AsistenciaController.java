package Controller.AsistenciasLog;

import Model.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaController {

    // true = se marcó correctamente, false = ya existía marca de ese tipo hoy
    public boolean marcarEntrada(String rut) throws SQLException {
        if (yaMarcoHoy(rut, "entrada")) {
            return false;
        }
        insertarMarcacion(rut, "entrada");
        return true;
    }

    public boolean marcarSalida(String rut) throws SQLException {
        if (!yaMarcoHoy(rut, "entrada")) {
            // No puede marcar salida sin haber marcado entrada
            return false;
        }
        if (yaMarcoHoy(rut, "salida")) {
            return false;
        }
        insertarMarcacion(rut, "salida");
        return true;
    }

    private boolean yaMarcoHoy(String rut, String tipo) throws SQLException {
        Connect con = new Connect();
        Connection conexion = con.getConexion();
        if (conexion == null) throw new SQLException("Sin conexión a la base de datos");

        String sql = "SELECT * FROM marcaciones WHERE Rut = ? AND tipo = ? AND fecha = CURDATE()";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, rut);
        ps.setString(2, tipo);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    private void insertarMarcacion(String rut, String tipo) throws SQLException {
        Connect con = new Connect();
        Connection conexion = con.getConexion();
        if (conexion == null) throw new SQLException("Sin conexión a la base de datos");

        String sql = "INSERT INTO marcaciones (Rut, tipo, fecha, hora) VALUES (?, ?, CURDATE(), CURTIME())";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, rut);
        ps.setString(2, tipo);
        ps.executeUpdate();
    }
}