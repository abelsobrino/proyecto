package pe.edu.utp.proyecto.service.patron.creacional_factory;

/**
 * Interfaz que define el contrato para los procesadores de pago.
 * Implementa el patron Factory para la creacion de diferentes tipos de pago.
 *
 * @author Sistema de Ventas UTP
 */
public interface ProcesadorPago {

    /**
     * Procesa el pago segun la implementacion especifica (efectivo, tarjeta, etc.).
     */
    void procesarPago();
}