package pe.edu.utp.proyecto.service.patron.comportamiento_state;

/**
 * Fabrica para la creacion de estados de venta (State Factory Pattern).
 * Retorna la implementacion de EstadoVenta correspondiente al nombre del estado.
 *
 * @author Sistema de Ventas UTP
 */
public class EstadoFactory {

    private EstadoFactory() {}

    /**
     * Obtiene el estado correspondiente al nombre especificado.
     * @param nombreEstado Nombre del estado (PENDIENTE, PROCESANDO, COMPLETADA, CANCELADA).
     * @return Implementacion de EstadoVenta correspondiente.
     */
    public static EstadoVenta getEstado(String nombreEstado) {
        if (nombreEstado == null) {
            return new EstadoPendiente();
        }
        return switch (nombreEstado.toUpperCase()) {
            case "PENDIENTE" -> new EstadoPendiente();
            case "PROCESANDO" -> new EstadoProcesando();
            case "COMPLETADA" -> new EstadoCompletada();
            case "CANCELADA" -> new EstadoCancelada();
            default -> new EstadoPendiente();
        };
    }
}