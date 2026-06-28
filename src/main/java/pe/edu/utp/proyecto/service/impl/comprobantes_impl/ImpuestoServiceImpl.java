package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.comprobantes.Impuesto;
import pe.edu.utp.proyecto.repository.comprobantes_repository.ImpuestoRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.ImpuestoService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ImpuestoServiceImpl implements ImpuestoService {

    @Autowired
    private ImpuestoRepository impuestoRepository;

    @Override
    @Transactional
    public Impuesto guardarImpuesto(Impuesto impuesto) {
        log.info("Guardando impuesto");
        return impuestoRepository.save(impuesto);
    }

    @Override
    public Optional<Impuesto> obtenerImpuestoPorId(String tipoImpuesto) {
        log.debug("Buscando impuesto: {}", tipoImpuesto);
        return impuestoRepository.findById(tipoImpuesto);
    }

    @Override
    public List<Impuesto> obtenerTodosLosImpuestos() {
        log.info("Obteniendo todos los impuestos");
        return impuestoRepository.findAll();
    }

    @Override
    @Transactional
    public Impuesto actualizarImpuesto(String tipoImpuesto, Impuesto impuesto) {
        log.info("Actualizando impuesto: {}", tipoImpuesto);

        Impuesto existente = impuestoRepository.findById(tipoImpuesto)
                .orElseThrow(() -> new BusinessException("Impuesto no encontrado: " + tipoImpuesto));

        existente.setPorcentaje(impuesto.getPorcentaje());
        existente.setMonto(impuesto.getMonto());

        return impuestoRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarImpuesto(String tipoImpuesto) {
        log.info("Eliminando impuesto: {}", tipoImpuesto);
        impuestoRepository.deleteById(tipoImpuesto);
    }
}