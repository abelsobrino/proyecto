package pe.edu.utp.proyecto.service.comprobantes;

import pe.edu.utp.proyecto.modelo.comprobantes.EnvioSUNAT;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para la gestion de envios a SUNAT.
 * Define las operaciones disponibles para la entidad EnvioSUNAT.
 */
public interface EnvioSUNATService {

    /**
     * Guarda un nuevo envio a SUNAT en el sistema.
     * @param envioSUNAT Datos del envio a guardar.
     * @return Envio guardado.
     */
    EnvioSUNAT guardarEnvioSUNAT(EnvioSUNAT envioSUNAT);

    /**
     * Busca un envio a SUNAT por su codigo.
     * @param codigoEnvio Codigo del envio.
     * @return Optional con el envio encontrado o vacio.
     */
    Optional<EnvioSUNAT> obtenerEnvioSUNATPorId(String codigoEnvio);

    /**
     * Obtiene todos los envios a SUNAT registrados.
     * @return Lista de todos los envios.
     */
    List<EnvioSUNAT> obtenerTodosLosEnviosSUNAT();

    /**
     * Actualiza los datos de un envio a SUNAT existente.
     * @param codigoEnvio Codigo del envio a actualizar.
     * @param envioSUNAT Datos actualizados del envio.
     * @return Envio actualizado.
     */
    EnvioSUNAT actualizarEnvioSUNAT(String codigoEnvio, EnvioSUNAT envioSUNAT);

    /**
     * Elimina un envio a SUNAT del sistema.
     * @param codigoEnvio Codigo del envio a eliminar.
     */
    void eliminarEnvioSUNAT(String codigoEnvio);
}