package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

@Slf4j
public class EstadoProcesando implements EstadoVenta {

    @Override
    public void procesar(Venta venta) {
        throw new BusinessException("La venta ya esta siendo procesada");
    }

    @Override
    public void completar(Venta venta) {
        log.info("Venta {}: PASANDO DE PROCESANDO A COMPLETADA", venta.getIdVenta());
        venta.setEstado("COMPLETADA");
    }

    @Override
    public void cancelar(Venta venta) {
        log.info("Venta {}: PASANDO DE PROCESANDO A CANCELADA", venta.getIdVenta());
        venta.setEstado("CANCELADA");
    }

    @Override
    public String getNombreEstado() {
        return "PROCESANDO";
    }
}
