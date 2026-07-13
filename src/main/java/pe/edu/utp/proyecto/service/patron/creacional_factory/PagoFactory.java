package pe.edu.utp.proyecto.service.patron.creacional_factory;

/**
 * Fabrica para la creacion de procesadores de pago (Factory Pattern).
 * Oculta la logica de creacion y devuelve la implementacion adecuada segun el tipo.
 *
 * @author Sistema de Ventas UTP
 */
public class PagoFactory {

    private PagoFactory() {}

    /**
     * Crea un procesador de pago segun el tipo especificado.
     * @param tipo Tipo de pago: "TARJETA" o "EFECTIVO" (por defecto).
     * @return Procesador de pago correspondiente.
     */
    public static ProcesadorPago crearPago(String tipo) {
        if ("TARJETA".equalsIgnoreCase(tipo)) {
            return new PagoTarjeta();
        }
        return new PagoEfectivo();
    }
}