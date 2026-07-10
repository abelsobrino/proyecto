package pe.edu.utp.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private String timestamp;
    private String path;

    public static ErrorResponse of(String message, String path) {
        return new ErrorResponse("ERROR", message, LocalDateTime.now().toString(), path);
    }
}