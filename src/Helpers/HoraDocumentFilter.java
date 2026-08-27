package Helpers;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class HoraDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        if (string == null) {
            return;
        }

        replace(fb, offset, 0, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length,
            String text, AttributeSet attrs)
            throws BadLocationException {

        if (text == null) {
            return;
        }

        String actual = fb.getDocument().getText(0,fb.getDocument().getLength());

        String nuevo = actual.substring(0, offset)+ text+ actual.substring(offset + length);

        // Eliminar ":" para trabajar solamente con números
        String numeros = nuevo.replace(":", "");

        // No permitir letras, espacios ni otros caracteres
        if (!numeros.matches("\\d*")) {
            return;
        }

        // Máximo 4 dígitos
        if (numeros.length() > 4) {
            return;
        }

        // Formato HH:MM
        if (numeros.length() >= 3) {
            nuevo = numeros.substring(0, 2) + ":" + numeros.substring(2);
        } else {
            nuevo = numeros;
        }

        fb.replace(0,fb.getDocument().getLength(),nuevo,attrs);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length)
            throws BadLocationException {

        String actual = fb.getDocument().getText(0, fb.getDocument().getLength());

        String nuevo = actual.substring(0, offset) + actual.substring(offset + length);

        String numeros = nuevo.replace(":", "");

        if (numeros.length() <= 2) {
            nuevo = numeros;
        } else {
            nuevo = numeros.substring(0, 2) + ":" + numeros.substring(2);
        }

        fb.replace(0, fb.getDocument().getLength(), nuevo, null);
    }
}
