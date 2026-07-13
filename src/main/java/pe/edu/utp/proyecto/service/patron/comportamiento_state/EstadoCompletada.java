package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

/**
 * Estado COMPLETADA de una venta.
 * Es un estado final. No permite ninguna transicion.
 *
 * @author Sistema de Ventas UTP
 */
@Slf4j
public class EstadoCompletada implements EstadoVenta {

    /**
     * No permite procesar una venta completada.
     * @param venta Venta a procesar.
     * @throws BusinessException si se intenta procesar.
     */
    @Override
    public void procesar(Venta venta) {
        throw new BusinessException("No se puede procesar una venta completada");
    }

    /**
     * No permite completar una venta ya completada.
     * @param venta Venta a completar.
     * @throws BusinessException si se intenta completar.
     */
    @Override
    public void completar(Venta venta) {
        throw new BusinessException("La venta ya esta completada");
    }

    /**
     * No permite cancelar una venta completada.
     * @param venta Venta a cancelar.
     * @throws BusinessException si se intenta cancelar.
     */
    @Override
    public void cancelar(Venta venta) {
        throw new BusinessException("No se puede cancelar una venta completada");
    }

    @Override
    public String getNombreEstado() {
        return "COMPLETADA";
    }
}