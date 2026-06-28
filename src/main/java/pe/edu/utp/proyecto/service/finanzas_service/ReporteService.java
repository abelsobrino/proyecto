package pe.edu.utp.proyecto.service.finanzas_service;

import pe.edu.utp.proyecto.modelo.finanzas.Reporte;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReporteService {

    // CRUD Básico
    Reporte guardarReporte(Reporte reporte);
    Optional<Reporte> obtenerReportePorId(Integer id);
    List<Reporte> obtenerTodosLosReportes();
    Reporte actualizarReporte(Integer id, Reporte reporte);
    void eliminarReporte(Integer id);

    // Consultas específicas
    List<Reporte> obtenerReportesPorTipo(String tipoReporte);
    List<Reporte> obtenerReportesPorFecha(LocalDate fechaGeneracion);
}