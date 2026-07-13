package pe.edu.utp.proyecto.service.ventas;

import pe.edu.utp.proyecto.modelo.ventas.Cotizacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de cotizaciones.
 * Define las operaciones disponibles para la entidad Cotizacion.
 */
public interface CotizacionService {

    /**
     * Guarda una nueva cotizacion en el sistema.
     * @param cotizacion Datos de la cotizacion a guardar.
     * @return Cotizacion guardada con su ID generado.
     */
    Cotizacion guardarCotizacion(Cotizacion cotizacion);

    /**
     * Busca una cotizacion por su ID.
     * @param id ID de la cotizacion.
     * @return Optional con la cotizacion encontrada o vacio.
     */
    Optional<Cotizacion> obtenerCotizacionPorId(Integer id);

    /**
     * Obtiene todas las cotizaciones registradas.
     * @return Lista de todas las cotizaciones.
     */
    List<Cotizacion> obtenerTodasLasCotizaciones();

    /**
     * Actualiza los datos de una cotizacion existente.
     * @param id ID de la cotizacion a actualizar.
     * @param cotizacion Datos actualizados de la cotizacion.
     * @return Cotizacion actualizada.
     */
    Cotizacion actualizarCotizacion(Integer id, Cotizacion cotizacion);

    /**
     * Elimina una cotizacion del sistema.
     * @param id ID de la cotizacion a eliminar.
     */
    void eliminarCotizacion(Integer id);

    /**
     * Busca cotizaciones entre dos fechas.
     * @param inicio Fecha de inicio del rango.
     * @param fin Fecha de fin del rango.
     * @return Lista de cotizaciones en el rango.
     */
    List<Cotizacion> obtenerCotizacionesEntreFechas(Date inicio, Date fin);

    /**
     * Busca cotizaciones cuyo total estimado sea mayor a un valor.
     * @param total Valor minimo del total estimado.
     * @return Lista de cotizaciones con total mayor al especificado.
     */
    List<Cotizacion> obtenerCotizacionesPorTotalMayor(Double total);

    /**
     * Obtiene las 5 cotizaciones mas recientes.
     * @return Lista de las 5 cotizaciones mas recientes.
     */
    List<Cotizacion> obtenerUltimas5Cotizaciones();
}