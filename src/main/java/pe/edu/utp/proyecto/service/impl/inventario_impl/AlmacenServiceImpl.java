package pe.edu.utp.proyecto.service.impl.inventario_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.inventario.Almacen;
import pe.edu.utp.proyecto.repository.inventario_repository.AlmacenRepository;
import pe.edu.utp.proyecto.service.inventario_service.AlmacenService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class AlmacenServiceImpl implements AlmacenService {

    @Autowired
    private AlmacenRepository almacenRepository;

    @Override
    @Transactional
    public Almacen guardarAlmacen(Almacen almacen) {
        log.info("Guardando nuevo almacén");
        return almacenRepository.save(almacen);
    }

    @Override
    public Optional<Almacen> obtenerAlmacenPorId(Integer id) {
        log.debug("Buscando almacén con ID: {}", id);
        return almacenRepository.findById(id);
    }

    @Override
    public List<Almacen> obtenerTodosLosAlmacenes() {
        log.info("Obteniendo todos los almacenes");
        return almacenRepository.findAll();
    }

    @Override
    @Transactional
    public Almacen actualizarAlmacen(Integer id, Almacen almacen) {
        log.info("Actualizando almacén con ID: {}", id);

        Almacen existente = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado con ID: " + id));

        existente.setNombre(almacen.getNombre());
        existente.setDireccion(almacen.getDireccion());
        existente.setResponsable(almacen.getResponsable());
        existente.setCapacidadMaxima(almacen.getCapacidadMaxima());

        return almacenRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarAlmacen(Integer id) {
        log.info("Eliminando almacén con ID: {}", id);
        almacenRepository.deleteById(id);
    }

    @Override
    public List<Almacen> obtenerAlmacenesPorNombre(String nombre) {
        log.info("Buscando almacenes por nombre: {}", nombre);
        return almacenRepository.findByNombreContaining(nombre);
    }

    @Override
    public List<Almacen> obtenerAlmacenesPorResponsable(String responsable) {
        log.info("Buscando almacenes por responsable: {}", responsable);
        return almacenRepository.findByResponsableContaining(responsable);
    }
}
