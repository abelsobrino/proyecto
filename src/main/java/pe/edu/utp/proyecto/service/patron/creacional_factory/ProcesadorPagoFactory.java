package pe.edu.utp.proyecto.service.patron.creacional_factory;

/**
 * Clase abstracta que define el Factory Method para crear procesadores de pago.
 *
 * <p>Este es el corazon del patron Factory Method. Las subclases concretas
 * implementan el metodo crearPago() para devolver el tipo especifico de pago.</p>
 *
 * <p>Equivale a la clase Logistica del ejemplo del profesor.</p>
 *
 * @author Sistema de Ventas UTP
 */
public abstract class ProcesadorPagoFactory {

    /**
     * Factory Method que debe ser implementado por las subclases.
     * Cada subclase retorna un tipo especifico de ProcesadorPago.
     *
     * <p>Equivale al metodo crearTransporte() del ejemplo del profesor.</p>
     *
     * @return ProcesadorPago correspondiente a la fabrica concreta.
     */
    public abstract ProcesadorPago crearPago();

    /**
     * Metodo que utiliza el Factory Method para procesar el pago.
     *
     * <p>Este metodo sigue el patron Template Method: define el esqueleto
     * del algoritmo (crear el pago y procesarlo) y delega la creacion
     * a las subclases mediante el Factory Method.</p>
     */
    public void procesar() {
        ProcesadorPago pago = crearPago();
        pago.procesarPago();
    }
}