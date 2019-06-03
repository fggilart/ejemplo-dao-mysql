package empleado.control;

import empleado.dominio.Empleado;
import empleado.vista.VistaEmpleado;
import tienda.vista.VistaTienda;
import util.Color;

public class GestionaEmpleados {

    ControladorEmpleado controlador;
    Empleado empleadoAutenticado;

    public GestionaEmpleados() {
        this.controlador = new ControladorEmpleado();
        this.empleadoAutenticado = null;
    }

    public void login() throws Exception {
        boolean esEmpleadoValido = false;
        boolean esPasswordValido = false;

        int codigoEntrada = VistaEmpleado.pedirCodigoEmpleado();
        String passwordEntrada = VistaEmpleado.pedirPasswordEmpleado();

        empleadoAutenticado = controlador.getEmpleadoPorCodigo(codigoEntrada);
        if (empleadoAutenticado != null) {
            esEmpleadoValido = true;
            if (passwordEntrada.equals(empleadoAutenticado.getPassword())) {
                esPasswordValido = true;
            }
        }

        if (!esEmpleadoValido) {
            //throw new ;
            VistaTienda.muestraMensaje("Usuario incorrecto", Color.ERROR);
            throw new Exception();
        } else if (!esPasswordValido) {
            //throw new ;
            VistaTienda.muestraMensaje("Contraseña incorrecta", Color.ERROR);
            throw new Exception();
        }

    }

    public Empleado getEmpleadoAutenticado() {
        return empleadoAutenticado;
    }

    public void cambiarPassword() {
        String nuevoPassword = VistaEmpleado.pedirNuevoPasswordEmpleado();
        empleadoAutenticado.setPassword(nuevoPassword);

        if (controlador.actualizarEmpleado(empleadoAutenticado, Empleado.Atributo.PASSWORD)) {
            VistaEmpleado.notificarActualizacionExito();
        } else {
            VistaEmpleado.notificarActualizacionError();
        }

        VistaTienda.esperarTeclaEnterContinuar();
    }

}
