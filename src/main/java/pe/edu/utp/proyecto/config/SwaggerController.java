package pe.edu.utp.proyecto.config;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controlador para redireccionar a Swagger UI.
 *
 * <p>Proporciona endpoints para acceder facilmente a la documentacion de la API.</p>
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@RestController
@Tag(name = "Redireccion", description = "Redireccion a Swagger UI")
public class SwaggerController {

    /**
     * Redirecciona a la pagina principal de Swagger UI.
     * @return Redireccion a /api/swagger-ui/index.html
     */
    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/api/swagger-ui/index.html");
    }

    /**
     * Redirecciona a Swagger UI.
     * @return Redireccion a /api/swagger-ui/index.html
     */
    @GetMapping("/swagger")
    public RedirectView swagger() {
        return new RedirectView("/api/swagger-ui/index.html");
    }
}