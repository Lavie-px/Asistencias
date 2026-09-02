package Helpers;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ClaveGenerator {

    public static void configurar(
            JTextField txtNombre,
            JTextField txtApellido,
            JTextField txtRut,
            JTextField txtClave) {

        DocumentListener listener = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                generar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                generar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                generar();
            }

            private void generar() {

                String nombre = txtNombre.getText().trim();
                String apellido = txtApellido.getText().trim();
                String rut = txtRut.getText().trim();

                // Si falta algún dato, limpiar la clave
                if (nombre.isEmpty() || apellido.isEmpty() || rut.isEmpty()) {

                    txtClave.setText("");
                    return;
                }

                // =========================
                // PRIMER NOMBRE
                // =========================

                String primerNombre = nombre.split("\\s+")[0];

                primerNombre = primerNombre.substring(0, 1).toUpperCase() + primerNombre.substring(1).toLowerCase();

                // =========================
                // PRIMER APELLIDO
                // =========================

                String primerApellido = apellido.split("\\s+")[0];

                char primeraLetraApellido =Character.toUpperCase(primerApellido.charAt(0));

                // =========================
                // RUT
                // =========================

                String rutLimpio = rut.replace(".", "").replace("-", "");

                if (rutLimpio.length() < 7) {
                    txtClave.setText("");
                    return;
                }

                // Primer dígito
                char primerDigito = rutLimpio.charAt(0);

                // Sexto dígito
                char sextoDigito = rutLimpio.charAt(5);

                // Dígito verificador
                char digitoVerificador = rutLimpio.charAt( rutLimpio.length() - 1);

                // =========================
                // GENERAR CLAVE
                // =========================

                String clave = primerNombre + primeraLetraApellido + primerDigito + sextoDigito + digitoVerificador;

                txtClave.setText(clave);
            }
        };

        txtNombre.getDocument().addDocumentListener(listener);
        txtApellido.getDocument().addDocumentListener(listener);
        txtRut.getDocument().addDocumentListener(listener);
    }
}