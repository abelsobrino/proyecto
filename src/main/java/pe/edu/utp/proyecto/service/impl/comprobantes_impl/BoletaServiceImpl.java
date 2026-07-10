package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.comprobantes.Boleta;
import pe.edu.utp.proyecto.repository.comprobantes_repository.BoletaRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.BoletaService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoletaServiceImpl implements BoletaService {

    private final BoletaRepository boletaRepository;

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
}