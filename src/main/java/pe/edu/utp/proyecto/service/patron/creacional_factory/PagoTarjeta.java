package pe.edu.utp.proyecto.service.patron.creacional_factory;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementacion concreta de ProcesadorPago para pagos con tarjeta.
 *
 * @author Sistema de Ventas UTP
 */
@Slf4j
public class PagoTarjeta implements ProcesadorPago {

    /**
     * Procesa un pago con tarjeta registrando la transaccion.
     */
    @Override
    public void procesarPago() {
        log.info("Pago realizado con tarjeta");
    }
}