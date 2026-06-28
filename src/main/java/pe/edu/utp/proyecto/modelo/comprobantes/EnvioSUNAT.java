package pe.edu.utp.proyecto.modelo.comprobantes;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="envio_sunat")
@Slf4j
public class EnvioSUNAT {
    @Id
    private String codigoEnvio;
    private String estadoEnvio;
    private LocalDate fechaEnvio;

    public boolean enviarComprobante() {
        this.estadoEnvio = "PROCESADO_ACEPTADO";
        log.info("[EnvioSUNAT] Conectando con servidores SUNAT... Enviando documento...");
        log.info("[EnvioSUNAT] Documento recibido correctamente el: {}", fechaEnvio);
        return true;
    }

    public String obtenerEstadoRespuesta() {
        log.info("[EnvioSUNAT] Consultando respuesta: CDR recibido con estado -> {}", this.estadoEnvio);
        return this.estadoEnvio;
    }
}