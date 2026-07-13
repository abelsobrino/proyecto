package pe.edu.utp.proyecto.service.empresa.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.empresa.Empleado;
import pe.edu.utp.proyecto.repository.empresa.EmpleadoRepository;
import pe.edu.utp.proyecto.service.empresa.EmpleadoService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de empleados.
 * Contiene la logica de negocio para la gestion de empleados.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    /**
     * Guarda un nuevo empleado.
     * @param empleado Datos del empleado.
     * @return Empleado guardado.
     */
    @Override
    @Transactional
    public Empleado guardarEmpleado(Empleado empleado) {
        try {
            log.info("Guardando nuevo empleado: {}", empleado.getNombres());
            return empleadoRepository.save(empleado);
        } catch (Exception e) {
            log.error("Error al guardar empleado: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el empleado: " + e.getMessage());
        }
    }

    /**
     * Busca un empleado por su ID.
     * @param id ID del empleado.
     * @return Optional con el empleado encontrado.
     */
    @Override
    public Optional<Empleado> obtenerEmpleadoPorId(Integer id) {
        try {
            log.debug("Buscando empleado con ID: {}", id);
            return empleadoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar empleado: {}", e.getMessage());
            throw new BusinessException("Error al buscar el empleado: " + e.getMessage());
        }
    }

    /**
     * Obtiene todos los empleados.
     * @return Lista de empleados.
     */
    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        try {
            log.info("Obteniendo todos los empleados");
            return empleadoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener empleados: {}", e.getMessage());
            throw new BusinessException("Error al obtener los empleados: " + e.getMessage());
        }
    }

    /**
     * Actualiza un empleado existente.
     * @param id ID del empleado.
     * @param empleado Datos actualizados.
     * @return Empleado actualizado.
     */
    @Override
    @Transactional
    public Empleado actualizarEmpleado(Integer id, Empleado empleado) {
        try {
            log.info("Actualizando empleado con ID: {}", id);
            Empleado existente = empleadoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Empleado no encontrado con ID: " + id));
            existente.setNombres(empleado.getNombres());
            existente.setApellidos(empleado.getApellidos());
            existente.setCargo(empleado.getCargo());
            return empleadoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar empleado: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el empleado: " + e.getMessage());
        }
    }

    /**
     * Elimina un empleado.
     * @param id ID del empleado a eliminar.
     */
    @Override
    @Transactional
    public void eliminarEmpleado(Integer id) {
        try {
            log.info("Eliminando empleado con ID: {}", id);
            if (!empleadoRepository.existsById(id)) {
                throw new BusinessException("Empleado no encontrado con ID: " + id);
            }
            empleadoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar empleado: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el empleado: " + e.getMessage());
        }
    }

    /**
     * Busca empleados por cargo.
     * @param cargo Cargo del empleado.
     * @return Lista de empleados.
     */
    @Override
    public List<Empleado> buscarPorCargo(String cargo) {
        try {
            log.info("Buscando empleados por cargo: {}", cargo);
            return empleadoRepository.findByCargo(cargo);
        } catch (Exception e) {
            log.error("Error al buscar empleados por cargo: {}", e.getMessage());
            throw new BusinessException("Error al buscar empleados: " + e.getMessage());
        }
    }

    /**
     * Busca empleados cuyo apellido contenga un texto.
     * @param apellidos Texto a buscar en los apellidos.
     * @return Lista de empleados.
     */
    @Override
    public List<Empleado> buscarPorApellidosContaining(String apellidos) {
        try {
            log.info("Buscando empleados por apellido que contenga: {}", apellidos);
            return empleadoRepository.findByApellidosContaining(apellidos);
        } catch (Exception e) {
            log.error("Error al buscar empleados por apellido: {}", e.getMessage());
            throw new BusinessException("Error al buscar empleados: " + e.getMessage());
        }
    }
}