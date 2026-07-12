package pe.edu.utp.proyecto.service.empresa;

import pe.edu.utp.proyecto.modelo.empresa.Proveedor;
import java.util.List;
import java.util.Optional;

public interface ProveedorService {
    Proveedor guardarProveedor(Proveedor proveedor);
    Optional<Proveedor> obtenerProveedorPorId(Integer id);
    List<Proveedor> obtenerTodosLosProveedores();
    Proveedor actualizarProveedor(Integer id, Proveedor proveedor);
    void eliminarProveedor(Integer id);
    Proveedor obtenerProveedorPorRuc(String ruc);
    List<Proveedor> obtenerProveedoresPorRazonSocial(String razonSocial);
}