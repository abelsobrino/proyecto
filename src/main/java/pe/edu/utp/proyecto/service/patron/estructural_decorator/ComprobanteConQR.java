package pe.edu.utp.proyecto.service.patron.estructural_decorator;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.modelo.comprobantes.ComprobanteElectronico;

/**
 * Decorador concreto que agrega funcionalidad de generacion de codigo QR a un comprobante.
 *
 * @author Sistema de Ventas UTP
 */
@Slf4j
public class ComprobanteConQR extends ComprobanteDecorator {

    /**
     * Constructor que recibe el comprobante a decorar.
     * @param comprobante Comprobante electronico base.
     */
    public ComprobanteConQR(ComprobanteElectronico comprobante) {
        super(comprobante);
    }

    /**
     * Emite el comprobante agregando la generacion del codigo QR.
     * Primero emite el comprobante base y luego genera el QR.
     */
    @Override
    public void emitir() {
        super.emitir();
        generarQR();
    }

    /**
     * Simula la generacion de un codigo QR para el comprobante.
     */
    private void generarQR() {
        log.info("Generando codigo QR para el comprobante");
    }
}