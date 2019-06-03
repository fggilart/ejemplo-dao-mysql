package producto.control;

import empleado.dominio.Empleado;
import factura.FacturaEstandar;
import java.util.List;
import producto.dominio.Producto;
import producto.vista.VistaProducto;
import tienda.vista.MenuPedido;
import tienda.vista.MenuProducto;
import tienda.vista.VistaTienda;

public class GestionaProductos {

    private Empleado empleadoAutenticado;
    private final ControladorProducto controlador;

    public GestionaProductos() {
        this.empleadoAutenticado = null;
        controlador = new ControladorProducto();
    }

    public void setEmpleadoAutenticado(Empleado empleadoAutenticado) {
        this.empleadoAutenticado = empleadoAutenticado;
    }

    public void cambiarCodigo() {
        Producto productoSeleccionado = seleccionarProductoDeLista();
        int nuevoCodigo = VistaProducto.pedirNuevoCodigo();
        
        // verificar si el nuevo codigo ya existe
        Producto productoConNuevoCodigo = controlador.getProductoPorCodigo(nuevoCodigo);
        if (productoConNuevoCodigo == null) {
            boolean actualizado = controlador.actualizarCodigoProducto(productoSeleccionado.getCodigo(), nuevoCodigo);
            notificarActualizacion(actualizado);
        }
        else{
            VistaProducto.notificarCodigoRepetido();
            VistaTienda.esperarTeclaEnterContinuar();
        }

    }

    public void cambiarNombre() {
        Producto productoSeleccionado = seleccionarProductoDeLista();
        String nuevoNombre = VistaProducto.pedirNuevoNombre();
        productoSeleccionado.setNombre(nuevoNombre);

        boolean actualizado = controlador.actualizarProducto(productoSeleccionado, Producto.Atributo.NOMBRE);
        notificarActualizacion(actualizado);
    }

    public void cambiarPrecio() {
        Producto productoSeleccionado = seleccionarProductoDeLista();
        double nuevoPrecio = VistaProducto.pedirNuevoPrecio();
        productoSeleccionado.setPrecio(nuevoPrecio);

        boolean actualizado = controlador.actualizarProducto(productoSeleccionado, Producto.Atributo.PRECIO);
        notificarActualizacion(actualizado);
    }

    public void agregarProductoACesta(List<Producto> cesta) {
        Producto productoSeleccionado = seleccionarProductoDeLista();
        cesta.add(productoSeleccionado);
        VistaProducto.notificarProductoAgregadoACesta();
        VistaTienda.esperarTeclaEnterContinuar();
    }

    public void verPrecioCesta(List<Producto> cesta) {

        double acumulado = 0;
        for (Producto p : cesta) {
            acumulado += p.getPrecio();
        }
        VistaProducto.muestraPrecioCesta(acumulado);
        VistaTienda.esperarTeclaEnterContinuar();

    }

    public List<Producto> listarProductos() {
        List<Producto> productos = controlador.leerProductos();
        VistaProducto.listarProductos(productos);
        return productos;
    }

    private Producto seleccionarProductoDeLista() {
        List<Producto> productos = listarProductos();

        int codigo = VistaProducto.pedirCodigoDeLista();
        Producto productoSeleccionado = getProductoDeLista(productos, codigo);

        while (productoSeleccionado == null) {
            VistaProducto.notificarNoExisteProducto();
            codigo = VistaProducto.pedirCodigoDeLista();
            productoSeleccionado = getProductoDeLista(productos, codigo);
        }

        return productoSeleccionado;
    }

    private Producto getProductoDeLista(List<Producto> productos, int codigo) {
        for (Producto p : productos) {
            if (p.getCodigo() == codigo) {
                return p;
            }
        }
        return null;
    }

    public void verFactura(List<Producto> cesta) {
        var factura = new FacturaEstandar(cesta, empleadoAutenticado);
        VistaProducto.muestraFactura(factura);
        VistaTienda.esperarTeclaEnterContinuar();
    }

    public void modificarProducto() {
        boolean seguirModificando = true;
        while (seguirModificando) {
            MenuProducto menu = VistaTienda.opcionDesdeMenuProducto();
            switch (menu) {
                case VER_PRODUCTOS:
                    listarProductos();
                    VistaTienda.esperarTeclaEnterContinuar();
                    break;
                case CAMBIAR_CODIGO:
                    cambiarCodigo();
                    break;
                case CAMBIAR_NOMBRE:
                    cambiarNombre();
                    break;
                case CAMBIAR_PRECIO:
                    cambiarPrecio();
                    break;
                case CANCELAR:
                    seguirModificando = false;
                    break;
            }
        }

    }

    public void hacerPedido(List<Producto> cesta) {
        boolean esPedidoActivo = true;
        while (esPedidoActivo) {
            MenuPedido menu = VistaTienda.opcionDesdeMenuPedido();
            switch (menu) {
                case AGREGAR_PRODUCTO:
                    agregarProductoACesta(cesta);
                    break;
                case VER_PRECIO_CESTA:
                    verPrecioCesta(cesta);
                    break;
                case VER_FACTURA:
                    verFactura(cesta);
                    break;
                case TERMINAR_PEDIDO:
                    esPedidoActivo = false;
                    break;
            }
        }
    }

    private void notificarActualizacion(boolean actualizado) {

        if (actualizado) {
            VistaProducto.notificarActualizacionExito();
        } else {
            VistaProducto.notificarActualizacionError();
        }

        VistaTienda.esperarTeclaEnterContinuar();
    }
}
