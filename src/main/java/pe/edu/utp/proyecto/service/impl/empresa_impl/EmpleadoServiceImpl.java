package pe.edu.utp.proyecto.service.impl.empresa_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.empresa.Empleado;
import pe.edu.utp.proyecto.repository.empresa_repository.EmpleadoRepository;
import pe.edu.utp.proyecto.service.empresa_service.EmpleadoService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    @Transactional
    public Empleado guardarEmpleado(Empleado empleado) {
        log.info("Guardando nuevo empleado");
        return empleadoRepository.save(empleado);
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoPorId(Integer id) {
        log.debug("Buscando empleado con ID: {}", id);
        return empleadoRepository.findById(id);
    }

    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        log.info("Obteniendo todos los empleados");
        return empleadoRepository.findAll();
    }

    @Override
    @Transactional
    public Empleado actualizarEmpleado(Integer id, Empleado empleado) {
        log.info("Actualizando empleado con ID: {}", id);

        Empleado existente = empleadoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Empleado no encontrado con ID: " + id));

        existente.setNombres(empleado.getNombres());
        existente.setApellidos(empleado.getApellidos());
        existente.setCargo(empleado.getCargo());

        return empleadoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarEmpleado(Integer id) {
        log.info("Eliminando empleado con ID: {}", id);
        empleadoRepository.deleteById(id);
    }
}