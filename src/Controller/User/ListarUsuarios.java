/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.User;

import Model.Connect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jose9
 */
public class ListarUsuarios {
    
    private Connect connect;

    public ListarUsuarios() {
        connect = new Connect();
    }

    public ResultSet ListarTodos() {
        String sql = "SELECT * FROM LoginEmpleados";

        try {

            Connection ActiveCon = connect.getConexion();

            Statement SqlSentence = ActiveCon.createStatement();
            return SqlSentence.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al listar");
            return null;
        }
    }
}
