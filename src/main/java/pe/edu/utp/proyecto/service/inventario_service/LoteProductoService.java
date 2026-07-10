package pe.edu.utp.proyecto.service.inventario_service;

import pe.edu.utp.proyecto.modelo.inventario.LoteProducto;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LoteProductoService {
    LoteProducto guardarLote(LoteProducto lote);
    Optional<LoteProducto> obtenerLotePorId(Integer id);
    List<LoteProducto> obtenerTodosLosLotes();
    LoteProducto actualizarLote(Integer id, LoteProducto lote);
    void eliminarLote(Integer id);
    List<LoteProducto> obtenerLotesPorCodigo(String codigoLote);
    List<LoteProducto> obtenerLotesPorFechaVencimiento(Date fechaVencimiento);
}