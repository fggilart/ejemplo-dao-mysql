package empleado.control;

import empleado.dominio.Empleado;
import empleado.persistencia.EmpleadoDAO;
import empleado.persistencia.EmpleadoDAOImp;
import java.util.List;

public class ControladorEmpleado implements EmpleadoDAO {

    @Override
    public List<Empleado> leerEmpleados() {
        EmpleadoDAO edao = new EmpleadoDAOImp();
        return edao.leerEmpleados();
    }

    @Override
    public Empleado getEmpleadoPorCodigo(int codigo) {
        EmpleadoDAO edao = new EmpleadoDAOImp();
        return edao.getEmpleadoPorCodigo(codigo);
    }

    @Override
    public boolean actualizarEmpleado(Empleado empleado, Empleado.Atributo... atributos) {
        EmpleadoDAO edao = new EmpleadoDAOImp();
        return edao.actualizarEmpleado(empleado, atributos);
    }
}
