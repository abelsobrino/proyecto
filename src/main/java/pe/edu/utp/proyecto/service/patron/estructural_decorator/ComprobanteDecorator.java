package pe.edu.utp.proyecto.service.patron.estructural_decorator;

import pe.edu.utp.proyecto.modelo.comprobantes.ComprobanteElectronico;

/**
 * Clase abstracta base para los decoradores de comprobantes (Decorator Pattern).
 * Permite agregar funcionalidades extras a los comprobantes sin modificar la clase original.
 *
 * @author Sistema de Ventas UTP
 */
public abstract class ComprobanteDecorator extends ComprobanteElectronico {

    protected ComprobanteElectronico comprobante;

    /**
     * Constructor protegido que recibe el comprobante a decorar.
     * @param comprobante Comprobante electronico base.
     */
    protected ComprobanteDecorator(ComprobanteElectronico comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * Delega la emision al comprobante base.
     * Las subclases pueden agregar comportamiento adicional.
     */
    @Override
    public void emitir() {
        comprobante.emitir();
    }

    /**
     * Delega el calculo del total al comprobante base.
     * @return Total calculado por el comprobante base.
     */
    @Override
    public double calcularTotal() {
        return comprobante.calcularTotal();
    }
}