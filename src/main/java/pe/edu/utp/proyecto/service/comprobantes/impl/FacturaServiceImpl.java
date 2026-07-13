package pe.edu.utp.proyecto.service.comprobantes.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.comprobantes.Factura;
import pe.edu.utp.proyecto.repository.comprobantes.FacturaRepository;
import pe.edu.utp.proyecto.service.comprobantes.FacturaService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de facturas.
 * Contiene la logica de negocio para la gestion de facturas.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;

    /**
     * Guarda una nueva factura.
     * @param factura Datos de la factura.
     * @return Factura guardada.
     */
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

    /**
     * Busca una factura por su ID.
     * @param id ID de la factura.
     * @return Optional con la factura encontrada.
     */
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

    /**
     * Obtiene todas las facturas.
     * @return Lista de facturas.
     */
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

    /**
     * Actualiza una factura existente.
     * @param id ID de la factura.
     * @param factura Datos actualizados.
     * @return Factura actualizada.
     */
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

    /**
     * Elimina una factura.
     * @param id ID de la factura a eliminar.
     */
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

    /**
     * Busca una factura por el RUC del cliente.
     * @param rucCliente RUC del cliente.
     * @return Factura encontrada o null.
     */
    @Override
    public Factura buscarPorRucCliente(String rucCliente) {
        try {
            log.info("Buscando factura por RUC: {}", rucCliente);
            return facturaRepository.findByRucCliente(rucCliente);
        } catch (Exception e) {
            log.error("Error al buscar factura por RUC: {}", e.getMessage());
            throw new BusinessException("Error al buscar la factura: " + e.getMessage());
        }
    }

    /**
     * Busca facturas cuyo RUC contenga un texto.
     * @param rucCliente Texto a buscar en el RUC.
     * @return Lista de facturas que coinciden.
     */
    @Override
    public List<Factura> buscarPorRucClienteContaining(String rucCliente) {
        try {
            log.info("Buscando facturas por RUC que contenga: {}", rucCliente);
            return facturaRepository.findByRucClienteContaining(rucCliente);
        } catch (Exception e) {
            log.error("Error al buscar facturas por RUC: {}", e.getMessage());
            throw new BusinessException("Error al buscar facturas: " + e.getMessage());
        }
    }

    /**
     * Busca facturas con total mayor al especificado.
     * @param total Valor minimo del total.
     * @return Lista de facturas.
     */
    @Override
    public List<Factura> buscarPorTotalMayor(double total) {
        try {
            log.info("Buscando facturas con total mayor a: {}", total);
            return facturaRepository.findByTotalGreaterThan(total);
        } catch (Exception e) {
            log.error("Error al buscar facturas por total: {}", e.getMessage());
            throw new BusinessException("Error al buscar facturas: " + e.getMessage());
        }
    }
}