package pe.edu.utp.proyecto.config;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@Tag(name = "Redireccion", description = "Redireccion a Swagger UI")
public class SwaggerController {

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/api/swagger-ui/index.html");
    }

    @GetMapping("/swagger")
    public RedirectView swagger() {
        return new RedirectView("/api/swagger-ui/index.html");
    }
}