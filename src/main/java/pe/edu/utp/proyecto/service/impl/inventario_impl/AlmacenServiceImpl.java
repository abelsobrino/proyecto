package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.inventario.Almacen;
import pe.edu.utp.proyecto.repository.inventario_repository.AlmacenRepository;
import pe.edu.utp.proyecto.service.inventario_service.AlmacenService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlmacenServiceImpl implements AlmacenService {

    private final AlmacenRepository almacenRepository;

    @Override
    @Transactional
    public Almacen guardarAlmacen(Almacen almacen) {
        try {
            log.info("Guardando nuevo almacen: {}", almacen.getNombre());
            return almacenRepository.save(almacen);
        } catch (Exception e) {
            log.error("Error al guardar almacen: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el almacen: " + e.getMessage());
        }
    }

    @Override
    public Optional<Almacen> obtenerAlmacenPorId(Integer id) {
        try {
            log.debug("Buscando almacen con ID: {}", id);
            return almacenRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar almacen: {}", e.getMessage());
            throw new BusinessException("Error al buscar el almacen: " + e.getMessage());
        }
    }

    @Override
    public List<Almacen> obtenerTodosLosAlmacenes() {
        try {
            log.info("Obteniendo todos los almacenes");
            return almacenRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener almacenes: {}", e.getMessage());
            throw new BusinessException("Error al obtener los almacenes: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Almacen actualizarAlmacen(Integer id, Almacen almacen) {
        try {
            log.info("Actualizando almacen con ID: {}", id);
            Almacen existente = almacenRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Almacen no encontrado con ID: " + id));
            existente.setNombre(almacen.getNombre());
            existente.setDireccion(almacen.getDireccion());
            existente.setResponsable(almacen.getResponsable());
            existente.setCapacidadMaxima(almacen.getCapacidadMaxima());
            return almacenRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar almacen: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el almacen: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarAlmacen(Integer id) {
        try {
            log.info("Eliminando almacen con ID: {}", id);
            if (!almacenRepository.existsById(id)) {
                throw new BusinessException("Almacen no encontrado con ID: " + id);
            }
            almacenRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar almacen: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el almacen: " + e.getMessage());
        }
    }

    @Override
    public List<Almacen> obtenerAlmacenesPorNombre(String nombre) {
        try {
            log.info("Buscando almacenes por nombre: {}", nombre);
            return almacenRepository.findByNombreContaining(nombre);
        } catch (Exception e) {
            log.error("Error al buscar almacenes por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar los almacenes: " + e.getMessage());
        }
    }

    @Override
    public List<Almacen> obtenerAlmacenesPorResponsable(String responsable) {
        try {
            log.info("Buscando almacenes por responsable: {}", responsable);
            return almacenRepository.findByResponsableContaining(responsable);
        } catch (Exception e) {
            log.error("Error al buscar almacenes por responsable: {}", e.getMessage());
            throw new BusinessException("Error al buscar los almacenes: " + e.getMessage());
        }
    }
}