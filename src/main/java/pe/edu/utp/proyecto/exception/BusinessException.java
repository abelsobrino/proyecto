package pe.edu.utp.proyecto.exception;

/**
 * Excepcion personalizada para errores de logica de negocio.
 *
 * <p>Representa errores que ocurren cuando se violan reglas de negocio,
 * como validaciones de unicidad, stock insuficiente, estados invalidos, etc.</p>
 *
 * <p>Extiende RuntimeException para no requerir try-catch obligatorio.</p>
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
public class BusinessException extends RuntimeException {

    /**
     * Constructor con mensaje de error.
     * @param message Mensaje descriptivo del error.
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y causa.
     * @param message Mensaje descriptivo del error.
     * @param cause Causa original de la excepcion.
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}