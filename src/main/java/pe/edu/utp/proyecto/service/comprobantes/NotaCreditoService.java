package pe.edu.utp.proyecto.service.comprobantes;

import pe.edu.utp.proyecto.modelo.comprobantes.NotaCredito;
import java.util.List;
import java.util.Optional;

public interface NotaCreditoService {
    NotaCredito guardarNotaCredito(NotaCredito notaCredito);
    Optional<NotaCredito> obtenerNotaCreditoPorId(Long id);
    List<NotaCredito> obtenerTodasLasNotasCredito();
    NotaCredito actualizarNotaCredito(Long id, NotaCredito notaCredito);
    void eliminarNotaCredito(Long id);
}