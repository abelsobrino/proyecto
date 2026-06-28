package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.comprobantes.Boleta;
import pe.edu.utp.proyecto.repository.comprobantes_repository.BoletaRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.BoletaService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class BoletaServiceImpl implements BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    @Override
    @Transactional
    public Boleta guardarBoleta(Boleta boleta) {
        log.info("Guardando nueva boleta");
        return boletaRepository.save(boleta);
    }

    @Override
    public Optional<Boleta> obtenerBoletaPorId(Long id) {
        log.debug("Buscando boleta con ID: {}", id);
        return boletaRepository.findById(id);
    }

    @Override
    public List<Boleta> obtenerTodasLasBoletas() {
        log.info("Obteniendo todas las boletas");
        return boletaRepository.findAll();
    }

    @Override
    @Transactional
    public Boleta actualizarBoleta(Long id, Boleta boleta) {
        log.info("Actualizando boleta con ID: {}", id);

        Boleta existente = boletaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Boleta no encontrada con ID: " + id));

        existente.setDniCliente(boleta.getDniCliente());

        return boletaRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarBoleta(Long id) {
        log.info("Eliminando boleta con ID: {}", id);
        boletaRepository.deleteById(id);
    }
}
