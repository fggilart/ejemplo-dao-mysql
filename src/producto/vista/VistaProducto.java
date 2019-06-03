package producto.vista;

import factura.Factura;
import java.util.List;
import producto.dominio.Producto;
import tienda.vista.VistaTienda;
import util.Color;

public class VistaProducto {

    public static int pedirCodigoDeLista() {
        return VistaTienda.pedirDatoInt("Introduce el código del producto: ");
    }

    public static int pedirNuevoCodigo() {
        return VistaTienda.pedirDatoInt("Introduce el nuevo código del producto: ");
    }

    public static String pedirNuevoNombre() {
        return VistaTienda.pedirDatoString("Introduzca el nuevo nombre del producto: ");
    }

    public static double pedirNuevoPrecio() {
        return VistaTienda.pedirDatoDouble("Introduzca el nuevo precio del producto: ");
    }

    public static void notificarActualizacionExito() {
        VistaTienda.muestraMensaje("Se ha actualizado correctamente los datos del producto", Color.EXITO);
    }

    public static void notificarActualizacionError() {
        VistaTienda.muestraMensaje("No se ha actualizado los datos del producto", Color.ERROR);
    }

    public static void notificarNoExisteProducto() {
        VistaTienda.muestraMensaje("El producto seleccionado no existe en la lista", Color.ERROR);
    }

    public static void notificarProductoAgregadoACesta() {
        VistaTienda.muestraMensaje("El producto seleccionado se ha agregado a la cesta", Color.EXITO);
    }
    
    public static void notificarCodigoRepetido() {
        VistaTienda.muestraMensaje("El codigo del producto ya existe", Color.ERROR);
    }
    
    public static void muestraPrecioCesta(double precio) {
        String precioConFormato = String.format("%.2f", precio);
        precioConFormato = VistaTienda.creaTextoColor(precioConFormato, Color.INFO);
        VistaTienda.muestraMensaje("El precio de la cesta es " + precioConFormato);
    }
    
    public static void muestraFactura(Factura factura){
        factura.imprimir();
    }

    public static void listarProductos(List<Producto> productos) {
        int contar = 1;
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%s:%n%n", "Productos");
        System.out.printf("%-5s%-12s%-30s%s%n", "#", "Código", "Nombre", "Precio");
        for (Producto producto : productos) {
            System.out.printf("%-5d%-12d%-30s%.2f€%n", contar, producto.getCodigo(), producto.getNombre(), producto.getPrecio());
            contar++;
        }
        System.out.println("-----------------------------------------------------------");
    }

}
