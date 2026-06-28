package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.comprobantes.Factura;
import pe.edu.utp.proyecto.repository.comprobantes_repository.FacturaRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.FacturaService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    @Transactional
    public Factura guardarFactura(Factura factura) {
        log.info("Guardando nueva factura");
        return facturaRepository.save(factura);
    }

    @Override
    public Optional<Factura> obtenerFacturaPorId(Long id) {
        log.debug("Buscando factura con ID: {}", id);
        return facturaRepository.findById(id);
    }

    @Override
    public List<Factura> obtenerTodasLasFacturas() {
        log.info("Obteniendo todas las facturas");
        return facturaRepository.findAll();
    }

    @Override
    @Transactional
    public Factura actualizarFactura(Long id, Factura factura) {
        log.info("Actualizando factura con ID: {}", id);

        Factura existente = facturaRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Factura no encontrada con ID: " + id));

        existente.setRucCliente(factura.getRucCliente());

        return facturaRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarFactura(Long id) {
        log.info("Eliminando factura con ID: {}", id);
        facturaRepository.deleteById(id);
    }
}