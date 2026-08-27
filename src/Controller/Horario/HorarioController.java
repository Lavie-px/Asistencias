/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.Horario;

import Model.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HorarioController {

    private Connect connect;

    public HorarioController() {
        connect = new Connect();
    }

    public String[] obtenerDirectiva() {

        String sql = """
            SELECT HoraEntrada, HoraSalida
            FROM Directivas
            WHERE IdDirectiva = 1
        """;

        String[] datos = new String[2];

        try {
            Connection con = connect.getConexion();

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // TIME de MySQL → HH:mm
                if (rs.getTime("HoraEntrada") != null) {
                    datos[0] = rs.getTime("HoraEntrada").toLocalTime().toString().substring(0, 5);
                }

                if (rs.getTime("HoraSalida") != null) {
                    datos[1] = rs.getTime("HoraSalida").toLocalTime().toString().substring(0, 5);
                }
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            System.err.println("Error obteniendo directiva: " + e.getMessage());
        }

        return datos;
    }

    public boolean guardarHorario(String horaEntrada, String horaSalida) {

        try {
            Connection con = connect.getConexion();

            String sqlExiste = """
            SELECT IdDirectiva
            FROM Directivas
            WHERE IdDirectiva = 1
        """;

            PreparedStatement psExiste = con.prepareStatement(sqlExiste);
            ResultSet rs = psExiste.executeQuery();

            boolean existe = rs.next();

            rs.close();
            psExiste.close();

            if (existe) {

                String sqlUpdate = """
                UPDATE Directivas
                SET HoraEntrada = COALESCE(NULLIF(?, ''), HoraEntrada),
                    HoraSalida = COALESCE(NULLIF(?, ''), HoraSalida)
                WHERE IdDirectiva = 1
            """;

                PreparedStatement ps = con.prepareStatement(sqlUpdate);

                if (horaEntrada == null || horaEntrada.isBlank()) {
                    ps.setNull(1, java.sql.Types.TIME);
                } else {
                    ps.setTime(1, java.sql.Time.valueOf(horaEntrada + ":00"));
                }

                if (horaSalida == null || horaSalida.isBlank()) {
                    ps.setNull(2, java.sql.Types.TIME);
                } else {
                    ps.setTime(2, java.sql.Time.valueOf(horaSalida + ":00"));
                }

                int filas = ps.executeUpdate();
                ps.close();

                return filas > 0;

            } else {

                String sqlInsert = """
                INSERT INTO Directivas
                    (IdDirectiva, HoraEntrada, HoraSalida)
                VALUES
                    (1, ?, ?)
                """;

                PreparedStatement ps = con.prepareStatement(sqlInsert);

                if (horaEntrada == null || horaEntrada.isBlank()) {
                    ps.setNull(1, java.sql.Types.TIME);
                } else {
                    ps.setTime(1, java.sql.Time.valueOf(horaEntrada + ":00"));
                }

                if (horaSalida == null || horaSalida.isBlank()) {
                    ps.setNull(2, java.sql.Types.TIME);
                } else {
                    ps.setTime(2, java.sql.Time.valueOf(horaSalida + ":00"));
                }

                int filas = ps.executeUpdate();
                ps.close();

                return filas > 0;
            }

        } catch (Exception e) {
            System.err.println("Error guardando horario: " + e.getMessage());
            return false;
        }
    }
}
