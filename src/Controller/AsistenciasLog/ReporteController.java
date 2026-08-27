package Controller.AsistenciasLog;

import Model.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReporteController {

    public ResultSet obtenerAtrasos() throws SQLException {
        Connect con = new Connect();
        Connection conexion = con.getConexion();
        if (conexion == null) {
            throw new SQLException("Sin conexión a la base de datos");
        }

        String sql = "SELECT m.Rut, "
                + "CONCAT(l.Nombre, ' ', l.Apellido) AS NombreCompleto, "
                + "m.fecha, m.hora "
                + "FROM marcaciones m "
                + "JOIN LoginEmpleados l ON m.Rut = l.Rut "
                + "CROSS JOIN Directivas d "
                + "WHERE m.tipo = 'entrada' "
                + "AND d.IdDirectiva = 1 "
                + "AND m.hora > d.HoraEntrada "
                + "ORDER BY m.fecha DESC";

        PreparedStatement ps = conexion.prepareStatement(sql);
        return ps.executeQuery();
    }

    public ResultSet obtenerSalidasAnticipadas() throws SQLException {
        Connect con = new Connect();
        Connection conexion = con.getConexion();
        if (conexion == null) {
            throw new SQLException("Sin conexión a la base de datos");
        }

        String sql = "SELECT m.Rut, "
                + "CONCAT(l.Nombre, ' ', l.Apellido) AS NombreCompleto, "
                + "m.fecha, m.hora "
                + "FROM marcaciones m "
                + "JOIN LoginEmpleados l ON m.Rut = l.Rut "
                + "WHERE m.tipo = 'salida' "
                + "AND m.hora < (SELECT HoraSalida FROM Directivas WHERE IdDirectiva = 1) "
                + "ORDER BY m.fecha DESC";

        PreparedStatement ps = conexion.prepareStatement(sql);
        return ps.executeQuery();
    }

    // MVP: reporta inasistencias del día actual (empleados sin ninguna marca hoy)
    public ResultSet obtenerInasistencias() throws SQLException {
        Connect con = new Connect();
        Connection conexion = con.getConexion();
        if (conexion == null) {
            throw new SQLException("Sin conexión a la base de datos");
        }

        String sql = "SELECT l.Rut, CONCAT(l.Nombre, ' ', l.Apellido) AS NombreCompleto, "
                + "CURDATE() AS fecha "
                + "FROM LoginEmpleados l "
                + "WHERE l.Rut NOT IN ("
                + "    SELECT Rut FROM marcaciones WHERE fecha = CURDATE()"
                + ")";

        PreparedStatement ps = conexion.prepareStatement(sql);
        return ps.executeQuery();
    }
}
