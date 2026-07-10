package pe.edu.utp.proyecto.service.comprobantes_service;

import pe.edu.utp.proyecto.modelo.comprobantes.Impuesto;
import java.util.List;
import java.util.Optional;

public interface ImpuestoService {
    Impuesto guardarImpuesto(Impuesto impuesto);
    Optional<Impuesto> obtenerImpuestoPorId(String tipoImpuesto);
    List<Impuesto> obtenerTodosLosImpuestos();
    Impuesto actualizarImpuesto(String tipoImpuesto, Impuesto impuesto);
    void eliminarImpuesto(String tipoImpuesto);
}