package pe.edu.utp.proyecto.service.patron.estructural_decorator;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.modelo.comprobantes.ComprobanteElectronico;

@Slf4j
public class ComprobanteConQR extends ComprobanteDecorator {

    public ComprobanteConQR(ComprobanteElectronico comprobante) {
        super(comprobante);
    }

    @Override
    public void emitir() {
        super.emitir();
        generarQR();
    }

    private void generarQR() {
        log.info("Generando codigo QR para el comprobante");
    }
}