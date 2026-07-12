package pe.edu.utp.proyecto.service.empresa.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.empresa.Proveedor;
import pe.edu.utp.proyecto.repository.empresa.ProveedorRepository;
import pe.edu.utp.proyecto.service.empresa.ProveedorService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Override
    @Transactional
    public Proveedor guardarProveedor(Proveedor proveedor) {
        try {
            log.info("Guardando nuevo proveedor: {}", proveedor.getRazonSocial());
            return proveedorRepository.save(proveedor);
        } catch (Exception e) {
            log.error("Error al guardar proveedor: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el proveedor: " + e.getMessage());
        }
    }

    @Override
    public Optional<Proveedor> obtenerProveedorPorId(Integer id) {
        try {
            log.debug("Buscando proveedor con ID: {}", id);
            return proveedorRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar proveedor: {}", e.getMessage());
            throw new BusinessException("Error al buscar el proveedor: " + e.getMessage());
        }
    }

    @Override
    public List<Proveedor> obtenerTodosLosProveedores() {
        try {
            log.info("Obteniendo todos los proveedores");
            return proveedorRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener proveedores: {}", e.getMessage());
            throw new BusinessException("Error al obtener los proveedores: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Proveedor actualizarProveedor(Integer id, Proveedor proveedor) {
        try {
            log.info("Actualizando proveedor con ID: {}", id);
            Proveedor existente = proveedorRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Proveedor no encontrado con ID: " + id));
            existente.setRazonSocial(proveedor.getRazonSocial());
            existente.setRuc(proveedor.getRuc());
            existente.setTelefono(proveedor.getTelefono());
            return proveedorRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar proveedor: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el proveedor: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarProveedor(Integer id) {
        try {
            log.info("Eliminando proveedor con ID: {}", id);
            if (!proveedorRepository.existsById(id)) {
                throw new BusinessException("Proveedor no encontrado con ID: " + id);
            }
            proveedorRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar proveedor: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el proveedor: " + e.getMessage());
        }
    }

    @Override
    public Proveedor obtenerProveedorPorRuc(String ruc) {
        try {
            log.info("Buscando proveedor por RUC: {}", ruc);
            return proveedorRepository.findByRuc(ruc);
        } catch (Exception e) {
            log.error("Error al buscar proveedor por RUC: {}", e.getMessage());
            throw new BusinessException("Error al buscar el proveedor: " + e.getMessage());
        }
    }

    @Override
    public List<Proveedor> obtenerProveedoresPorRazonSocial(String razonSocial) {
        try {
            log.info("Buscando proveedores por razon social: {}", razonSocial);
            return proveedorRepository.findByRazonSocialContaining(razonSocial);
        } catch (Exception e) {
            log.error("Error al buscar proveedores: {}", e.getMessage());
            throw new BusinessException("Error al buscar los proveedores: " + e.getMessage());
        }
    }
}