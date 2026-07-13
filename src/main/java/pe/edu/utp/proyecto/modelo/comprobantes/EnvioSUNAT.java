package pe.edu.utp.proyecto.modelo.comprobantes;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

/**
 * Representa el envio de un comprobante a SUNAT.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "envio_sunat")
@Slf4j
public class EnvioSUNAT {

    @Id
    private String codigoEnvio;

    private String estadoEnvio;

    private LocalDate fechaEnvio;

    /**
     * Simula el envio del comprobante a SUNAT.
     * @return true si el envio fue exitoso.
     */
    public boolean enviarComprobante() {
        this.estadoEnvio = "PROCESADO_ACEPTADO";
        log.info("Conectando con servidores SUNAT... Enviando documento...");
        log.info("Documento recibido correctamente el: {}", fechaEnvio);
        return true;
    }

    /**
     * Obtiene el estado de la respuesta de SUNAT.
     * @return Estado actual del envio.
     */
    public String obtenerEstadoRespuesta() {
        log.info("Consultando respuesta: CDR recibido con estado -> {}", this.estadoEnvio);
        return this.estadoEnvio;
    }
}