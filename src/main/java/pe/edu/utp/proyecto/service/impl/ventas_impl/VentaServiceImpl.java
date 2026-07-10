package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Venta;
import pe.edu.utp.proyecto.repository.ventas_repository.VentaRepository;
import pe.edu.utp.proyecto.service.patron.comportamiento_state.EstadoFactory;
import pe.edu.utp.proyecto.service.ventas_service.VentaService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VentaServiceImpl implements VentaService {

    private static final String MENSAJE_VENTA_NO_ENCONTRADA = "Venta no encontrada con ID: ";

    private final VentaRepository ventaRepository;

    @Override
    @Transactional
    public Venta guardarVenta(Venta venta) {
        try {
            log.info("Guardando nueva venta");
            if (venta.getEstado() == null || venta.getEstado().isEmpty()) {
                venta.setEstado("PENDIENTE");
            }
            venta.setEstadoActual(EstadoFactory.getEstado(venta.getEstado()));
            return ventaRepository.save(venta);
        } catch (Exception e) {
            log.error("Error al guardar venta: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la venta: " + e.getMessage());
        }
    }

    @Override
    public Optional<Venta> obtenerVentaPorId(Integer id) {
        try {
            log.debug("Buscando venta con ID: {}", id);
            Optional<Venta> ventaOpt = ventaRepository.findById(id);
            ventaOpt.ifPresent(v -> v.setEstadoActual(EstadoFactory.getEstado(v.getEstado())));
            return ventaOpt;
        } catch (Exception e) {
            log.error("Error al buscar venta: {}", e.getMessage());
            throw new BusinessException("Error al buscar la venta: " + e.getMessage());
        }
    }

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        try {
            log.info("Obteniendo todas las ventas");
            List<Venta> ventas = ventaRepository.findAll();
            ventas.forEach(v -> v.setEstadoActual(EstadoFactory.getEstado(v.getEstado())));
            return ventas;
        } catch (Exception e) {
            log.error("Error al obtener ventas: {}", e.getMessage());
            throw new BusinessException("Error al obtener las ventas: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Venta actualizarVenta(Integer id, Venta venta) {
        try {
            log.info("Actualizando venta con ID: {}", id);
            Venta existente = ventaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException(MENSAJE_VENTA_NO_ENCONTRADA + id));

            existente.setFechaVenta(venta.getFechaVenta());
            existente.setTotal(venta.getTotal());
            existente.setCliente(venta.getCliente());
            existente.setDetalles(venta.getDetalles());

            if (venta.getEstado() != null && !venta.getEstado().isEmpty()) {
                existente.setEstado(venta.getEstado());
                existente.setEstadoActual(EstadoFactory.getEstado(venta.getEstado()));
            }

            return ventaRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar venta: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la venta: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarVenta(Integer id) {
        try {
            log.info("Eliminando venta con ID: {}", id);
            if (!ventaRepository.existsById(id)) {
                throw new BusinessException(MENSAJE_VENTA_NO_ENCONTRADA + id);
            }
            ventaRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar venta: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la venta: " + e.getMessage());
        }
    }

    @Override
    public List<Venta> obtenerVentasPorEstado(String estado) {
        try {
            log.info("Obteniendo ventas por estado: {}", estado);
            List<Venta> ventas = ventaRepository.findAll()
                    .stream()
                    .filter(v -> v.getEstado() != null && v.getEstado().equalsIgnoreCase(estado))
                    .toList();
            ventas.forEach(v -> v.setEstadoActual(EstadoFactory.getEstado(v.getEstado())));
            return ventas;
        } catch (Exception e) {
            log.error("Error al obtener ventas por estado: {}", e.getMessage());
            throw new BusinessException("Error al obtener las ventas: " + e.getMessage());
        }
    }

    @Override
    public Double calcularTotalVenta(Integer id) {
        try {
            log.debug("Calculando total de venta con ID: {}", id);
            return ventaRepository.findById(id)
                    .map(Venta::getTotal)
                    .map(BigDecimal::doubleValue)
                    .orElse(0.0);
        } catch (Exception e) {
            log.error("Error al calcular total de venta: {}", e.getMessage());
            throw new BusinessException("Error al calcular el total de la venta: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Venta procesarVenta(Integer id) {
        try {
            log.info("Procesando venta con ID: {}", id);
            Venta venta = ventaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException(MENSAJE_VENTA_NO_ENCONTRADA + id));

            venta.setEstadoActual(EstadoFactory.getEstado(venta.getEstado()));
            venta.procesar();

            log.info("Venta {} procesada. Nuevo estado: {}", id, venta.getEstado());
            return ventaRepository.save(venta);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al procesar venta: {}", e.getMessage());
            throw new BusinessException("No se pudo procesar la venta: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Venta completarVenta(Integer id) {
        try {
            log.info("Completando venta con ID: {}", id);
            Venta venta = ventaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException(MENSAJE_VENTA_NO_ENCONTRADA + id));

            venta.setEstadoActual(EstadoFactory.getEstado(venta.getEstado()));
            venta.completar();

            log.info("Venta {} completada. Nuevo estado: {}", id, venta.getEstado());
            return ventaRepository.save(venta);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al completar venta: {}", e.getMessage());
            throw new BusinessException("No se pudo completar la venta: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Venta cancelarVenta(Integer id) {
        try {
            log.info("Cancelando venta con ID: {}", id);
            Venta venta = ventaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException(MENSAJE_VENTA_NO_ENCONTRADA + id));

            venta.setEstadoActual(EstadoFactory.getEstado(venta.getEstado()));
            venta.cancelar();

            log.info("Venta {} cancelada. Nuevo estado: {}", id, venta.getEstado());
            return ventaRepository.save(venta);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al cancelar venta: {}", e.getMessage());
            throw new BusinessException("No se pudo cancelar la venta: " + e.getMessage());
        }
    }
}