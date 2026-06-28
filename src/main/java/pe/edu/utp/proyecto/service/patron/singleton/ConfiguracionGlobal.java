package pe.edu.utp.proyecto.service.patron.singleton;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ConfiguracionGlobal {

    // 1. Instancia única
    private static ConfiguracionGlobal instancia;

    // 2. Variables de configuración
    private String nombreSistema;
    private String version;
    private String entorno;
    private LocalDateTime fechaInicio;
    private Map<String, Object> propiedades;

    // 3. Constructor privado
    private ConfiguracionGlobal() {
        this.nombreSistema = "Sistema de Ventas UTP";
        this.version = "1.0.0";
        this.entorno = "DESARROLLO";
        this.fechaInicio = LocalDateTime.now();
        this.propiedades = new HashMap<>();

        cargarPropiedadesDefault();

        log.info("=== SINGLETON CONFIGURACIÓN INICIALIZADO ===");
        log.info("Sistema: {}", nombreSistema);
        log.info("Versión: {}", version);
        log.info("Entorno: {}", entorno);
        log.info("Inicio: {}", fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    // 4. Método para obtener la instancia
    public static synchronized ConfiguracionGlobal getInstance() {
        if (instancia == null) {
            instancia = new ConfiguracionGlobal();
        }
        return instancia;
    }

    // 5. Cargar propiedades por defecto
    private void cargarPropiedadesDefault() {
        propiedades.put("impuesto.igv", 18.0);
        propiedades.put("moneda.defecto", "PEN");
        propiedades.put("descuento.maximo", 30.0);
        propiedades.put("stock.minimo.alerta", 10);
        propiedades.put("timeout.sesion", 30);
        log.info("Propiedades por defecto cargadas: {}", propiedades.size());
    }

    // 6. Getters
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

    // 7. Métodos para manejar propiedades
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
        return valor instanceof Double ? (Double) valor : null;
    }

    public Integer getIntegerPropiedad(String clave) {
        Object valor = propiedades.get(clave);
        return valor instanceof Integer ? (Integer) valor : null;
    }

    // 8. Métodos de utilidad
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

    // 9. Método para resetear (útil en pruebas)
    public static synchronized void resetInstance() {
        instancia = null;
        log.info("Singleton reseteado");
    }
}