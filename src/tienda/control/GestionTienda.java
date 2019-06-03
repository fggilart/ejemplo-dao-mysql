package tienda.control;

import empleado.control.GestionaEmpleados;
import empleado.dominio.Empleado;
import java.util.ArrayList;
import java.util.List;
import producto.control.GestionaProductos;
import producto.dominio.Producto;
import tienda.vista.MenuPrincipal;
import tienda.vista.VistaTienda;

public class GestionTienda {

    private Empleado empleadoAutenticado;
    private List<Producto> cesta;
    GestionaEmpleados gestionaEmpleados;
    GestionaProductos gestionaProductos;

    public GestionTienda() {
        empleadoAutenticado = null;
        cesta = new ArrayList<>();
        gestionaEmpleados = new GestionaEmpleados();
        gestionaProductos = new GestionaProductos();
    }

    public void iniciar() {

        boolean terminarAplicacion = false;

        while (!terminarAplicacion) {

            login();

            sesion();
        }

    }

    private void login() {
        VistaTienda.borrarPantalla();

        boolean esLoginCorrecto = false;
        while (!esLoginCorrecto) {
            try {
                gestionaEmpleados.login();
                esLoginCorrecto = true;
            } catch (Exception e) {
            }
        }

        // establecer nueva sesion
        empleadoAutenticado = gestionaEmpleados.getEmpleadoAutenticado();
        gestionaProductos.setEmpleadoAutenticado(empleadoAutenticado);
        VistaTienda.setEmpleadoAutenticado(empleadoAutenticado);
    }

    private void sesion() {
        boolean esSesionAbierta = true;
        while (esSesionAbierta) {
            MenuPrincipal menu = VistaTienda.opcionDesdeMenuPrincipal();
            switch (menu) {
                case HACER_PEDIDO:
                    gestionaProductos.hacerPedido(cesta);
                    break;
                case MODIFICAR_PRODUCTO:
                    gestionaProductos.modificarProducto();
                    break;
                case CAMBIAR_PASSWORD:
                    gestionaEmpleados.cambiarPassword();
                    break;
                case CERRAR_SESION:
                    empleadoAutenticado = null;
                    esSesionAbierta = false;
                    break;
            }
        }
    }

}
