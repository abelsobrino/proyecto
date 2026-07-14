package pe.edu.utp.proyecto.service.patron.creacional_factory;

/**
 * Fabrica concreta que crea procesadores de pago con tarjeta.
 * Implementa el Factory Method para devolver PagoTarjeta.
 *
 * <p>Equivale a la clase LogisticaMaritima del ejemplo del profesor.</p>
 *
 * @author Sistema de Ventas UTP
 */
public class PagoTarjetaFactory extends ProcesadorPagoFactory {

    /**
     * Implementacion del Factory Method.
     *
     * @return Un nuevo procesador de pago con tarjeta.
     */
    @Override
    public ProcesadorPago crearPago() {
        return new PagoTarjeta();
    }
}