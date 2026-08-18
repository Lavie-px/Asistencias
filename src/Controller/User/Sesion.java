
package Controller.User;

public class Sesion {

    private static String Correo;
    private static String Rut;
    private static String tipoUsuario;
    private static String nombreReal;
    private static String apellidoReal;   

    public static String getCorreo() {
        return Correo;
    }

    public static void setCorreo(String Correo) {
        Sesion.Correo = Correo;
    }

    public static String getRut() {
        return Rut;
    }

    public static void setRut(String Rut) {
        Sesion.Rut = Rut;
    }

    public static String getTipoUsuario() { 
        return tipoUsuario;
    }

    public static void setTipoUsuario(String tipoUsuario) {
        Sesion.tipoUsuario = tipoUsuario;
    }

    public static String getNombreReal() {
        return nombreReal;
    }

    public static void setNombreReal(String nombreReal) {
        Sesion.nombreReal = nombreReal;
    }

    public static String getApellidoReal() {
        return apellidoReal;
    }

    public static void setApellidoReal(String apellidoReal) {
        Sesion.apellidoReal = apellidoReal;
    }

    public static String NombreCompleto(String Nombre, String Apellido){
        return Nombre+""+Apellido;
    }
    
}

