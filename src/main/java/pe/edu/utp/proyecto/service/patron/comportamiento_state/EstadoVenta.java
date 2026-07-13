package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import pe.edu.utp.proyecto.modelo.ventas.Venta;

/**
 * Interfaz que define el comportamiento de los estados de una venta (State Pattern).
 * Cada estado concreto implementa las transiciones permitidas.
 *
 * @author Sistema de Ventas UTP
 */
public interface EstadoVenta {

    /**
     * Cambia el estado de la venta a PROCESANDO.
     * @param venta Venta a procesar.
     */
    void procesar(Venta venta);

    /**
     * Cambia el estado de la venta a COMPLETADA.
     * @param venta Venta a completar.
     */
    void completar(Venta venta);

    /**
     * Cambia el estado de la venta a CANCELADA.
     * @param venta Venta a cancelar.
     */
    void cancelar(Venta venta);

    /**
     * Obtiene el nombre del estado actual.
     * @return Nombre del estado.
     */
    String getNombreEstado();
}