package producto.control;

import java.util.List;
import producto.dominio.Producto;
import producto.persistencia.ProductoDAO;
import producto.persistencia.ProductoDAOImp;

public class ControladorProducto implements  ProductoDAO{

    @Override
    public List<Producto> leerProductos() {
        ProductoDAO pdao = new ProductoDAOImp();
        return pdao.leerProductos();
    }
    
    @Override
    public Producto getProductoPorCodigo(int codigo){
        ProductoDAO pdao = new ProductoDAOImp();
        return pdao.getProductoPorCodigo(codigo);
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        ProductoDAO pdao = new ProductoDAOImp();
        return pdao.actualizarProducto(producto);
    }

    @Override
    public boolean actualizarProducto(Producto producto, Producto.Atributo... atributos) {
        ProductoDAO pdao = new ProductoDAOImp();
        return pdao.actualizarProducto(producto, atributos);
    }

    @Override
    public boolean actualizarCodigoProducto(int codigoActual, int codigoNuevo) {
        ProductoDAO pdao = new ProductoDAOImp();
        return pdao.actualizarCodigoProducto(codigoActual, codigoNuevo);
    }

}
