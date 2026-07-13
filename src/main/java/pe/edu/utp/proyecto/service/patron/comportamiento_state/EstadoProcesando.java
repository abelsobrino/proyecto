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

    /**
     * No permite procesar una venta que ya esta en PROCESANDO.
     * @param venta Venta a procesar.
     * @throws BusinessException si se intenta procesar.
     */
    @Override
    public void procesar(Venta venta) {
        throw new BusinessException("La venta ya esta siendo procesada");
    }

    /**
     * Cambia la venta de PROCESANDO a COMPLETADA.
     * @param venta Venta a completar.
     */
    @Override
    public void completar(Venta venta) {
        log.info("Venta {}: PASANDO DE PROCESANDO A COMPLETADA", venta.getIdVenta());
        venta.setEstado("COMPLETADA");
    }

    /**
     * Cambia la venta de PROCESANDO a CANCELADA.
     * @param venta Venta a cancelar.
     */
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