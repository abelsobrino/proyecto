package pe.edu.utp.proyecto.service.patron.otro;


public class PagoTarjeta implements ProcesadorPago {

    @Override
    public void procesarPago() {
        System.out.println("Pago realizado con tarjeta");
    }
}