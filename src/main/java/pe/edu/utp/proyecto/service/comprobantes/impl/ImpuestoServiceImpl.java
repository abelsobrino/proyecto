package pe.edu.utp.proyecto.service.comprobantes.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.comprobantes.Impuesto;
import pe.edu.utp.proyecto.repository.comprobantes.ImpuestoRepository;
import pe.edu.utp.proyecto.service.comprobantes.ImpuestoService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImpuestoServiceImpl implements ImpuestoService {

    private final ImpuestoRepository impuestoRepository;

    @Override
    @Transactional
    public Impuesto guardarImpuesto(Impuesto impuesto) {
        try {
            log.info("Guardando impuesto: {}", impuesto.getTipoImpuesto());
            return impuestoRepository.save(impuesto);
        } catch (Exception e) {
            log.error("Error al guardar impuesto: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el impuesto: " + e.getMessage());
        }
    }

    @Override
    public Optional<Impuesto> obtenerImpuestoPorId(String tipoImpuesto) {
        try {
            log.debug("Buscando impuesto: {}", tipoImpuesto);
            return impuestoRepository.findById(tipoImpuesto);
        } catch (Exception e) {
            log.error("Error al buscar impuesto: {}", e.getMessage());
            throw new BusinessException("Error al buscar el impuesto: " + e.getMessage());
        }
    }

    @Override
    public List<Impuesto> obtenerTodosLosImpuestos() {
        try {
            log.info("Obteniendo todos los impuestos");
            return impuestoRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener impuestos: {}", e.getMessage());
            throw new BusinessException("Error al obtener los impuestos: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Impuesto actualizarImpuesto(String tipoImpuesto, Impuesto impuesto) {
        try {
            log.info("Actualizando impuesto: {}", tipoImpuesto);
            Impuesto existente = impuestoRepository.findById(tipoImpuesto)
                    .orElseThrow(() -> new BusinessException("Impuesto no encontrado: " + tipoImpuesto));
            existente.setPorcentaje(impuesto.getPorcentaje());
            existente.setMonto(impuesto.getMonto());
            return impuestoRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar impuesto: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el impuesto: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarImpuesto(String tipoImpuesto) {
        try {
            log.info("Eliminando impuesto: {}", tipoImpuesto);
            if (!impuestoRepository.existsById(tipoImpuesto)) {
                throw new BusinessException("Impuesto no encontrado: " + tipoImpuesto);
            }
            impuestoRepository.deleteById(tipoImpuesto);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar impuesto: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el impuesto: " + e.getMessage());
        }
    }
}