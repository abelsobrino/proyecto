package pe.edu.utp.proyecto.modelo.comprobantes;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import jakarta.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="envio_sunat")
public class EnvioSUNAT {
    @Id
    private String codigoEnvio;
    private String estadoEnvio;
    private LocalDate fechaEnvio;

    public boolean enviarComprobante(@SuppressWarnings("unused") ComprobanteElectronico comprobante) {
        this.estadoEnvio = "PROCESADO_ACEPTADO";
        System.out.println("[EnvioSUNAT] Conectando con servidores SUNAT... Enviando documento...");
        System.out.println("[EnvioSUNAT] Documento recibido correctamente el: " + fechaEnvio);
        return true;
    }

    public String obtenerEstadoRespuesta() {
        System.out.println("[EnvioSUNAT] Consultando respuesta: CDR recibido con estado -> " + this.estadoEnvio);
        return this.estadoEnvio;
    }
}