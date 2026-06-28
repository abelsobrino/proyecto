package pe.edu.utp.proyecto.service.inventario_service;

import pe.edu.utp.proyecto.modelo.inventario.Almacen;

import java.util.List;
import java.util.Optional;

public interface AlmacenService {

    // CRUD Básico
    Almacen guardarAlmacen(Almacen almacen);
    Optional<Almacen> obtenerAlmacenPorId(Integer id);
    List<Almacen> obtenerTodosLosAlmacenes();
    Almacen actualizarAlmacen(Integer id, Almacen almacen);
    void eliminarAlmacen(Integer id);

    // Consultas específicas
    List<Almacen> obtenerAlmacenesPorNombre(String nombre);
    List<Almacen> obtenerAlmacenesPorResponsable(String responsable);
}