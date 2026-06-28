package pe.edu.utp.proyecto.service.ventas_service;

import pe.edu.utp.proyecto.modelo.ventas.CarritoCompra;

import java.util.List;
import java.util.Optional;

public interface CarritoCompraService {

    CarritoCompra guardarCarrito(CarritoCompra carrito);
    Optional<CarritoCompra> obtenerCarritoPorId(Integer id);
    List<CarritoCompra> obtenerTodosLosCarritos();
    CarritoCompra actualizarCarrito(Integer id, CarritoCompra carrito);
    void eliminarCarrito(Integer id);

    List<CarritoCompra> obtenerPorEstado(String estado);
}