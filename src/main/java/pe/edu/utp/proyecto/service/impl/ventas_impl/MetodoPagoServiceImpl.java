package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.ventas.MetodoPago;
import pe.edu.utp.proyecto.repository.ventas_repository.MetodoPagoRepository;
import pe.edu.utp.proyecto.service.ventas_service.MetodoPagoService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class MetodoPagoServiceImpl implements MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Override
    @Transactional
    public MetodoPago guardarMetodoPago(MetodoPago metodoPago) {
        log.info("Guardando nuevo método de pago: {}", metodoPago.getNombreMetodo());

        if (metodoPagoRepository.findByNombreMetodo(metodoPago.getNombreMetodo()).isPresent()) {
            throw new BusinessException("Ya existe un método de pago con el nombre: " + metodoPago.getNombreMetodo());
        }

        return metodoPagoRepository.save(metodoPago);
    }

    @Override
    public Optional<MetodoPago> obtenerMetodoPagoPorId(Integer id) {
        log.debug("Buscando método de pago con ID: {}", id);
        return metodoPagoRepository.findById(id);
    }

    @Override
    public List<MetodoPago> obtenerTodosLosMetodosPago() {
        log.info("Obteniendo todos los métodos de pago");
        return metodoPagoRepository.findAll();
    }

    @Override
    @Transactional
    public MetodoPago actualizarMetodoPago(Integer id, MetodoPago metodoPago) {
        log.info("Actualizando método de pago con ID: {}", id);

        MetodoPago existente = metodoPagoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Método de pago no encontrado con ID: " + id));

        if (!existente.getNombreMetodo().equals(metodoPago.getNombreMetodo())) {
            Optional<MetodoPago> conNombre = metodoPagoRepository.findByNombreMetodo(metodoPago.getNombreMetodo());
            if (conNombre.isPresent() && conNombre.get().getIdMetodo() != id) {
                throw new BusinessException("Ya existe otro método con el nombre: " + metodoPago.getNombreMetodo());
            }
        }

        existente.setNombreMetodo(metodoPago.getNombreMetodo());
        existente.setDescripcion(metodoPago.getDescripcion());

        return metodoPagoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarMetodoPago(Integer id) {
        log.info("Eliminando método de pago con ID: {}", id);
        metodoPagoRepository.deleteById(id);
    }

    @Override
    public Optional<MetodoPago> obtenerMetodoPorNombre(String nombre) {
        log.debug("Buscando método de pago por nombre: {}", nombre);
        return metodoPagoRepository.findByNombreMetodo(nombre);
    }
}