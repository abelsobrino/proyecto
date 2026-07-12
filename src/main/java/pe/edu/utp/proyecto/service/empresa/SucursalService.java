package pe.edu.utp.proyecto.service.empresa;

import pe.edu.utp.proyecto.modelo.empresa.Sucursal;
import java.util.List;
import java.util.Optional;

public interface SucursalService {
    Sucursal guardarSucursal(Sucursal sucursal);
    Optional<Sucursal> obtenerSucursalPorId(Integer id);
    List<Sucursal> obtenerTodasLasSucursales();
    Sucursal actualizarSucursal(Integer id, Sucursal sucursal);
    void eliminarSucursal(Integer id);
    List<Sucursal> obtenerSucursalesPorNombre(String nombre);
    List<Sucursal> obtenerSucursalesPorDireccion(String direccion);
}