package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.comprobantes.NotaCredito;
import pe.edu.utp.proyecto.repository.comprobantes_repository.NotaCreditoRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.NotaCreditoService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotaCreditoServiceImpl implements NotaCreditoService {

    private final NotaCreditoRepository notaCreditoRepository;

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
}