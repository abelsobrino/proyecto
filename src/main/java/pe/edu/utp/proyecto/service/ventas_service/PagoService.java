package pe.edu.utp.proyecto.service.ventas_service;

import pe.edu.utp.proyecto.modelo.ventas.Pago;
import java.util.List;
import java.util.Optional;

public interface PagoService {
    Pago guardarPago(Pago pago);
    Optional<Pago> obtenerPagoPorId(Integer id);
    List<Pago> obtenerTodosLosPagos();
    Pago actualizarPago(Integer id, Pago pago);
    void eliminarPago(Integer id);
    List<Pago> obtenerPagosPorMonto(Double montoMinimo);
    Double calcularMontoTotalPagos();
}