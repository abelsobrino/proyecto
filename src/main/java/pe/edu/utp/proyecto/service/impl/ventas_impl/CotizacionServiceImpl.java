package pe.edu.utp.proyecto.service.impl.ventas_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.ventas.Cotizacion;
import pe.edu.utp.proyecto.repository.ventas_repository.CotizacionRepository;
import pe.edu.utp.proyecto.service.patron.singleton.ConfiguracionGlobal;
import pe.edu.utp.proyecto.service.ventas_service.CotizacionService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CotizacionServiceImpl implements CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Override
    public Optional<Cotizacion> obtenerCotizacionPorId(Integer id) {
        log.debug("Buscando cotización con ID: {}", id);
        return cotizacionRepository.findById(id);
    }

    @Override
    public List<Cotizacion> obtenerTodasLasCotizaciones() {
        log.info("Obteniendo todas las cotizaciones");
        return cotizacionRepository.findAll();
    }

    @Override
    @Transactional
    public Cotizacion actualizarCotizacion(Integer id, Cotizacion cotizacion) {
        log.info("Actualizando cotización con ID: {}", id);

        Cotizacion existente = cotizacionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cotización no encontrada con ID: " + id));

        existente.setFecha(cotizacion.getFecha());
        existente.setTotalEstimado(cotizacion.getTotalEstimado());

        return cotizacionRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarCotizacion(Integer id) {
        log.info("Eliminando cotización con ID: {}", id);

        Cotizacion cotizacion = cotizacionRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cotización no encontrada con ID: " + id));

        cotizacionRepository.delete(cotizacion);
    }

    @Override
    public List<Cotizacion> obtenerCotizacionesEntreFechas(Date inicio, Date fin) {
        log.info("Obteniendo cotizaciones entre {} y {}", inicio, fin);
        return cotizacionRepository.findByFechaBetween(inicio, fin);
    }

    @Override
    public List<Cotizacion> obtenerCotizacionesPorTotalMayor(Double total) {
        log.info("Obteniendo cotizaciones con total mayor a: {}", total);
        return cotizacionRepository.findByTotalEstimadoGreaterThan(total);
    }

    @Override
    public List<Cotizacion> obtenerUltimas5Cotizaciones() {
        log.info("Obteniendo últimas 5 cotizaciones");
        return cotizacionRepository.findTop5ByOrderByFechaDesc();
    }
    @Override
    @Transactional
    public Cotizacion guardarCotizacion(Cotizacion cotizacion) {
        log.info("Guardando nueva cotización");

        if (cotizacion.getFecha() == null) {
            cotizacion.setFecha(new Date());
        }

        ConfiguracionGlobal config = ConfiguracionGlobal.getInstance();
        log.info("Sistema: {}, Versión: {}", config.getNombreSistema(), config.getVersion());

        return cotizacionRepository.save(cotizacion);
    }
}