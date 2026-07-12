package pe.edu.utp.proyecto.service.inventario;

import pe.edu.utp.proyecto.modelo.inventario.Inventario;
import java.util.List;
import java.util.Optional;

public interface InventarioService {
    Inventario guardarInventario(Inventario inventario);
    Optional<Inventario> obtenerInventarioPorId(Integer id);
    List<Inventario> obtenerTodosLosInventarios();
    Inventario actualizarInventario(Integer id, Inventario inventario);
    void eliminarInventario(Integer id);
    List<Inventario> obtenerInventariosPorNombre(String nombre);
    List<Inventario> obtenerInventariosPorDescripcion(String descripcion);
}