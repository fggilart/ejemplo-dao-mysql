package factura;

import empleado.dominio.Empleado;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import producto.dominio.Producto;
import tienda.vista.VistaTienda;
import util.Color;

public class FacturaEstandar implements Factura {

    private final List<Producto> productos;
    private final Empleado empleadoAutenticado;
    private double total;
    private final String facturaArchivo;
    StringBuilder factBuilder;

    public FacturaEstandar(List<Producto> productos, Empleado empleadoAutenticado) {
        this.productos = productos;
        this.empleadoAutenticado = empleadoAutenticado;
        total = 0;
        facturaArchivo = "factura.txt";
        factBuilder = new StringBuilder();
    }

    @Override
    public void imprimir() {

        generaFactura();
        System.out.println(factBuilder);
        guardar();

    }

    private void generaFactura() {

        // notificacion si no hay productos
        if (productos.isEmpty()) {
            factBuilder.append(VistaTienda.creaTextoColor("No introdujo ningún producto en la cesta\n", Color.ALERTA));
            VistaTienda.muestraMensaje("Se ha guardado correctamente la factura en el archivo " + facturaArchivo, Color.ALERTA);
        } else {

            calculaTotal();
            factBuilder.setLength(0);

            // cabecera
            factBuilder.append(String.format("%s%n", "-----------------------------------------------------------"));
            factBuilder.append(String.format("%s%n%n", "Factura"));
            // lista de productos en la cesta
            int i = 1;
            for (Producto p : productos) {
                factBuilder.append("[Producto " + i + "]\n");
                factBuilder.append(p.toString());
                factBuilder.append("\n");
                i++;
            }

            // precio y empleado
            String precioConFormato = String.format("%.2f", total);
            factBuilder.append(String.format("%s: %s €%n", "El precio TOTAL es", precioConFormato));
            factBuilder.append(String.format("%s: %s%n", "Atendido por", empleadoAutenticado.getNombre()));

            // pie de factura
            factBuilder.append(String.format("%s%n", "-----------------------------------------------------------"));
        }

    }

    private void calculaTotal() {
        double acumulado = 0;
        for (Producto p : productos) {
            acumulado += p.getPrecio();
        }
        total = acumulado;
    }

    @Override
    public void guardar() {
        try (var file = Files.newBufferedWriter(Paths.get(facturaArchivo), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            file.write(factBuilder.toString());

            VistaTienda.muestraMensaje("Se ha guardado correctamente la factura en el archivo " + facturaArchivo, Color.EXITO);

        } catch (IOException e) {
            VistaTienda.muestraMensaje("Error, no se ha podido guardar la factura en el archivo " + facturaArchivo, Color.ERROR);
        }

    }

}
