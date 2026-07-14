package pe.edu.utp.proyecto.service.patron.estructural_decorator;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.modelo.comprobantes.ComprobanteElectronico;

@Slf4j
public class ComprobanteConPDF extends ComprobanteDecorator {

    public ComprobanteConPDF(ComprobanteElectronico comprobante) {
        super(comprobante);
    }

    @Override
    public void emitir() {
        super.emitir();
        exportarPDF();
    }

    private void exportarPDF() {
        log.info("Exportando comprobante a PDF");
    }
}
