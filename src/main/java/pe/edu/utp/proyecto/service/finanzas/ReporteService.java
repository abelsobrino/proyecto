package pe.edu.utp.proyecto.service.finanzas;

import pe.edu.utp.proyecto.modelo.finanzas.Reporte;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de reportes.
 * Define las operaciones disponibles para la entidad Reporte.
 */
public interface ReporteService {

    /**
     * Guarda un nuevo reporte en el sistema.
     * @param reporte Datos del reporte a guardar.
     * @return Reporte guardado con su ID generado.
     */
    Reporte guardarReporte(Reporte reporte);

    /**
     * Busca un reporte por su ID.
     * @param id ID del reporte.
     * @return Optional con el reporte encontrado o vacio.
     */
    Optional<Reporte> obtenerReportePorId(Integer id);

    /**
     * Obtiene todos los reportes registrados.
     * @return Lista de todos los reportes.
     */
    List<Reporte> obtenerTodosLosReportes();

    /**
     * Actualiza los datos de un reporte existente.
     * @param id ID del reporte a actualizar.
     * @param reporte Datos actualizados del reporte.
     * @return Reporte actualizado.
     */
    Reporte actualizarReporte(Integer id, Reporte reporte);

    /**
     * Elimina un reporte del sistema.
     * @param id ID del reporte a eliminar.
     */
    void eliminarReporte(Integer id);

    /**
     * Busca reportes por tipo de reporte.
     * @param tipoReporte Tipo de reporte.
     * @return Lista de reportes del tipo especificado.
     */
    List<Reporte> obtenerReportesPorTipo(String tipoReporte);

    /**
     * Busca reportes por fecha de generacion.
     * @param fechaGeneracion Fecha en que se genero el reporte.
     * @return Lista de reportes de esa fecha.
     */
    List<Reporte> obtenerReportesPorFecha(LocalDate fechaGeneracion);
}