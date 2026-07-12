package pe.edu.utp.proyecto.service.ventas.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.MetodoPago;
import pe.edu.utp.proyecto.repository.ventas.MetodoPagoRepository;
import pe.edu.utp.proyecto.service.ventas.MetodoPagoService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MetodoPagoServiceImpl implements MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    @Override
    @Transactional
    public MetodoPago guardarMetodoPago(MetodoPago metodoPago) {
        try {
            log.info("Guardando metodo de pago: {}", metodoPago.getNombreMetodo());
            if (metodoPagoRepository.findByNombreMetodo(metodoPago.getNombreMetodo()).isPresent()) {
                throw new BusinessException("Ya existe un metodo de pago con el nombre: " + metodoPago.getNombreMetodo());
            }
            return metodoPagoRepository.save(metodoPago);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al guardar metodo de pago: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el metodo de pago: " + e.getMessage());
        }
    }

    @Override
    public Optional<MetodoPago> obtenerMetodoPagoPorId(Integer id) {
        try {
            log.debug("Buscando metodo de pago con ID: {}", id);
            return metodoPagoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar metodo de pago: {}", e.getMessage());
            throw new BusinessException("Error al buscar el metodo de pago: " + e.getMessage());
        }
    }

    @Override
    public List<MetodoPago> obtenerTodosLosMetodosPago() {
        try {
            log.info("Obteniendo todos los metodos de pago");
            return metodoPagoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener metodos de pago: {}", e.getMessage());
            throw new BusinessException("Error al obtener los metodos de pago: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public MetodoPago actualizarMetodoPago(Integer id, MetodoPago metodoPago) {
        try {
            log.info("Actualizando metodo de pago con ID: {}", id);
            MetodoPago existente = metodoPagoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Metodo de pago no encontrado con ID: " + id));

            if (!existente.getNombreMetodo().equals(metodoPago.getNombreMetodo())) {
                Optional<MetodoPago> conNombre = metodoPagoRepository.findByNombreMetodo(metodoPago.getNombreMetodo());
                if (conNombre.isPresent() && conNombre.get().getIdMetodo() != id) {
                    throw new BusinessException("Ya existe otro metodo con el nombre: " + metodoPago.getNombreMetodo());
                }
            }

            existente.setNombreMetodo(metodoPago.getNombreMetodo());
            existente.setDescripcion(metodoPago.getDescripcion());
            return metodoPagoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar metodo de pago: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el metodo de pago: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarMetodoPago(Integer id) {
        try {
            log.info("Eliminando metodo de pago con ID: {}", id);
            if (!metodoPagoRepository.existsById(id)) {
                throw new BusinessException("Metodo de pago no encontrado con ID: " + id);
            }
            metodoPagoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar metodo de pago: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el metodo de pago: " + e.getMessage());
        }
    }

    @Override
    public Optional<MetodoPago> obtenerMetodoPorNombre(String nombre) {
        try {
            log.debug("Buscando metodo de pago por nombre: {}", nombre);
            return metodoPagoRepository.findByNombreMetodo(nombre);
        } catch (Exception e) {
            log.error("Error al buscar metodo de pago por nombre: {}", e.getMessage());
            throw new BusinessException("Error al buscar el metodo de pago: " + e.getMessage());
        }
    }
}