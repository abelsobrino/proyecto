package pe.edu.utp.proyecto.service.patron.estructural_decorator;

import pe.edu.utp.proyecto.modelo.comprobantes.ComprobanteElectronico;

public abstract class ComprobanteDecorator extends ComprobanteElectronico {
    protected ComprobanteElectronico comprobante;

    protected ComprobanteDecorator(ComprobanteElectronico comprobante) {
        this.comprobante = comprobante;
    }

    @Override
    public void emitir() {
        comprobante.emitir();
    }

    @Override
    public double calcularTotal() {
        return comprobante.calcularTotal();
    }
}