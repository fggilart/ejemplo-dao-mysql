package empleado.vista;

import tienda.vista.VistaTienda;
import util.Color;

public class VistaEmpleado {

    public static int pedirCodigoEmpleado() {
        return VistaTienda.pedirDatoInt("Introduce el código de tu usuario: ");
    }

    public static String pedirPasswordEmpleado() {
        return VistaTienda.pedirDatoString("Introduce tu contraseña :");
    }

    public static String pedirNuevoPasswordEmpleado() {
        return VistaTienda.pedirDatoString("Introduce tu nueva contraseña :");
    }

    public static void notificarActualizacionExito() {
        VistaTienda.muestraMensaje("Se ha actualizado correctamente los datos del empleado", Color.EXITO);
    }

    public static void notificarActualizacionError() {
        VistaTienda.muestraMensaje("No se ha actualizado los datos del empleado", Color.ERROR);
    }
}
