package pe.edu.utp.proyecto.service.comprobantes.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.comprobantes.Boleta;
import pe.edu.utp.proyecto.repository.comprobantes.BoletaRepository;
import pe.edu.utp.proyecto.service.comprobantes.BoletaService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de boletas.
 * Contiene la logica de negocio para la gestion de boletas.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoletaServiceImpl implements BoletaService {

    private final BoletaRepository boletaRepository;

    // =============================================
    // CRUD
    // =============================================

    /**
     * Guarda una nueva boleta.
     * @param boleta Datos de la boleta.
     * @return Boleta guardada.
     */
    @Override
    @Transactional
    public Boleta guardarBoleta(Boleta boleta) {
        try {
            log.info("Guardando nueva boleta para DNI: {}", boleta.getDniCliente());
            boleta.emitir();
            return boletaRepository.save(boleta);
        } catch (Exception e) {
            log.error("Error al guardar boleta: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la boleta: " + e.getMessage());
        }
    }

    /**
     * Busca una boleta por su ID.
     * @param id ID de la boleta.
     * @return Optional con la boleta encontrada.
     */
    @Override
    public Optional<Boleta> obtenerBoletaPorId(Long id) {
        try {
            log.debug("Buscando boleta con ID: {}", id);
            return boletaRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar boleta con ID {}: {}", id, e.getMessage());
            throw new BusinessException("Error al buscar la boleta: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las boletas.
     * @return Lista de boletas.
     */
    @Override
    public List<Boleta> obtenerTodasLasBoletas() {
        try {
            log.info("Obteniendo todas las boletas");
            return boletaRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener las boletas: {}", e.getMessage());
            throw new BusinessException("Error al obtener las boletas: " + e.getMessage());
        }
    }

    /**
     * Actualiza una boleta existente.
     * @param id ID de la boleta.
     * @param boleta Datos actualizados.
     * @return Boleta actualizada.
     */
    @Override
    @Transactional
    public Boleta actualizarBoleta(Long id, Boleta boleta) {
        try {
            log.info("Actualizando boleta con ID: {}", id);
            Boleta existente = boletaRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Boleta no encontrada con ID: " + id));
            existente.setDniCliente(boleta.getDniCliente());
            existente.setSerie(boleta.getSerie());
            existente.setNumero(boleta.getNumero());
            existente.setFechaEmision(boleta.getFechaEmision());
            existente.setTotal(boleta.getTotal());
            return boletaRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar boleta con ID {}: {}", id, e.getMessage());
            throw new BusinessException("No se pudo actualizar la boleta: " + e.getMessage());
        }
    }

    /**
     * Elimina una boleta.
     * @param id ID de la boleta a eliminar.
     */
    @Override
    @Transactional
    public void eliminarBoleta(Long id) {
        try {
            log.info("Eliminando boleta con ID: {}", id);
            if (!boletaRepository.existsById(id)) {
                throw new BusinessException("Boleta no encontrada con ID: " + id);
            }
            boletaRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar boleta con ID {}: {}", id, e.getMessage());
            throw new BusinessException("No se pudo eliminar la boleta: " + e.getMessage());
        }
    }

    // =============================================
    // CONSULTAS ESPECIALIZADAS
    // =============================================

    /**
     * Busca boletas por DNI del cliente.
     * @param dniCliente DNI del cliente.
     * @return Lista de boletas.
     */
    @Override
    public List<Boleta> buscarPorDniCliente(String dniCliente) {
        try {
            log.info("Buscando boletas por DNI: {}", dniCliente);
            return boletaRepository.findByDniCliente(dniCliente);
        } catch (Exception e) {
            log.error("Error al buscar boletas por DNI: {}", e.getMessage());
            throw new BusinessException("Error al buscar boletas: " + e.getMessage());
        }
    }

    /**
     * Busca boletas con total mayor al especificado.
     * @param total Valor minimo del total.
     * @return Lista de boletas.
     */
    @Override
    public List<Boleta> buscarPorTotalMayor(double total) {
        try {
            log.info("Buscando boletas con total mayor a: {}", total);
            return boletaRepository.findByTotalGreaterThan(total);
        } catch (Exception e) {
            log.error("Error al buscar boletas por total: {}", e.getMessage());
            throw new BusinessException("Error al buscar boletas: " + e.getMessage());
        }
    }
}