package pe.edu.utp.proyecto.service.patron.singleton;

import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementacion del patron Singleton para la configuracion global del sistema.
 * Garantiza que solo exista una instancia unica de la configuracion en toda la aplicacion.
 *
 * <p>Contiene propiedades como: impuesto IGV, moneda, descuento maximo, stock minimo, etc.</p>
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@Slf4j
public class ConfiguracionGlobal {

    private static ConfiguracionGlobal instancia;
    private String nombreSistema;
    private String version;
    private String entorno;
    private LocalDateTime fechaInicio;
    private Map<String, Object> propiedades;

    /**
     * Constructor privado para evitar instanciacion externa.
     * Inicializa la configuracion con valores por defecto.
     */
    private ConfiguracionGlobal() {
        this.nombreSistema = "Sistema de Ventas UTP";
        this.version = "1.0.0";
        this.entorno = "DESARROLLO";
        this.fechaInicio = LocalDateTime.now(Clock.system(ZoneId.of("America/Lima")));
        this.propiedades = new HashMap<>();
        cargarPropiedadesDefault();
        log.info("Singleton Configuracion inicializado");
    }

    /**
     * Obtiene la instancia unica de la configuracion global (Singleton).
     * Si no existe, la crea.
     * @return Instancia unica de ConfiguracionGlobal.
     */
    public static synchronized ConfiguracionGlobal getInstance() {
        if (instancia == null) {
            instancia = new ConfiguracionGlobal();
        }
        return instancia;
    }

    /**
     * Carga las propiedades por defecto del sistema.
     */
    private void cargarPropiedadesDefault() {
        propiedades.put("impuesto.igv", 18.0);
        propiedades.put("moneda.defecto", "PEN");
        propiedades.put("descuento.maximo", 30.0);
        propiedades.put("stock.minimo.alerta", 10);
        propiedades.put("timeout.sesion", 30);
    }

    public String getNombreSistema() {
        return nombreSistema;
    }

    public String getVersion() {
        return version;
    }

    public String getEntorno() {
        return entorno;
    }

    public String getFechaInicioFormateada() {
        return fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }

    public void setPropiedad(String clave, Object valor) {
        propiedades.put(clave, valor);
        log.info("Propiedad actualizada: {} = {}", clave, valor);
    }

    public Object getPropiedad(String clave) {
        return propiedades.get(clave);
    }

    public String getStringPropiedad(String clave) {
        Object valor = propiedades.get(clave);
        return valor != null ? valor.toString() : null;
    }

    public Double getDoublePropiedad(String clave) {
        Object valor = propiedades.get(clave);
        if (valor instanceof Double doubleVal) {
            return doubleVal;
        }
        return null;
    }

    public Integer getIntegerPropiedad(String clave) {
        Object valor = propiedades.get(clave);
        if (valor instanceof Integer intVal) {
            return intVal;
        }
        return null;
    }
}