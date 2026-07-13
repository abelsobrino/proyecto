package pe.edu.utp.proyecto.modelo.finanzas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;

/**
 * Representa un reporte financiero o comercial.
 */
@Entity
@Table(name = "reporte")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReporte;

    private String tipoReporte;

    private LocalDate fechaGeneracion;

    /**
     * Genera un reporte comercial en un rango de fechas.
     * @param fechaInicio Fecha de inicio del reporte.
     * @param fechaFin Fecha de fin del reporte.
     */
    public void generarReporteComercial(Date fechaInicio, Date fechaFin) {
        log.info("Extrayendo base de datos financiera desde {} hasta {}", fechaInicio, fechaFin);
        log.info("Compilando metricas del reporte de tipo: {}", tipoReporte);
    }

    /**
     * Exporta el reporte a formato PDF.
     * @return Ruta del archivo PDF generado.
     */
    public String exportarPDF() {
        String rutaArchivo = "/reportes/comercial_" + idReporte + ".pdf";
        log.info("Documento exportado exitosamente en: {}", rutaArchivo);
        return rutaArchivo;
    }
}