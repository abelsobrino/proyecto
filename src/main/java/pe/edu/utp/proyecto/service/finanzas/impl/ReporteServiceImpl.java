package pe.edu.utp.proyecto.service.finanzas.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.finanzas.Reporte;
import pe.edu.utp.proyecto.repository.finanzas.ReporteRepository;
import pe.edu.utp.proyecto.service.finanzas.ReporteService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository reporteRepository;

    @Override
    @Transactional
    public Reporte guardarReporte(Reporte reporte) {
        try {
            log.info("Guardando nuevo reporte: {}", reporte.getTipoReporte());
            return reporteRepository.save(reporte);
        } catch (Exception e) {
            log.error("Error al guardar reporte: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el reporte: " + e.getMessage());
        }
    }

    @Override
    public Optional<Reporte> obtenerReportePorId(Integer id) {
        try {
            log.debug("Buscando reporte con ID: {}", id);
            return reporteRepository.findById(id);
        } catch (Exception e) {
            log.error("Error al buscar reporte: {}", e.getMessage());
            throw new BusinessException("Error al buscar el reporte: " + e.getMessage());
        }
    }

    @Override
    public List<Reporte> obtenerTodosLosReportes() {
        try {
            log.info("Obteniendo todos los reportes");
            return reporteRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener reportes: {}", e.getMessage());
            throw new BusinessException("Error al obtener los reportes: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public Reporte actualizarReporte(Integer id, Reporte reporte) {
        try {
            log.info("Actualizando reporte con ID: {}", id);
            Reporte existente = reporteRepository.findById(id)
                    .orElseThrow(() -> new BusinessException("Reporte no encontrado con ID: " + id));
            existente.setTipoReporte(reporte.getTipoReporte());
            existente.setFechaGeneracion(reporte.getFechaGeneracion());
            return reporteRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar reporte: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el reporte: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarReporte(Integer id) {
        try {
            log.info("Eliminando reporte con ID: {}", id);
            if (!reporteRepository.existsById(id)) {
                throw new BusinessException("Reporte no encontrado con ID: " + id);
            }
            reporteRepository.deleteById(id);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar reporte: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el reporte: " + e.getMessage());
        }
    }

    @Override
    public List<Reporte> obtenerReportesPorTipo(String tipoReporte) {
        try {
            log.info("Buscando reportes por tipo: {}", tipoReporte);
            return reporteRepository.findByTipoReporte(tipoReporte);
        } catch (Exception e) {
            log.error("Error al buscar reportes por tipo: {}", e.getMessage());
            throw new BusinessException("Error al buscar los reportes: " + e.getMessage());
        }
    }

    @Override
    public List<Reporte> obtenerReportesPorFecha(LocalDate fechaGeneracion) {
        try {
            log.info("Buscando reportes por fecha: {}", fechaGeneracion);
            return reporteRepository.findByFechaGeneracion(fechaGeneracion);
        } catch (Exception e) {
            log.error("Error al buscar reportes por fecha: {}", e.getMessage());
            throw new BusinessException("Error al buscar los reportes: " + e.getMessage());
        }
    }
}
