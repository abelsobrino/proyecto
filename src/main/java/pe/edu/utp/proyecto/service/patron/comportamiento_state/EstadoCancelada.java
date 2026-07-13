package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

/**
 * Estado CANCELADA de una venta.
 * Es un estado final. No permite ninguna transicion.
 *
 * @author Sistema de Ventas UTP
 */
@Slf4j
public class EstadoCancelada implements EstadoVenta {

    /**
     * No permite procesar una venta cancelada.
     * @param venta Venta a procesar.
     * @throws BusinessException si se intenta procesar.
     */
    @Override
    public void procesar(Venta venta) {
        throw new BusinessException("No se puede procesar una venta cancelada");
    }

    /**
     * No permite completar una venta cancelada.
     * @param venta Venta a completar.
     * @throws BusinessException si se intenta completar.
     */
    @Override
    public void completar(Venta venta) {
        throw new BusinessException("No se puede completar una venta cancelada");
    }

    /**
     * No permite cancelar una venta ya cancelada.
     * @param venta Venta a cancelar.
     * @throws BusinessException si se intenta cancelar.
     */
    @Override
    public void cancelar(Venta venta) {
        throw new BusinessException("La venta ya esta cancelada");
    }

    @Override
    public String getNombreEstado() {
        return "CANCELADA";
    }
}