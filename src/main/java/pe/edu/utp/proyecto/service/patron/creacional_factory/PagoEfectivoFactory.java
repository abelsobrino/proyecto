package pe.edu.utp.proyecto.service.patron.creacional_factory;

/**
 * Fabrica concreta que crea procesadores de pago en efectivo.
 * Implementa el Factory Method para devolver PagoEfectivo.
 *
 * <p>Equivale a la clase LogisticaTerrestre del ejemplo del profesor.</p>
 *
 * @author Sistema de Ventas UTP
 */
public class PagoEfectivoFactory extends ProcesadorPagoFactory {

    /**
     * Implementacion del Factory Method.
     *
     * @return Un nuevo procesador de pago en efectivo.
     */
    @Override
    public ProcesadorPago crearPago() {
        return new PagoEfectivo();
    }
}