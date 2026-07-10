package pe.edu.utp.proyecto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ProyectoFinalApplication {

	private static final String SEPARADOR = "============================================================";

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);

		log.info(SEPARADOR);
		log.info("SISTEMA DE VENTAS INICIADO");
		log.info(SEPARADOR);
		log.info("Swagger: http://localhost:8090/api/swagger-ui/index.html");
		log.info("H2:      http://localhost:8090/api/h2-console");
		log.info(SEPARADOR);
	}
}