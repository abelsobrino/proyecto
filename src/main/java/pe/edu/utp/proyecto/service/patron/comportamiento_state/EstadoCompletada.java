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

    private final Venta venta;

    public EstadoCompletada(Venta venta) {
        this.venta = venta;
    }

    @Override
    public void onEnterState() {
        log.info("{} esta COMPLETADA.", venta);
    }

    @Override
    public void procesar() {
        throw new BusinessException("No se puede procesar una venta completada");
    }

    @Override
    public void completar() {
        throw new BusinessException("La venta ya esta completada");
    }

    @Override
    public void cancelar() {
        throw new BusinessException("No se puede cancelar una venta completada");
    }

    @Override
    public String getNombreEstado() {
        return "COMPLETADA";
    }
}