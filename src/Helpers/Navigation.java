/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Navigation {

    public static void Salir(Component parent) {
        int Opcion = JOptionPane.showConfirmDialog(parent, "Esta seguro de salir?", "Salir", 0);
        if (Opcion == 0) {
            System.exit(0);
        }
    }

    public static void Volver(JFrame actual, JFrame anterior) {
        int Opcion = JOptionPane.showConfirmDialog(actual, "Esta seguro de volver?", "Salir", JOptionPane.YES_NO_OPTION);
        if (Opcion == JOptionPane.YES_OPTION) {
            actual.dispose();
            anterior.setVisible(true);
        }
    }
    
    public static void Viajar(JFrame actual, JFrame anterior) {
        actual.dispose();
        anterior.setVisible(true);
        
    }
}
