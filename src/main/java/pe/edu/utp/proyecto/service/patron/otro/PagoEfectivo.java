package pe.edu.utp.proyecto.service.patron.otro;


public class PagoEfectivo implements ProcesadorPago {

    @Override
    public void procesarPago() {
        System.out.println("Pago realizado en efectivo");
    }
}