package producto.persistencia;

import conexion.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import producto.dominio.Producto;
import tienda.vista.VistaTienda;
import util.Color;

public class ProductoDAOImp implements ProductoDAO {

    @Override
    public List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<>();

        String query = "SELECT * FROM productos";

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement();
                var resultado = sentencia.executeQuery(query)) {

            // capturar los resultados
            while (resultado.next()) {
                var codigo = resultado.getInt("p_codigo");
                var nombre = resultado.getString("p_nombre");
                var descripcion = resultado.getString("p_descripcion");
                var precio = resultado.getDouble("p_precio");

                productos.add(new Producto(codigo, nombre, descripcion, precio));
            }

        } catch (SQLException e) {
            System.out.println("Error al leer los productos en la base de datos "
                    + this.getClass().getName());
        }
        

        return productos;
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        return actualizarProducto(producto, Producto.Atributo.TODOS_LOS_ATRIBUTOS);

    }

    @Override
    public boolean actualizarProducto(Producto producto, Producto.Atributo... atributos) {
        boolean actualizado = false;

        String col_sql = "";

        String col_nombre_sql = "p_nombre = '" + producto.getNombre() + "' ";
        String col_descripcion_sql = "p_descripcion = '" + producto.getDescripcion() + "' ";
        String col_precio_sql = "p_precio = '" + producto.getPrecio() + "' ";

        String separador = (atributos.length == 1) ? "" : ", ";

        for (Producto.Atributo atributo : atributos) {
            switch (atributo) {
                case NOMBRE:
                    col_sql += col_nombre_sql + separador;
                    break;
                case DESCRIPCION:
                    col_sql += col_descripcion_sql + separador;
                    break;
                case PRECIO:
                    col_sql += col_precio_sql + separador;
                    break;
                case TODOS_LOS_ATRIBUTOS:
                    col_sql = col_nombre_sql + separador + col_descripcion_sql + separador + col_precio_sql;
                    break;
            }
        }

        String query = "UPDATE productos SET " + col_sql + " WHERE p_codigo = '" + producto.getCodigo() + "'";

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(query);

            actualizado = true;

        } catch (SQLException e) {
            System.out.println(query);
            VistaTienda.muestraMensaje("Error al actualizar el producto con el codigo " + producto.getCodigo(), Color.ERROR);
            //e.printStackTrace();
        }

        return actualizado;

    }

    @Override
    public boolean actualizarCodigoProducto(int codigoActual, int codigoNuevo) {

        boolean actualizado = false;
        String query = "UPDATE productos SET p_codigo = '" + codigoNuevo + "' WHERE p_codigo = '" + codigoActual + "'";

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(query);

            actualizado = true;

        } catch (SQLException e) {
            VistaTienda.muestraMensaje("Error al actualizar el producto con el codigo " + codigoActual, Color.ERROR);
            e.printStackTrace();
        }

        return actualizado;

    }

    @Override
    public Producto getProductoPorCodigo(int codigo) {
        Producto producto = null;
        String query = "SELECT * FROM productos WHERE p_codigo = " + codigo;

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement();
                var resultado = sentencia.executeQuery(query)) {

            resultado.next();

            var code = resultado.getInt("p_codigo");
            var nombre = resultado.getString("p_nombre");
            var descripcion = resultado.getString("p_descripcion");
            var precio = resultado.getDouble("p_precio");
            producto = new Producto(codigo, nombre, descripcion, precio);

        } catch (SQLException e) {
            //VistaTienda.muestraMensaje("Error al cargar producto con el codigo " + codigo, Color.ERROR);
        }

        return producto;
    }

}
