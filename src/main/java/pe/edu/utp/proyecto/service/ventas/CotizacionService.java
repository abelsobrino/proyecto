package pe.edu.utp.proyecto.service.ventas;

import pe.edu.utp.proyecto.modelo.ventas.Cotizacion;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CotizacionService {
    Cotizacion guardarCotizacion(Cotizacion cotizacion);
    Optional<Cotizacion> obtenerCotizacionPorId(Integer id);
    List<Cotizacion> obtenerTodasLasCotizaciones();
    Cotizacion actualizarCotizacion(Integer id, Cotizacion cotizacion);
    void eliminarCotizacion(Integer id);
    List<Cotizacion> obtenerCotizacionesEntreFechas(Date inicio, Date fin);
    List<Cotizacion> obtenerCotizacionesPorTotalMayor(Double total);
    List<Cotizacion> obtenerUltimas5Cotizaciones();
}