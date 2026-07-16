package pe.edu.utp.proyecto.service.patron.comportamiento_state;

/**
 * Interfaz que define el comportamiento de los estados de una venta (State Pattern).
 *
 * @author Sistema de Ventas UTP
 */
public interface EstadoVenta {

    /**
     * Accion que se ejecuta al entrar al estado.
     */
    void onEnterState();

    /**
     * Procesa la venta en el estado actual.
     */
    void procesar();

    /**
     * Completa la venta en el estado actual.
     */
    void completar();

    /**
     * Cancela la venta en el estado actual.
     */
    void cancelar();

    /**
     * Obtiene el nombre del estado.
     * @return Nombre del estado.
     */
    String getNombreEstado();
}