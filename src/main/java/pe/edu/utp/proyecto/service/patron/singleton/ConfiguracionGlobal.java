package pe.edu.utp.proyecto.service.patron.singleton;

import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ConfiguracionGlobal {

    private static ConfiguracionGlobal instancia;

    private String nombreSistema;
    private String version;
    private String entorno;
    private LocalDateTime fechaInicio;
    private Map<String, Object> propiedades;

    private ConfiguracionGlobal() {
        this.nombreSistema = "Sistema de Ventas UTP";
        this.version = "1.0.0";
        this.entorno = "DESARROLLO";
        this.fechaInicio = LocalDateTime.now(Clock.system(ZoneId.of("America/Lima")));
        this.propiedades = new HashMap<>();

        cargarPropiedadesDefault();

        log.info("=== SINGLETON CONFIGURACIÓN INICIALIZADO ===");
        log.info("Sistema: {}", nombreSistema);
        log.info("Versión: {}", version);
        log.info("Entorno: {}", entorno);
        log.info("Inicio: {}", fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    public static synchronized ConfiguracionGlobal getInstance() {
        if (instancia == null) {
            instancia = new ConfiguracionGlobal();
        }
        return instancia;
    }

    private void cargarPropiedadesDefault() {
        propiedades.put("impuesto.igv", 18.0);
        propiedades.put("moneda.defecto", "PEN");
        propiedades.put("descuento.maximo", 30.0);
        propiedades.put("stock.minimo.alerta", 10);
        propiedades.put("timeout.sesion", 30);
        log.info("Propiedades por defecto cargadas: {}", propiedades.size());
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

    public void mostrarConfiguracion() {
        log.info("=== CONFIGURACIÓN ACTUAL ===");
        log.info("Sistema: {}", nombreSistema);
        log.info("Versión: {}", version);
        log.info("Entorno: {}", entorno);
        log.info("Iniciado: {}", getFechaInicioFormateada());
        log.info("--- PROPIEDADES ---");
        propiedades.forEach((k, v) -> log.info("{} = {}", k, v));
        log.info("=============================");
    }

    public static synchronized void resetInstance() {
        instancia = null;
        log.info("Singleton reseteado");
    }
}