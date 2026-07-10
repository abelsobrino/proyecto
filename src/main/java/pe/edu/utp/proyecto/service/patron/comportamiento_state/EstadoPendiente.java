package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

@Slf4j
public class EstadoPendiente implements EstadoVenta {

    @Override
    public void procesar(Venta venta) {
        log.info("Venta {}: PASANDO DE PENDIENTE A PROCESANDO", venta.getIdVenta());
        venta.setEstado("PROCESANDO");
    }

    @Override
    public void completar(Venta venta) {
        log.info("Venta {}: PASANDO DE PENDIENTE A COMPLETADA", venta.getIdVenta());
        venta.setEstado("COMPLETADA");
    }

    @Override
    public void cancelar(Venta venta) {
        log.info("Venta {}: PASANDO DE PENDIENTE A CANCELADA", venta.getIdVenta());
        venta.setEstado("CANCELADA");
    }

    @Override
    public String getNombreEstado() {
        return "PENDIENTE";
    }
}