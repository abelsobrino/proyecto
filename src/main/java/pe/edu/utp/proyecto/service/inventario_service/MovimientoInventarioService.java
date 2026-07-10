package pe.edu.utp.proyecto.service.inventario_service;

import pe.edu.utp.proyecto.modelo.inventario.MovimientoInventario;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovimientoInventarioService {
    MovimientoInventario guardarMovimiento(MovimientoInventario movimiento);
    Optional<MovimientoInventario> obtenerMovimientoPorId(Integer id);
    List<MovimientoInventario> obtenerTodosLosMovimientos();
    MovimientoInventario actualizarMovimiento(Integer id, MovimientoInventario movimiento);
    void eliminarMovimiento(Integer id);
    List<MovimientoInventario> obtenerPorTipo(String tipoMovimiento);
    List<MovimientoInventario> obtenerPorProducto(Integer idProducto);
    List<MovimientoInventario> obtenerEntreFechas(Date inicio, Date fin);
}