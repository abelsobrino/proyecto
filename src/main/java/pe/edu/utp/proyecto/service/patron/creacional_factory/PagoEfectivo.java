package pe.edu.utp.proyecto.service.patron.creacional_factory;

import lombok.extern.slf4j.Slf4j;

/**
 * Implementacion concreta de ProcesadorPago para pagos en efectivo.
 *
 * @author Sistema de Ventas UTP
 */
@Slf4j
public class PagoEfectivo implements ProcesadorPago {

    /**
     * Procesa un pago en efectivo registrando la transaccion.
     */
    @Override
    public void procesarPago() {
        log.info("Pago realizado en efectivo");
    }
}