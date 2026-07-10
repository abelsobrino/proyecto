package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import pe.edu.utp.proyecto.modelo.ventas.Venta;

public interface EstadoVenta {

    void procesar(Venta venta);

    void completar(Venta venta);

    void cancelar(Venta venta);

    String getNombreEstado();
}