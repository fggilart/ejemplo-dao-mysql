package empleado.persistencia;

import empleado.dominio.Empleado;
import java.util.List;

public interface EmpleadoDAO {

    List<Empleado> leerEmpleados();  //Read

    Empleado getEmpleadoPorCodigo(int codigo);
    
    boolean actualizarEmpleado(Empleado empleado, Empleado.Atributo... atributos); // Update
}
