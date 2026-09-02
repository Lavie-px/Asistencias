package Controller.User;

import Model.Connect;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import Controller.User.Usuario;

public class CrudUsuarios {

    private Connect connect;

    public CrudUsuarios() {
        connect = new Connect();
    }

    public void grabar(Usuario u) {

        String sql = "INSERT INTO LoginEmpleados(Rut, Nombre, Apellido, Correo, Contrasena, Rol)" + "VALUES (?,?,?,?,?,?)";

        try {

            Connection ActiveCon = connect.getConexion();

            PreparedStatement SqlSentence = ActiveCon.prepareStatement(sql);
            SqlSentence.setString(1, u.getRut());
            SqlSentence.setString(2, u.getNombre());
            SqlSentence.setString(3, u.getApellido());
            SqlSentence.setString(4, u.getCorreo());
            SqlSentence.setString(5, u.getClave());

            SqlSentence.setString(6, u.getTipoUsuario());

            SqlSentence.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos guardados con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "error al grabar", "ERROR", JOptionPane.ERROR_MESSAGE);

        }
    }

    public Usuario buscar(String rutBuscado) {
        String sql = "SELECT * FROM LoginEmpleados WHERE Rut = ?";
        try {

            Connection ActiveCon = connect.getConexion();

            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);
            consultaSQL.setString(1, rutBuscado);
            ResultSet rs = consultaSQL.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();

                u.setRut(rs.getString("Rut"));
                u.setNombre(rs.getString("Nombre"));
                u.setApellido(rs.getString("Apellido"));
                u.setCorreo(rs.getString("Correo"));
                u.setClave(rs.getString("Contrasena"));
                u.setTipoUsuario(rs.getString("Rol"));

                return u;
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al buscar: " + error.getMessage());
            return null;
        }
        return null;
    }

    public void actualizar(Usuario u) {
        String sql = "UPDATE LoginEmpleados SET Nombre=?, Apellido=?, Correo=?, Contrasena=?, Rol=?, WHERE Rut=?";

        try {
            Connection ActiveCon = connect.getConexion();

            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);

            consultaSQL.setString(1, u.getNombre());
            consultaSQL.setString(2, u.getApellido());
            consultaSQL.setString(3, u.getCorreo());
            consultaSQL.setString(4, u.getClave());
            consultaSQL.setString(5, u.getTipoUsuario());

            consultaSQL.setString(6, u.getRut());

            consultaSQL.executeUpdate();

            JOptionPane.showMessageDialog(null, "Datos actualizados correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al modificar " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eiliminar(String id) {
        String sql = "DELETE FROM LoginEmpleados WHERE Rut=?";

        try {
            Connection ActiveCon = connect.getConexion();

            PreparedStatement consultaSQL = ActiveCon.prepareStatement(sql);
            consultaSQL.setString(1, id);

            int filas = consultaSQL.executeUpdate();

            if (filas > 0) {
                JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el rut", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
