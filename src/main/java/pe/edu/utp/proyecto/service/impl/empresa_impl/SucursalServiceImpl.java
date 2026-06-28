package pe.edu.utp.proyecto.service.impl.empresa_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.empresa.Sucursal;
import pe.edu.utp.proyecto.repository.empresa_repository.SucursalRepository;
import pe.edu.utp.proyecto.service.empresa_service.SucursalService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    @Transactional
    public Sucursal guardarSucursal(Sucursal sucursal) {
        log.info("Guardando nueva sucursal");
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Optional<Sucursal> obtenerSucursalPorId(Integer id) {
        log.debug("Buscando sucursal con ID: {}", id);
        return sucursalRepository.findById(id);
    }

    @Override
    public List<Sucursal> obtenerTodasLasSucursales() {
        log.info("Obteniendo todas las sucursales");
        return sucursalRepository.findAll();
    }

    @Override
    @Transactional
    public Sucursal actualizarSucursal(Integer id, Sucursal sucursal) {
        log.info("Actualizando sucursal con ID: {}", id);

        Sucursal existente = sucursalRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Sucursal no encontrada con ID: " + id));

        existente.setNombre(sucursal.getNombre());
        existente.setDireccion(sucursal.getDireccion());

        return sucursalRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarSucursal(Integer id) {
        log.info("Eliminando sucursal con ID: {}", id);
        sucursalRepository.deleteById(id);
    }

    @Override
    public List<Sucursal> obtenerSucursalesPorNombre(String nombre) {
        log.info("Buscando sucursales por nombre: {}", nombre);
        return sucursalRepository.findByNombreContaining(nombre);
    }

    @Override
    public List<Sucursal> obtenerSucursalesPorDireccion(String direccion) {
        log.info("Buscando sucursales por dirección: {}", direccion);
        return sucursalRepository.findByDireccionContaining(direccion);
    }
}