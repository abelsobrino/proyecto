package pe.edu.utp.proyecto.service.ventas_service;

import pe.edu.utp.proyecto.modelo.ventas.Venta;
import java.util.List;
import java.util.Optional;

public interface VentaService {

    // CRUD
    Venta guardarVenta(Venta venta);
    Optional<Venta> obtenerVentaPorId(Integer id);
    List<Venta> obtenerTodasLasVentas();
    Venta actualizarVenta(Integer id, Venta venta);
    void eliminarVenta(Integer id);

    // Consultas
    List<Venta> obtenerVentasPorEstado(String estado);
    Double calcularTotalVenta(Integer id);

    // METODOS DEL PATRON STATE
    Venta procesarVenta(Integer id);
    Venta completarVenta(Integer id);
    Venta cancelarVenta(Integer id);
}