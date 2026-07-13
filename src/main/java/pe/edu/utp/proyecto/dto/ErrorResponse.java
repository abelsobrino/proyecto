package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Envoltorio estandar para respuestas de error de la API.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private static final ZoneId ZONA_LIMA = ZoneId.of("America/Lima");

    private String code;
    private String message;
    private String timestamp;
    private String path;

    /**
     * Crea una respuesta de error con mensaje y path.
     * @param message Mensaje de error.
     * @param path Ruta donde ocurrio el error.
     * @return ErrorResponse con codigo ERROR.
     */
    public static ErrorResponse of(String message, String path) {
        return new ErrorResponse("ERROR", message, LocalDateTime.now(Clock.system(ZONA_LIMA)).toString(), path);
    }
}