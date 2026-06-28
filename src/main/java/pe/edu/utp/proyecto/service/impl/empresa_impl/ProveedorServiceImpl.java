package pe.edu.utp.proyecto.service.impl.empresa_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.empresa.Proveedor;
import pe.edu.utp.proyecto.repository.empresa_repository.ProveedorRepository;
import pe.edu.utp.proyecto.service.empresa_service.ProveedorService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Override
    @Transactional
    public Proveedor guardarProveedor(Proveedor proveedor) {
        log.info("Guardando nuevo proveedor");
        return proveedorRepository.save(proveedor);
    }

    @Override
    public Optional<Proveedor> obtenerProveedorPorId(Integer id) {
        log.debug("Buscando proveedor con ID: {}", id);
        return proveedorRepository.findById(id);
    }

    @Override
    public List<Proveedor> obtenerTodosLosProveedores() {
        log.info("Obteniendo todos los proveedores");
        return proveedorRepository.findAll();
    }

    @Override
    @Transactional
    public Proveedor actualizarProveedor(Integer id, Proveedor proveedor) {
        log.info("Actualizando proveedor con ID: {}", id);

        Proveedor existente = proveedorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Proveedor no encontrado con ID: " + id));

        existente.setRazonSocial(proveedor.getRazonSocial());
        existente.setRuc(proveedor.getRuc());
        existente.setTelefono(proveedor.getTelefono());

        return proveedorRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarProveedor(Integer id) {
        log.info("Eliminando proveedor con ID: {}", id);
        proveedorRepository.deleteById(id);
    }

    @Override
    public Proveedor obtenerProveedorPorRuc(String ruc) {
        log.info("Buscando proveedor por RUC: {}", ruc);
        return proveedorRepository.findByRuc(ruc);
    }

    @Override
    public List<Proveedor> obtenerProveedoresPorRazonSocial(String razonSocial) {
        log.info("Buscando proveedores por razón social: {}", razonSocial);
        return proveedorRepository.findByRazonSocialContaining(razonSocial);
    }
}