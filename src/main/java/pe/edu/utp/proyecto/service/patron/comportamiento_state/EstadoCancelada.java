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

    private final Venta venta;

    public EstadoCancelada(Venta venta) {
        this.venta = venta;
    }

    @Override
    public void onEnterState() {
        log.info("{} esta CANCELADA.", venta);
    }

    @Override
    public void procesar() {
        throw new BusinessException("No se puede procesar una venta cancelada");
    }

    @Override
    public void completar() {
        throw new BusinessException("No se puede completar una venta cancelada");
    }

    @Override
    public void cancelar() {
        throw new BusinessException("La venta ya esta cancelada");
    }

    @Override
    public String getNombreEstado() {
        return "CANCELADA";
    }
}