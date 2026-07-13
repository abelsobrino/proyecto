package pe.edu.utp.proyecto.service.comprobantes;

import pe.edu.utp.proyecto.modelo.comprobantes.Impuesto;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de impuestos.
 * Define las operaciones disponibles para la entidad Impuesto.
 */
public interface ImpuestoService {

    /**
     * Guarda un nuevo impuesto en el sistema.
     * @param impuesto Datos del impuesto a guardar.
     * @return Impuesto guardado.
     */
    Impuesto guardarImpuesto(Impuesto impuesto);

    /**
     * Busca un impuesto por su tipo.
     * @param tipoImpuesto Tipo del impuesto (IGV, ISC, etc.).
     * @return Optional con el impuesto encontrado o vacio.
     */
    Optional<Impuesto> obtenerImpuestoPorId(String tipoImpuesto);

    /**
     * Obtiene todos los impuestos registrados.
     * @return Lista de todos los impuestos.
     */
    List<Impuesto> obtenerTodosLosImpuestos();

    /**
     * Actualiza los datos de un impuesto existente.
     * @param tipoImpuesto Tipo del impuesto a actualizar.
     * @param impuesto Datos actualizados del impuesto.
     * @return Impuesto actualizado.
     */
    Impuesto actualizarImpuesto(String tipoImpuesto, Impuesto impuesto);

    /**
     * Elimina un impuesto del sistema.
     * @param tipoImpuesto Tipo del impuesto a eliminar.
     */
    void eliminarImpuesto(String tipoImpuesto);
}