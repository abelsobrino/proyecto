package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String timestamp;

    private static final ZoneId ZONA_LIMA = ZoneId.of("America/Lima");

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, LocalDateTime.now(Clock.system(ZONA_LIMA)).toString());
    }

    public static <T> ApiResponse<T> success(T data) {
        return success(data, "Operacion exitosa");
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, message, null, LocalDateTime.now(Clock.system(ZONA_LIMA)).toString());
    }
}