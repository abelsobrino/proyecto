package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

/**
 * Estado PENDIENTE de una venta.
 * Permite transiciones a: PROCESANDO, COMPLETADA, CANCELADA.
 *
 * @author Sistema de Ventas UTP
 */
@Slf4j
public class EstadoPendiente implements EstadoVenta {

    /**
     * Cambia la venta de PENDIENTE a PROCESANDO.
     * @param venta Venta a procesar.
     */
    @Override
    public void procesar(Venta venta) {
        log.info("Venta {}: PASANDO DE PENDIENTE A PROCESANDO", venta.getIdVenta());
        venta.setEstado("PROCESANDO");
    }

    /**
     * Cambia la venta de PENDIENTE a COMPLETADA.
     * @param venta Venta a completar.
     */
    @Override
    public void completar(Venta venta) {
        log.info("Venta {}: PASANDO DE PENDIENTE A COMPLETADA", venta.getIdVenta());
        venta.setEstado("COMPLETADA");
    }

    /**
     * Cambia la venta de PENDIENTE a CANCELADA.
     * @param venta Venta a cancelar.
     */
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