package pe.edu.utp.proyecto.service.comprobantes;

import pe.edu.utp.proyecto.modelo.comprobantes.Boleta;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de boletas.
 * Define las operaciones disponibles para la entidad Boleta.
 */
public interface BoletaService {

    // =============================================
    // CRUD
    // =============================================

    /**
     * Guarda una nueva boleta en el sistema.
     * @param boleta Datos de la boleta a guardar.
     * @return Boleta guardada con su ID generado.
     */
    Boleta guardarBoleta(Boleta boleta);

    /**
     * Busca una boleta por su ID.
     * @param id ID de la boleta.
     * @return Optional con la boleta encontrada o vacio.
     */
    Optional<Boleta> obtenerBoletaPorId(Long id);

    /**
     * Obtiene todas las boletas registradas.
     * @return Lista de todas las boletas.
     */
    List<Boleta> obtenerTodasLasBoletas();

    /**
     * Actualiza los datos de una boleta existente.
     * @param id ID de la boleta a actualizar.
     * @param boleta Datos actualizados de la boleta.
     * @return Boleta actualizada.
     */
    Boleta actualizarBoleta(Long id, Boleta boleta);

    /**
     * Elimina una boleta del sistema.
     * @param id ID de la boleta a eliminar.
     */
    void eliminarBoleta(Long id);

    /**
     * Busca boletas por el DNI del cliente.
     * @param dniCliente DNI del cliente.
     * @return Lista de boletas del cliente.
     */
    List<Boleta> buscarPorDniCliente(String dniCliente);

    /**
     * Busca boletas cuyo total sea mayor a un valor especificado.
     * @param total Valor minimo del total.
     * @return Lista de boletas con total mayor al especificado.
     */
    List<Boleta> buscarPorTotalMayor(double total);
}