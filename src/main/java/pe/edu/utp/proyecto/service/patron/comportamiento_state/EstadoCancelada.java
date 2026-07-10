package pe.edu.utp.proyecto.service.patron.comportamiento_state;

import lombok.extern.slf4j.Slf4j;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.ventas.Venta;

@Slf4j
public class EstadoCancelada implements EstadoVenta {

    @Override
    public void procesar(Venta venta) {
        throw new BusinessException("No se puede procesar una venta cancelada");
    }

    @Override
    public void completar(Venta venta) {
        throw new BusinessException("No se puede completar una venta cancelada");
    }

    @Override
    public void cancelar(Venta venta) {
        throw new BusinessException("La venta ya esta cancelada");
    }

    @Override
    public String getNombreEstado() {
        return "CANCELADA";
    }
}