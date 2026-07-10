package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.exception.BusinessException;
import pe.edu.utp.proyecto.modelo.comprobantes.EnvioSUNAT;
import pe.edu.utp.proyecto.repository.comprobantes_repository.EnvioSUNATRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.EnvioSUNATService;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnvioSUNATServiceImpl implements EnvioSUNATService {

    private final EnvioSUNATRepository envioSUNATRepository;

    @Override
    @Transactional
    public EnvioSUNAT guardarEnvioSUNAT(EnvioSUNAT envioSUNAT) {
        try {
            log.info("Guardando envío a SUNAT");
            return envioSUNATRepository.save(envioSUNAT);
        } catch (Exception e) {
            log.error("Error al guardar envio SUNAT: {}", e.getMessage());
            throw new BusinessException("No se pudo guardar el envio SUNAT: " + e.getMessage());
        }
    }

    @Override
    public Optional<EnvioSUNAT> obtenerEnvioSUNATPorId(String codigoEnvio) {
        try {
            log.debug("Buscando envio SUNAT con codigo: {}", codigoEnvio);
            return envioSUNATRepository.findById(codigoEnvio);
        } catch (Exception e) {
            log.error("Error al buscar envio SUNAT: {}", e.getMessage());
            throw new BusinessException("Error al buscar el envio SUNAT: " + e.getMessage());
        }
    }

    @Override
    public List<EnvioSUNAT> obtenerTodosLosEnviosSUNAT() {
        try {
            log.info("Obteniendo todos los envios SUNAT");
            return envioSUNATRepository.findAll();
        } catch (Exception e) {
            log.error("Error al obtener envios SUNAT: {}", e.getMessage());
            throw new BusinessException("Error al obtener los envios SUNAT: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public EnvioSUNAT actualizarEnvioSUNAT(String codigoEnvio, EnvioSUNAT envioSUNAT) {
        try {
            log.info("Actualizando envio SUNAT con codigo: {}", codigoEnvio);
            EnvioSUNAT existente = envioSUNATRepository.findById(codigoEnvio)
                    .orElseThrow(() -> new BusinessException("Envio SUNAT no encontrado con codigo: " + codigoEnvio));
            existente.setEstadoEnvio(envioSUNAT.getEstadoEnvio());
            existente.setFechaEnvio(envioSUNAT.getFechaEnvio());
            return envioSUNATRepository.save(existente);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al actualizar envio SUNAT: {}", e.getMessage());
            throw new BusinessException("No se pudo actualizar el envio SUNAT: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void eliminarEnvioSUNAT(String codigoEnvio) {
        try {
            log.info("Eliminando envio SUNAT con codigo: {}", codigoEnvio);
            if (!envioSUNATRepository.existsById(codigoEnvio)) {
                throw new BusinessException("Envio SUNAT no encontrado con codigo: " + codigoEnvio);
            }
            envioSUNATRepository.deleteById(codigoEnvio);
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error al eliminar envio SUNAT: {}", e.getMessage());
            throw new BusinessException("No se pudo eliminar el envio SUNAT: " + e.getMessage());
        }
    }
}