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

    private final Venta venta;

    public EstadoPendiente(Venta venta) {
        this.venta = venta;
    }

    @Override
    public void onEnterState() {
        log.info("{} esta PENDIENTE.", venta);
    }

    @Override
    public void procesar() {
        log.info("{}: PASANDO DE PENDIENTE A PROCESANDO", venta);
        venta.cambiarEstado(new EstadoProcesando(venta));
    }

    @Override
    public void completar() {
        log.info("{}: PASANDO DE PENDIENTE A COMPLETADA", venta);
        venta.cambiarEstado(new EstadoCompletada(venta));
    }

    @Override
    public void cancelar() {
        log.info("{}: PASANDO DE PENDIENTE A CANCELADA", venta);
        venta.cambiarEstado(new EstadoCancelada(venta));
    }

    @Override
    public String getNombreEstado() {
        return "PENDIENTE";
    }
}