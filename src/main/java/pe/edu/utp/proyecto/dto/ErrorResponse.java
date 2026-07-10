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
public class ErrorResponse {
    private String code;
    private String message;
    private String timestamp;
    private String path;

    private static final ZoneId ZONA_LIMA = ZoneId.of("America/Lima");

    public static ErrorResponse of(String message, String path) {
        return new ErrorResponse("ERROR", message, LocalDateTime.now(Clock.system(ZONA_LIMA)).toString(), path);
    }
}