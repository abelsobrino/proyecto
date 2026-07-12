package pe.edu.utp.proyecto.service.empresa;

import pe.edu.utp.proyecto.modelo.empresa.Empleado;
import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    Empleado guardarEmpleado(Empleado empleado);
    Optional<Empleado> obtenerEmpleadoPorId(Integer id);
    List<Empleado> obtenerTodosLosEmpleados();
    Empleado actualizarEmpleado(Integer id, Empleado empleado);
    void eliminarEmpleado(Integer id);
}