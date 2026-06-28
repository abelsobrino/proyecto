package pe.edu.utp.proyecto.service.impl.finanzas_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.finanzas.Reporte;
import pe.edu.utp.proyecto.repository.finanzas_repository.ReporteRepository;
import pe.edu.utp.proyecto.service.finanzas_service.ReporteService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    @Transactional
    public Reporte guardarReporte(Reporte reporte) {
        log.info("Guardando nuevo reporte");
        return reporteRepository.save(reporte);
    }

    @Override
    public Optional<Reporte> obtenerReportePorId(Integer id) {
        log.debug("Buscando reporte con ID: {}", id);
        return reporteRepository.findById(id);
    }

    @Override
    public List<Reporte> obtenerTodosLosReportes() {
        log.info("Obteniendo todos los reportes");
        return reporteRepository.findAll();
    }

    @Override
    @Transactional
    public Reporte actualizarReporte(Integer id, Reporte reporte) {
        log.info("Actualizando reporte con ID: {}", id);

        Reporte existente = reporteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Reporte no encontrado con ID: " + id));

        existente.setTipoReporte(reporte.getTipoReporte());
        existente.setFechaGeneracion(reporte.getFechaGeneracion());

        return reporteRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarReporte(Integer id) {
        log.info("Eliminando reporte con ID: {}", id);
        reporteRepository.deleteById(id);
    }

    @Override
    public List<Reporte> obtenerReportesPorTipo(String tipoReporte) {
        log.info("Buscando reportes por tipo: {}", tipoReporte);
        return reporteRepository.findByTipoReporte(tipoReporte);
    }

    @Override
    public List<Reporte> obtenerReportesPorFecha(LocalDate fechaGeneracion) {
        log.info("Buscando reportes por fecha de generación: {}", fechaGeneracion);
        return reporteRepository.findByFechaGeneracion(fechaGeneracion);
    }
}
