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

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "reporte")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReporte;

    private String tipoReporte;
    private LocalDate fechaGeneracion;

    public void generarReporteComercial(Date fechaInicio, Date fechaFin) {
        System.out.println("[Reporte] Extrayendo base de datos financiera desde "
                + fechaInicio + " hasta " + fechaFin);
        System.out.println("[Reporte] Compilando métricas del reporte de tipo: "
                + tipoReporte);
    }

    public String exportarPDF() {
        String rutaArchivo = "/reportes/comercial_" + idReporte + ".pdf";
        System.out.println("[Reporte] Documento exportado exitosamente en: "
                + rutaArchivo);
        return rutaArchivo;
    }
}