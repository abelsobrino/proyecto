package pe.edu.utp.proyecto.service.comprobantes_service;
import pe.edu.utp.proyecto.modelo.comprobantes.Boleta;

import java.util.List;
import java.util.Optional;

public interface BoletaService {
    Boleta guardarBoleta(Boleta boleta);

    Optional<Boleta> obtenerBoletaPorId(Long id);

    List<Boleta> obtenerTodasLasBoletas();

    Boleta actualizarBoleta(Long id, Boleta boleta);

    void eliminarBoleta(Long id);
}
