package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.comprobantes.NotaCredito;
import pe.edu.utp.proyecto.repository.comprobantes_repository.NotaCreditoRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.NotaCreditoService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class NotaCreditoServiceImpl implements NotaCreditoService {

    @Autowired
    private NotaCreditoRepository notaCreditoRepository;

    @Override
    @Transactional
    public NotaCredito guardarNotaCredito(NotaCredito notaCredito) {
        log.info("Guardando nota de crédito");
        return notaCreditoRepository.save(notaCredito);
    }

    @Override
    public Optional<NotaCredito> obtenerNotaCreditoPorId(Long id) {
        log.debug("Buscando nota de crédito con ID: {}", id);
        return notaCreditoRepository.findById(id);
    }

    @Override
    public List<NotaCredito> obtenerTodasLasNotasCredito() {
        log.info("Obteniendo todas las notas de crédito");
        return notaCreditoRepository.findAll();
    }

    @Override
    @Transactional
    public NotaCredito actualizarNotaCredito(Long id, NotaCredito notaCredito) {
        log.info("Actualizando nota de crédito con ID: {}", id);

        NotaCredito existente = notaCreditoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Nota de crédito no encontrada con ID: " + id));

        existente.setMotivo(notaCredito.getMotivo());

        return notaCreditoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarNotaCredito(Long id) {
        log.info("Eliminando nota de crédito con ID: {}", id);
        notaCreditoRepository.deleteById(id);
    }
}