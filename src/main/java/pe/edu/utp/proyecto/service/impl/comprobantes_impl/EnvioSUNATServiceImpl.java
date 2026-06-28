package pe.edu.utp.proyecto.service.impl.comprobantes_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.comprobantes.EnvioSUNAT;
import pe.edu.utp.proyecto.repository.comprobantes_repository.EnvioSUNATRepository;
import pe.edu.utp.proyecto.service.comprobantes_service.EnvioSUNATService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EnvioSUNATServiceImpl implements EnvioSUNATService {

    @Autowired
    private EnvioSUNATRepository envioSUNATRepository;

    @Override
    @Transactional
    public EnvioSUNAT guardarEnvioSUNAT(EnvioSUNAT envioSUNAT) {
        log.info("Guardando envío a SUNAT");
        return envioSUNATRepository.save(envioSUNAT);
    }

    @Override
    public Optional<EnvioSUNAT> obtenerEnvioSUNATPorId(String codigoEnvio) {
        log.debug("Buscando envío SUNAT con código: {}", codigoEnvio);
        return envioSUNATRepository.findById(codigoEnvio);
    }

    @Override
    public List<EnvioSUNAT> obtenerTodosLosEnviosSUNAT() {
        log.info("Obteniendo todos los envíos SUNAT");
        return envioSUNATRepository.findAll();
    }

    @Override
    @Transactional
    public EnvioSUNAT actualizarEnvioSUNAT(String codigoEnvio, EnvioSUNAT envioSUNAT) {

        log.info("Actualizando envío SUNAT con código: {}", codigoEnvio);

        EnvioSUNAT existente = envioSUNATRepository.findById(codigoEnvio)
                .orElseThrow(() -> new RuntimeException("Envío SUNAT no encontrado con código: " + codigoEnvio));

        existente.setEstadoEnvio(envioSUNAT.getEstadoEnvio());
        existente.setFechaEnvio(envioSUNAT.getFechaEnvio());

        return envioSUNATRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarEnvioSUNAT(String codigoEnvio) {

        log.info("Eliminando envío SUNAT con código: {}", codigoEnvio);

        envioSUNATRepository.deleteById(codigoEnvio);
    }
}
