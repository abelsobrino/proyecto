package pe.edu.utp.proyecto.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pe.edu.utp.proyecto.dto.ErrorResponse;

/**
 * Manejador global de excepciones para toda la aplicacion.
 *
 * <p>Intercepta todas las excepciones lanzadas por los controllers y services,
 * las convierte en respuestas HTTP consistentes usando ErrorResponse.</p>
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo BusinessException (errores de negocio).
     * @param ex Excepcion capturada.
     * @param request Peticion actual.
     * @return ResponseEntity con ErrorResponse y HTTP 400 Bad Request.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, WebRequest request) {
        log.error("Error de negocio: {}", ex.getMessage());
        ErrorResponse error = ErrorResponse.of(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Maneja cualquier otra excepcion no capturada.
     * @param ex Excepcion capturada.
     * @param request Peticion actual.
     * @return ResponseEntity con ErrorResponse y HTTP 500 Internal Server Error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        log.error("Error interno: {}", ex.getMessage(), ex);
        ErrorResponse error = ErrorResponse.of("Ocurrio un error interno en el servidor", request.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}