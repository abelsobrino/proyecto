package pe.edu.utp.proyecto.service.patron.otro;


public class PagoFactory {

    public static ProcesadorPago crearPago(String tipo) {

        if ("TARJETA".equalsIgnoreCase(tipo)) {
            return new PagoTarjeta();
        }

        return new PagoEfectivo();
    }
}