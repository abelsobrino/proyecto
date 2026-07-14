package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

/**
 * Estado PROCESANDO de una venta.
 * Permite transiciones a: COMPLETADA, CANCELADA.
 * No permite procesar (ya esta en proceso).
 *
 * @author Sistema de Ventas UTP
 */
@Slf4j
public class EstadoProcesando implements EstadoVenta {

    private final Venta venta;

    public EstadoProcesando(Venta venta) {
        this.venta = venta;
    }

    @Override
    public void onEnterState() {
        log.info("{} esta PROCESANDO.", venta);
    }

    @Override
    public void procesar() {
        throw new BusinessException("La venta ya esta siendo procesada");
    }

    @Override
    public void completar() {
        log.info("{}: PASANDO DE PROCESANDO A COMPLETADA", venta);
        venta.cambiarEstado(new EstadoCompletada(venta));
    }

    @Override
    public void cancelar() {
        log.info("{}: PASANDO DE PROCESANDO A CANCELADA", venta);
        venta.cambiarEstado(new EstadoCancelada(venta));
    }

    @Override
    public String getNombreEstado() {
        return "PROCESANDO";
    }
}