package pe.edu.utp.proyecto.service.impl.empresa_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.proyecto.modelo.empresa.Empresa;
import pe.edu.utp.proyecto.repository.empresa_repository.EmpresaRepository;
import pe.edu.utp.proyecto.service.empresa_service.EmpresaService;
import pe.edu.utp.proyecto.service.patron.exception.BusinessException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public Empresa guardarEmpresa(Empresa empresa) {
        log.info("Guardando nueva empresa");
        return empresaRepository.save(empresa);
    }

    @Override
    public Optional<Empresa> obtenerEmpresaPorId(String ruc) {
        log.debug("Buscando empresa con RUC: {}", ruc);
        return empresaRepository.findById(ruc);
    }

    @Override
    public List<Empresa> obtenerTodasLasEmpresas() {
        log.info("Obteniendo todas las empresas");
        return empresaRepository.findAll();
    }

    @Override
    @Transactional
    public Empresa actualizarEmpresa(String ruc, Empresa empresa) {
        log.info("Actualizando empresa con RUC: {}", ruc);

        Empresa existente = empresaRepository.findById(ruc)
                .orElseThrow(() -> new BusinessException("Empresa no encontrada con RUC: " + ruc));

        existente.setRazonSocial(empresa.getRazonSocial());
        existente.setDireccion(empresa.getDireccion());
        existente.setTelefono(empresa.getTelefono());

        return empresaRepository.save(existente);
    }

    @Override
    @Transactional
    public void eliminarEmpresa(String ruc) {
        log.info("Eliminando empresa con RUC: {}", ruc);
        empresaRepository.deleteById(ruc);
    }
}