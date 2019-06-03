package producto.persistencia;

import java.util.List;
import producto.dominio.Producto;

public interface ProductoDAO {

    List<Producto> leerProductos();  //Read
    
    Producto getProductoPorCodigo(int codigo);

    boolean actualizarProducto(Producto producto, Producto.Atributo... atributos); // Update

    boolean actualizarProducto(Producto producto); // Update

    boolean actualizarCodigoProducto(int codigoActual, int codigoNuevo); // Update
}
