package pe.edu.utp.proyecto.service.comprobantes;

import pe.edu.utp.proyecto.modelo.comprobantes.NotaCredito;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de notas de credito.
 * Define las operaciones disponibles para la entidad NotaCredito.
 */
public interface NotaCreditoService {

    /**
     * Guarda una nueva nota de credito en el sistema.
     * @param notaCredito Datos de la nota de credito a guardar.
     * @return Nota de credito guardada con su ID generado.
     */
    NotaCredito guardarNotaCredito(NotaCredito notaCredito);

    /**
     * Busca una nota de credito por su ID.
     * @param id ID de la nota de credito.
     * @return Optional con la nota de credito encontrada o vacio.
     */
    Optional<NotaCredito> obtenerNotaCreditoPorId(Long id);

    /**
     * Obtiene todas las notas de credito registradas.
     * @return Lista de todas las notas de credito.
     */
    List<NotaCredito> obtenerTodasLasNotasCredito();

    /**
     * Actualiza los datos de una nota de credito existente.
     * @param id ID de la nota de credito a actualizar.
     * @param notaCredito Datos actualizados de la nota de credito.
     * @return Nota de credito actualizada.
     */
    NotaCredito actualizarNotaCredito(Long id, NotaCredito notaCredito);

    /**
     * Elimina una nota de credito del sistema.
     * @param id ID de la nota de credito a eliminar.
     */
    void eliminarNotaCredito(Long id);

    /**
     * Busca notas de credito cuyo motivo contenga un texto.
     * @param motivo Texto a buscar en el motivo.
     * @return Lista de notas de credito que coinciden.
     */
    List<NotaCredito> buscarPorMotivo(String motivo);

    /**
     * Busca notas de credito cuyo total sea mayor a un valor especificado.
     * @param total Valor minimo del total.
     * @return Lista de notas de credito con total mayor al especificado.
     */
    List<NotaCredito> buscarPorTotalMayor(double total);
}