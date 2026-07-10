package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.comprobantes.Factura;
import pe.edu.utp.proyecto.repository.comprobantes_repository.FacturaRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.FacturaService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    @Override
    @Transactional
    public Factura guardarFactura(Factura factura) {
        try {
            log.info("Guardando nueva factura para RUC: {}", factura.getRucCliente());
            factura.emitir();
            return facturaRepository.save(factura);
        } catch (Exception e) {
            log.error("Error al guardar factura: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la factura: " + e.getMessage());
        }
    }

    @Override
    public Optional<Factura> obtenerFacturaPorId(Long id) {
        try {
            log.debug("Buscando factura con ID: {}", id);
            return facturaRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar factura: {}", e.getMessage());
            throw new BusinessException("Error al buscar la factura: " + e.getMessage());
        }
    }

    @Override
    public List<Factura> obtenerTodasLasFacturas() {
        try {
            log.info("Obteniendo todas las facturas");
            return facturaRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener facturas: {}", e.getMessage());
            throw new BusinessException("Error al obtener las facturas: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Factura actualizarFactura(Long id, Factura factura) {
        try {
            log.info("Actualizando factura con ID: {}", id);
            Factura existente = facturaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Factura no encontrada con ID: " + id));
            existente.setRucCliente(factura.getRucCliente());
            existente.setSerie(factura.getSerie());
            existente.setNumero(factura.getNumero());
            existente.setFechaEmision(factura.getFechaEmision());
            existente.setTotal(factura.getTotal());
            return facturaRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar factura: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la factura: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarFactura(Long id) {
        try {
            log.info("Eliminando factura con ID: {}", id);
            if (!facturaRepository.existsById(id)) {
                throw new BusinessException("Factura no encontrada con ID: " + id);
            }
            facturaRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar factura: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la factura: " + e.getMessage());
        }
    }
}