package pe.edu.utp.proyecto.service.ventas.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Cotizacion;
import pe.edu.utp.proyecto.repository.ventas.CotizacionRepository;
import pe.edu.utp.proyecto.service.patron.singleton.ConfiguracionGlobal;
import pe.edu.utp.proyecto.service.ventas.CotizacionService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CotizacionServiceImpl implements CotizacionService {

    private final CotizacionRepository cotizacionRepository;

    @Override
    @Transactional
    public Cotizacion guardarCotizacion(Cotizacion cotizacion) {
        try {
            log.info("Guardando nueva cotizacion");
            if (cotizacion.getFecha() == null) {
                cotizacion.setFecha(new Date());
            }

            ConfiguracionGlobal config = ConfiguracionGlobal.getInstance();
            log.info("Sistema: {}, Version: {}", config.getNombreSistema(), config.getVersion());

            return cotizacionRepository.save(cotizacion);
        } catch (Exception e) {
            log.error("Error al guardar cotizacion: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar la cotizacion: " + e.getMessage());
        }
    }

    @Override
    public Optional<Cotizacion> obtenerCotizacionPorId(Integer id) {
        try {
            log.debug("Buscando cotizacion con ID: {}", id);
            return cotizacionRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar cotizacion: {}", e.getMessage());
            throw new BusinessException("Error al buscar la cotizacion: " + e.getMessage());
        }
    }

    @Override
    public List<Cotizacion> obtenerTodasLasCotizaciones() {
        try {
            log.info("Obteniendo todas las cotizaciones");
            return cotizacionRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener cotizaciones: {}", e.getMessage());
            throw new BusinessException("Error al obtener las cotizaciones: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Cotizacion actualizarCotizacion(Integer id, Cotizacion cotizacion) {
        try {
            log.info("Actualizando cotizacion con ID: {}", id);
            Cotizacion existente = cotizacionRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Cotizacion no encontrada con ID: " + id));
            existente.setFecha(cotizacion.getFecha());
            existente.setTotalEstimado(cotizacion.getTotalEstimado());
            return cotizacionRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar cotizacion: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar la cotizacion: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarCotizacion(Integer id) {
        try {
            log.info("Eliminando cotizacion con ID: {}", id);
            if (!cotizacionRepository.existsById(id)) {
                throw new BusinessException("Cotizacion no encontrada con ID: " + id);
            }
            cotizacionRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar cotizacion: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar la cotizacion: " + e.getMessage());
        }
    }

    @Override
    public List<Cotizacion> obtenerCotizacionesEntreFechas(Date inicio, Date fin) {
        try {
            log.info("Obteniendo cotizaciones entre {} y {}", inicio, fin);
            return cotizacionRepository.findByFechaBetween(inicio, fin);
        } catch (Exception e) {
            log.error("Error al obtener cotizaciones entre fechas: {}", e.getMessage());
            throw new BusinessException("Error al obtener las cotizaciones: " + e.getMessage());
        }
    }

    @Override
    public List<Cotizacion> obtenerCotizacionesPorTotalMayor(Double total) {
        try {
            log.info("Obteniendo cotizaciones con total mayor a: {}", total);
            return cotizacionRepository.findByTotalEstimadoGreaterThan(total);
        } catch (Exception e) {
            log.error("Error al obtener cotizaciones por total: {}", e.getMessage());
            throw new BusinessException("Error al obtener las cotizaciones: " + e.getMessage());
        }
    }

    @Override
    public List<Cotizacion> obtenerUltimas5Cotizaciones() {
        try {
            log.info("Obteniendo ultimas 5 cotizaciones");
            return cotizacionRepository.findTop5ByOrderByFechaDesc();
        } catch (Exception e) {
            log.error("Error al obtener ultimas cotizaciones: {}", e.getMessage());
            throw new BusinessException("Error al obtener las ultimas cotizaciones: " + e.getMessage());
        }
    }
}