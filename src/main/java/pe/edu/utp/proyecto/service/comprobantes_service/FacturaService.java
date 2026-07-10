package pe.edu.utp.proyecto.service.comprobantes_service;

import pe.edu.utp.proyecto.modelo.comprobantes.Factura;
import java.util.List;
import java.util.Optional;

public interface FacturaService {
    Factura guardarFactura(Factura factura);
    Optional<Factura> obtenerFacturaPorId(Long id);
    List<Factura> obtenerTodasLasFacturas();
    Factura actualizarFactura(Long id, Factura factura);
    void eliminarFactura(Long id);
}