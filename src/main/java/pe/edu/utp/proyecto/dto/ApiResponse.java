package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Envoltorio estandar para todas las respuestas de la API.
 * Garantiza consistencia en el formato de respuesta.
 *
 * @param <T> Tipo de dato contenido en la respuesta.
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private static final ZoneId ZONA_LIMA = ZoneId.of("America/Lima");

    private boolean success;
    private String message;
    private T data;
    private String timestamp;

    /**
     * Crea una respuesta exitosa con datos y mensaje.
     * @param data Datos de la respuesta.
     * @param message Mensaje informativo.
     * @return ApiResponse con exito true.
     */
    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, LocalDateTime.now(Clock.system(ZONA_LIMA)).toString());
    }

    /**
     * Crea una respuesta exitosa con datos y mensaje por defecto.
     * @param data Datos de la respuesta.
     * @return ApiResponse con exito true.
     */
    public static <T> ApiResponse<T> success(T data) {
        return success(data, "Operacion exitosa");
    }

    /**
     * Crea una respuesta de error.
     * @param message Mensaje de error.
     * @return ApiResponse con exito false.
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, LocalDateTime.now(Clock.system(ZONA_LIMA)).toString());
    }
}