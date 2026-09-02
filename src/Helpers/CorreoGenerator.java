package Helpers;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CorreoGenerator {

    public static void configurar(
            JTextField txtNombre,
            JTextField txtApellido,
            JTextField txtRut,
            JTextField txtCorreo) {

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

                if (nombre.isEmpty()|| apellido.isEmpty() || rut.isEmpty()) {
                    txtCorreo.setText("");
                    return;
                }

                // Primera letra del nombre
                String primeraNombre = nombre.substring(0, 1).toUpperCase();

                // Obtener el primer apellido
                String primerApellido = apellido.split("\\s+")[0];

                // Primera letra mayúscula + resto minúscula
                primerApellido = primerApellido.substring(0, 1).toUpperCase() + primerApellido.substring(1).toLowerCase();

                // Limpiar RUT
                String rutLimpio = rut.replace(".", "").replace("-", "");

                if (rutLimpio.length() < 7) {
                    txtCorreo.setText("");
                    return;
                }

                // Primer dígito
                char primerDigito =
                        rutLimpio.charAt(0);

                // Sexto dígito
                char sextoDigito =
                        rutLimpio.charAt(5);

                // Dígito verificador
                char digitoVerificador =
                        rutLimpio.charAt(rutLimpio.length() - 1);

                // Construir correo
                String correo = primeraNombre + "." + primerApellido + primerDigito+ sextoDigito + digitoVerificador + "@gmail.com";

                txtCorreo.setText(correo);
            }
        };

        txtNombre.getDocument().addDocumentListener(listener);
        txtApellido.getDocument().addDocumentListener(listener);
        txtRut.getDocument().addDocumentListener(listener);
    }
}