package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import pe.edu.utp.proyecto.modelo.ventas.Venta;

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
     * @param venta Venta asociada al estado.
     * @return Implementacion de EstadoVenta correspondiente.
     */
    public static EstadoVenta getEstado(String nombreEstado, Venta venta) {
        if (nombreEstado == null || nombreEstado.isEmpty()) {
            return new EstadoPendiente(venta);
        }
        return switch (nombreEstado.toUpperCase()) {
            case "PENDIENTE" -> new EstadoPendiente(venta);
            case "PROCESANDO" -> new EstadoProcesando(venta);
            case "COMPLETADA" -> new EstadoCompletada(venta);
            case "CANCELADA" -> new EstadoCancelada(venta);
            default -> new EstadoPendiente(venta);
        };
    }
}