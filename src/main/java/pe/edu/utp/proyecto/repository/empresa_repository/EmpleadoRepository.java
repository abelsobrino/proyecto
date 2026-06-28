package pe.edu.utp.proyecto.repository.empresa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.utp.proyecto.modelo.empresa.Empleado;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    List<Empleado> findByCargo(String cargo);

    List<Empleado> findByApellidosContaining(String apellidos);
}