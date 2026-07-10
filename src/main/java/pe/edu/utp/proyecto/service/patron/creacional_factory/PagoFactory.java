package pe.edu.utp.proyecto.service.patron.creacional_factory;

public class PagoFactory {

    private PagoFactory() {}

    public static ProcesadorPago crearPago(String tipo) {
        if ("TARJETA".equalsIgnoreCase(tipo)) {
            return new PagoTarjeta();
        }
        return new PagoEfectivo();
    }
}