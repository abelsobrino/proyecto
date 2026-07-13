package pe.edu.utp.proyecto.service.comprobantes.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.comprobantes.NotaCredito;
import pe.edu.utp.proyecto.repository.comprobantes.NotaCreditoRepository;
import pe.edu.utp.proyecto.service.comprobantes.NotaCreditoService;

import java.util.List;
import java.util.Optional;

/**
 * Implementacion del servicio de notas de credito.
 * Contiene la logica de negocio para la gestion de notas de credito.
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotaCreditoServiceImpl implements NotaCreditoService {

    private final NotaCreditoRepository notaCreditoRepository;

    /**
     * Guarda una nueva nota de credito.
     * @param notaCredito Datos de la nota de credito.
     * @return Nota de credito guardada.
     */
    @Override
    @Transactional
    public NotaCredito guardarNotaCredito(NotaCredito notaCredito) {
        try {
            log.info("Guardando nota de credito por motivo: {}", notaCredito.getMotivo());
            notaCredito.emitir();
            return notaCreditoRepository.save(notaCredito);
        } catch (Exception e) {
            log.error("Error al guardar nota de credito: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la nota de credito: " + e.getMessage());
        }
    }

    /**
     * Busca una nota de credito por su ID.
     * @param id ID de la nota de credito.
     * @return Optional con la nota de credito encontrada.
     */
    @Override
    public Optional<NotaCredito> obtenerNotaCreditoPorId(Long id) {
        try {
            log.debug("Buscando nota de credito con ID: {}", id);
            return notaCreditoRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar nota de credito: {}", e.getMessage());
            throw new BusinessException("Error al buscar la nota de credito: " + e.getMessage());
        }
    }

    /**
     * Obtiene todas las notas de credito.
     * @return Lista de notas de credito.
     */
    @Override
    public List<NotaCredito> obtenerTodasLasNotasCredito() {
        try {
            log.info("Obteniendo todas las notas de credito");
            return notaCreditoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener notas de credito: {}", e.getMessage());
            throw new BusinessException("Error al obtener las notas de credito: " + e.getMessage());
        }
    }

    /**
     * Actualiza una nota de credito existente.
     * @param id ID de la nota de credito.
     * @param notaCredito Datos actualizados.
     * @return Nota de credito actualizada.
     */
    @Override
    @Transactional
    public NotaCredito actualizarNotaCredito(Long id, NotaCredito notaCredito) {
        try {
            log.info("Actualizando nota de credito con ID: {}", id);
            NotaCredito existente = notaCreditoRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Nota de credito no encontrada con ID: " + id));
            existente.setMotivo(notaCredito.getMotivo());
            existente.setSerie(notaCredito.getSerie());
            existente.setNumero(notaCredito.getNumero());
            existente.setFechaEmision(notaCredito.getFechaEmision());
            existente.setTotal(notaCredito.getTotal());
            return notaCreditoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar nota de credito: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la nota de credito: " + e.getMessage());
        }
    }

    /**
     * Elimina una nota de credito.
     * @param id ID de la nota de credito a eliminar.
     */
    @Override
    @Transactional
    public void eliminarNotaCredito(Long id) {
        try {
            log.info("Eliminando nota de credito con ID: {}", id);
            if (!notaCreditoRepository.existsById(id)) {
                throw new BusinessException("Nota de credito no encontrada con ID: " + id);
            }
            notaCreditoRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar nota de credito: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la nota de credito: " + e.getMessage());
        }
    }

    /**
     * Busca notas de credito por motivo.
     * @param motivo Texto a buscar en el motivo.
     * @return Lista de notas de credito.
     */
    @Override
    public List<NotaCredito> buscarPorMotivo(String motivo) {
        try {
            log.info("Buscando notas de credito por motivo: {}", motivo);
            return notaCreditoRepository.findByMotivoContaining(motivo);
        } catch (Exception e) {
            log.error("Error al buscar notas de credito por motivo: {}", e.getMessage());
            throw new BusinessException("Error al buscar notas de credito: " + e.getMessage());
        }
    }

    /**
     * Busca notas de credito con total mayor al especificado.
     * @param total Valor minimo del total.
     * @return Lista de notas de credito.
     */
    @Override
    public List<NotaCredito> buscarPorTotalMayor(double total) {
        try {
            log.info("Buscando notas de credito con total mayor a: {}", total);
            return notaCreditoRepository.findByTotalGreaterThan(total);
        } catch (Exception e) {
            log.error("Error al buscar notas de credito por total: {}", e.getMessage());
            throw new BusinessException("Error al buscar notas de credito: " + e.getMessage());
        }
    }
}