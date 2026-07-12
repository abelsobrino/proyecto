package pe.edu.utp.proyecto.service.empresa.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.empresa.Sucursal;
import pe.edu.utp.proyecto.repository.empresa.SucursalRepository;
import pe.edu.utp.proyecto.service.empresa.SucursalService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SucursalServiceImpl implements SucursalService {

    private final SucursalRepository sucursalRepository;

    @Override
    @Transactional
    public Sucursal guardarSucursal(Sucursal sucursal) {
        try {
            log.info("Guardando nueva sucursal: {}", sucursal.getNombre());
            return sucursalRepository.save(sucursal);
        } catch (Exception e) {
            log.error("Error al guardar sucursal: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la sucursal: " + e.getMessage());
        }
    }

    @Override
    public Optional<Sucursal> obtenerSucursalPorId(Integer id) {
        try {
            log.debug("Buscando sucursal con ID: {}", id);
            return sucursalRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar sucursal: {}", e.getMessage());
            throw new BusinessException("Error al buscar la sucursal: " + e.getMessage());
        }
    }

    @Override
    public List<Sucursal> obtenerTodasLasSucursales() {
        try {
            log.info("Obteniendo todas las sucursales");
            return sucursalRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener sucursales: {}", e.getMessage());
            throw new BusinessException("Error al obtener las sucursales: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Sucursal actualizarSucursal(Integer id, Sucursal sucursal) {
        try {
            log.info("Actualizando sucursal con ID: {}", id);
            Sucursal existente = sucursalRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Sucursal no encontrada con ID: " + id));
            existente.setNombre(sucursal.getNombre());
            existente.setDireccion(sucursal.getDireccion());
            return sucursalRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar sucursal: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la sucursal: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarSucursal(Integer id) {
        try {
            log.info("Eliminando sucursal con ID: {}", id);
            if (!sucursalRepository.existsById(id)) {
                throw new BusinessException("Sucursal no encontrada con ID: " + id);
            }
            sucursalRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar sucursal: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la sucursal: " + e.getMessage());
        }
    }

    @Override
    public List<Sucursal> obtenerSucursalesPorNombre(String nombre) {
        try {
            log.info("Buscando sucursales por nombre: {}", nombre);
            return sucursalRepository.findByNombreContaining(nombre);
        } catch (Exception e) {
            log.error("Error al buscar sucursales por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar las sucursales: " + e.getMessage());
        }
    }

    @Override
    public List<Sucursal> obtenerSucursalesPorDireccion(String direccion) {
        try {
            log.info("Buscando sucursales por direccion: {}", direccion);
            return sucursalRepository.findByDireccionContaining(direccion);
        } catch (Exception e) {
            log.error("Error al buscar sucursales por direccion: {}", e.getMessage());
            throw new BusinessException("Error al buscar las sucursales: " + e.getMessage());
        }
    }
}