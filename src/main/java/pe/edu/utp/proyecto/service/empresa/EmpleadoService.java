package pe.edu.utp.proyecto.service.empresa;

import pe.edu.utp.proyecto.modelo.empresa.Empleado;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de empleados.
 * Define las operaciones disponibles para la entidad Empleado.
 */
public interface EmpleadoService {

    /**
     * Guarda un nuevo empleado en el sistema.
     * @param empleado Datos del empleado a guardar.
     * @return Empleado guardado con su ID generado.
     */
    Empleado guardarEmpleado(Empleado empleado);

    /**
     * Busca un empleado por su ID.
     * @param id ID del empleado.
     * @return Optional con el empleado encontrado o vacio.
     */
    Optional<Empleado> obtenerEmpleadoPorId(Integer id);

    /**
     * Obtiene todos los empleados registrados.
     * @return Lista de todos los empleados.
     */
    List<Empleado> obtenerTodosLosEmpleados();

    /**
     * Actualiza los datos de un empleado existente.
     * @param id ID del empleado a actualizar.
     * @param empleado Datos actualizados del empleado.
     * @return Empleado actualizado.
     */
    Empleado actualizarEmpleado(Integer id, Empleado empleado);

    /**
     * Elimina un empleado del sistema.
     * @param id ID del empleado a eliminar.
     */
    void eliminarEmpleado(Integer id);

    /**
     * Busca empleados por su cargo.
     * @param cargo Cargo del empleado.
     * @return Lista de empleados con ese cargo.
     */
    List<Empleado> buscarPorCargo(String cargo);

    /**
     * Busca empleados cuyo apellido contenga un texto.
     * @param apellidos Texto a buscar en los apellidos.
     * @return Lista de empleados que coinciden.
     */
    List<Empleado> buscarPorApellidosContaining(String apellidos);
}