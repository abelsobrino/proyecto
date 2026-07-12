package pe.edu.utp.proyecto.service.inventario;

import pe.edu.utp.proyecto.modelo.inventario.Almacen;
import java.util.List;
import java.util.Optional;

public interface AlmacenService {
    Almacen guardarAlmacen(Almacen almacen);
    Optional<Almacen> obtenerAlmacenPorId(Integer id);
    List<Almacen> obtenerTodosLosAlmacenes();
    Almacen actualizarAlmacen(Integer id, Almacen almacen);
    void eliminarAlmacen(Integer id);
    List<Almacen> obtenerAlmacenesPorNombre(String nombre);
    List<Almacen> obtenerAlmacenesPorResponsable(String responsable);
}