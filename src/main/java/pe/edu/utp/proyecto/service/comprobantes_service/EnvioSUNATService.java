package pe.edu.utp.proyecto.service.comprobantes_service;
import pe.edu.utp.proyecto.modelo.comprobantes.EnvioSUNAT;

import java.util.List;
import java.util.Optional;

public interface EnvioSUNATService {
    EnvioSUNAT guardarEnvioSUNAT(EnvioSUNAT envioSUNAT);

    Optional<EnvioSUNAT> obtenerEnvioSUNATPorId(String codigoEnvio);

    List<EnvioSUNAT> obtenerTodosLosEnviosSUNAT();

    EnvioSUNAT actualizarEnvioSUNAT(String codigoEnvio, EnvioSUNAT envioSUNAT);

    void eliminarEnvioSUNAT(String codigoEnvio);
}
