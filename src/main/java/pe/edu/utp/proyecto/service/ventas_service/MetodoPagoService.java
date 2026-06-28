package pe.edu.utp.proyecto.service.ventas_service;

import pe.edu.utp.proyecto.modelo.ventas.MetodoPago;

import java.util.List;
import java.util.Optional;

public interface MetodoPagoService {

    // CRUD Básico
    MetodoPago guardarMetodoPago(MetodoPago metodoPago);
    Optional<MetodoPago> obtenerMetodoPagoPorId(Integer id);
    List<MetodoPago> obtenerTodosLosMetodosPago();
    MetodoPago actualizarMetodoPago(Integer id, MetodoPago metodoPago);
    void eliminarMetodoPago(Integer id);

    // Consultas específicas
    Optional<MetodoPago> obtenerMetodoPorNombre(String nombre);
}