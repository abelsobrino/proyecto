package pe.edu.utp.proyecto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de Swagger/OpenAPI para la documentacion automatica de la API REST.
 *
 * <p>Proporciona informacion general del proyecto como titulo, descripcion,
 * version, contacto y licencia.</p>
 *
 * <p>Acceso: http://localhost:8090/api/swagger-ui/index.html</p>
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura la informacion principal de la API para Swagger.
     * @return OpenAPI con los metadatos del proyecto.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema de Ventas - API REST")
                        .description("API para gestion de ventas, inventario, comprobantes y usuarios")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Desarrollo UTP")
                                .email("soporte@utp.edu.pe"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}