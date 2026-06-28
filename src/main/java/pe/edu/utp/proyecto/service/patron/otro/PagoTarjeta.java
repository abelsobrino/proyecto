package pe.edu.utp.proyecto.service.patron.otro;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagoTarjeta implements ProcesadorPago {

    @Override
    public void procesarPago() {
        log.info("Pago realizado con tarjeta");
    }
}