package pe.edu.utp.proyecto.repository.empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.empresa.Empleado;

import java.util.List;

/**
 * Repositorio para la entidad Empleado.
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    /**
     * Busca empleados por su cargo.
     * @param cargo Cargo del empleado.
     * @return Lista de empleados con ese cargo.
     */
    List<Empleado> findByCargo(String cargo);

    /**
     * Busca empleados cuyo apellido contenga un texto.
     * @param apellidos Texto a buscar en los apellidos.
     * @return Lista de empleados que coinciden.
     */
    List<Empleado> findByApellidosContaining(String apellidos);
}