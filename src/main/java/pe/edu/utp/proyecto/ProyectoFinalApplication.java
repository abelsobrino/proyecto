package pe.edu.utp.proyecto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicacion Spring Boot.
 *
 * <p>Contiene el punto de entrada del sistema. Al ejecutarla,
 * se inicia el contexto de Spring, se levanta el servidor embebido
 * y se exponen los endpoints REST.</p>
 *
 * <p>Acceso a Swagger: http://localhost:8090/api/swagger-ui/index.html</p>
 *
 * @author Sistema de Ventas UTP
 * @version 1.0.0
 */
@Slf4j
@SpringBootApplication
public class ProyectoFinalApplication {

	private static final String separador = "============================================================";

	/**
	 * Punto de entrada de la aplicacion.
	 * @param args Argumentos de linea de comandos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);

		log.info(separador);
		log.info("SISTEMA DE VENTAS INICIADO");
		log.info(separador);
		log.info("Swagger: http://localhost:8090/api/swagger-ui/index.html");
		log.info(separador);
	}
}