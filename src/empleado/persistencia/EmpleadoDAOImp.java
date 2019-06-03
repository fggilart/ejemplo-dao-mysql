package empleado.persistencia;

import conexion.ConexionBD;
import empleado.dominio.Empleado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.vista.VistaTienda;
import util.Color;

public class EmpleadoDAOImp implements EmpleadoDAO {

    @Override
    public List<Empleado> leerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();

        String query = "SELECT * FROM empleados";

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement();
                var resultado = sentencia.executeQuery(query);) {

            // capturar los resultados
            while (resultado.next()) {
                var codigo = resultado.getInt("e_codigo");
                var nombre = resultado.getString("e_nombre");
                var apellidos = resultado.getString("e_apellidos");
                var password = resultado.getString("e_password");

                empleados.add(new Empleado(codigo, nombre, apellidos, password));
            }

        } catch (SQLException e) {
            System.out.println("Error al leer los empleados en la base de datos "
                    + this.getClass().getName());
        }

        return empleados;
    }

    @Override
    public Empleado getEmpleadoPorCodigo(int codigo) {
        Empleado empleado = null;
        String query = "SELECT * FROM empleados WHERE e_codigo = " + codigo;

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement();
                var resultado = sentencia.executeQuery(query)) {

            resultado.next();
            var code = resultado.getInt("e_codigo");
            var nombre = resultado.getString("e_nombre");
            var apellidos = resultado.getString("e_apellidos");
            var password = resultado.getString("e_password");
            empleado = new Empleado(code, nombre, apellidos, password);

        } catch (SQLException e) {
            VistaTienda.muestraMensaje("Error al cargar empleado con el codigo " + codigo, Color.ERROR);
        }

        return empleado;
    }

    @Override
    public boolean actualizarEmpleado(Empleado empleado, Empleado.Atributo... atributos) {
        boolean actualizado = false;

        String col_sql = "";

        String col_nombre_sql = "p_nombre = '" + empleado.getNombre() + "' ";
        String col_apellidos_sql = "p_descripcion = '" + empleado.getApellidos() + "' ";
        String col_password_sql = "p_precio = '" + empleado.getPassword() + "' ";

        String separador = (atributos.length == 1) ? "" : ", ";

        for (Empleado.Atributo atributo : atributos) {
            switch (atributo) {
                case NOMBRE:
                    col_sql += col_nombre_sql + separador;
                    break;
                case APELLIDOS:
                    col_sql += col_apellidos_sql + separador;
                    break;
                case PASSWORD:
                    col_sql += col_password_sql + separador;
                    break;
                case TODOS_LOS_ATRIBUTOS:
                    col_sql = col_nombre_sql + separador + col_apellidos_sql + separador + col_password_sql;
                    break;
            }
        }

        String query = "UPDATE productos SET " + col_sql + " WHERE p_codigo = '" + empleado.getCodigo() + "'";

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(query);

            actualizado = true;

        } catch (SQLException e) {
            VistaTienda.muestraMensaje("Error al actualizar el empleado con el codigo " + empleado.getCodigo(), Color.ERROR);
        }

        return actualizado;

    }

}
